package org.kopyrin.vasily.chat.server.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket implements Comparable {

    public final Socket socket;
    public DataInputStream in;
    public DataOutputStream out;

    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }



    public String read() throws Exception {
        return in.readUTF();
    }

    public void write(String msg) throws Exception {
        out.writeUTF(msg);
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
