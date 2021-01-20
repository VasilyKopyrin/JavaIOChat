package org.kopyrin.vasily.chat.server.log;

public interface Log {

    void print(String msg);

    void print(String msg, Exception e);
}
