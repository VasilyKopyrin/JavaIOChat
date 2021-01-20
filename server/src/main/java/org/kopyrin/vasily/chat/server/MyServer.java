package org.kopyrin.vasily.chat.server;

import org.kopyrin.vasily.chat.server.handler.ClientSocket;
import org.kopyrin.vasily.chat.server.log.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyServer {

    private ServerSocket serverSocket;
    CopyOnWriteArrayList<ClientSocket> clientSockets = new CopyOnWriteArrayList<>();
    private Log log;
    //    private Database db;
    private volatile boolean handlerLoopStop = false;
    private volatile boolean serverStop = false;

    MyServer(int port, Log log) {
        this.log = log;
        try {
            serverSocket = new ServerSocket(port);
            log.print("Server is running on port: " + port);
        } catch (IOException e) {
            log.print("Server error", e);
            System.exit(-1);
        }
//        db = new DatabaseImpl();
    }

    void start() {

        serverCommandLineStart();
        clientSocketsHandlerLoop();

        try {
            while (!serverSocket.isClosed()) {
                System.out.println("Waiting users...");
                Socket socket = serverSocket.accept();
                clientSockets.add(new ClientSocket(socket));
                System.out.println("user connected");
                System.out.println("user count: " + clientSockets.size());
            }
        } catch (IOException e) {
            if(serverStop) {
                System.out.println("Server down");
            }
            else {
                e.printStackTrace();
            }
        }
    }

    private void serverCommandLineStart() {
        Thread t = new Thread(() -> {
            Scanner scan = new Scanner(System.in);
            while(true) {
                String command = scan.nextLine();
                if (command.equals("stop")) {
                    try {
                        stop();
                        break;
                    } catch (IOException e) {
                        log.print("Error stopping the server: ", e);
                    }
                }
            }
            System.out.println("Server command line stopped");
        });

        t.setDaemon(true);
        t.start();
    }

    void stop() throws IOException {
        handlerLoopStop = true;
        for(ClientSocket c : clientSockets) {
            c.close();
        }
        System.out.println("client sockets are closed");

        serverStop = true;
        serverSocket.close();
        System.out.println("server socket is closed");
    }

    private void clientSocketsHandlerLoop() {
        Thread t = new Thread(() -> {
            int i = 0;
            while(!handlerLoopStop) {
                clientSockets.parallelStream().forEach((c) -> {
                    if(c.socket.isClosed()) {
                        clientSockets.remove(c);
                    } else {

                        int availableBytes = 0;
                        try {
                            availableBytes = c.in.available();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(availableBytes > 0) {
                            String msg = "";
                            try {
                                msg = c.in.readUTF();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            switch (msg) {
                                case "/exit" -> {
                                    clientSockets.remove(c);
                                    c.close();
                                }
                                case "" -> {}
                                default -> System.out.println(msg);
                            }
                        }
                    }
                });
            }

            System.out.println("socket handler loop is stopped");

        });
        t.setDaemon(true);
        t.start();
    }


}
