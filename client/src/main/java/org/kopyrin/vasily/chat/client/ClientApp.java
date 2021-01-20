package org.kopyrin.vasily.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    public static final String HOST = "localhost";
    public static final int PORT = 8989;

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket(HOST, PORT);
             Scanner scanner = new Scanner(System.in);
             DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            label: while(true) {

                String msg = scanner.nextLine();
                switch (msg) {
                    case "check" -> {
                        if(clientSocket.isConnected()) {
                            System.out.println("isConnected true");
                        } else {
                            System.out.println("isConnected false");
                        }
                    }
                    case "quit" -> {
                        System.out.println("Client close");
                        break label;
                    }
                    default -> {
                        try {
                            out.writeUTF(msg);
                            out.flush();
                        } catch (IOException e) {
                            System.out.println("Server down");
                            break label;
                        }
                    }
                }
            }

            System.out.println("Client is stopped");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
