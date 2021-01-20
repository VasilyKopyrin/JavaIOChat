package org.kopyrin.vasily.chat.server.log;

public class LogImpl implements Log {

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public void print(String msg, Exception e) {
        print(msg);
        e.printStackTrace();
    }
}
