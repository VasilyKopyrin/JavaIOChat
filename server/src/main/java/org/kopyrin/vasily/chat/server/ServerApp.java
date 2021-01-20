package org.kopyrin.vasily.chat.server;

import org.kopyrin.vasily.chat.message.*;

import org.kopyrin.vasily.chat.server.log.Log;
import org.kopyrin.vasily.chat.server.log.LogImpl;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ServerApp {

    public static final int PORT = 8989;

    public static void main(String[] args) {
/*//        Default port
        int port = PORT;
//        Create Log
        Log log = new LogImpl();

        if(args.length != 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                log.print("Wrong port", e);
                System.exit(-1);
            }
        }

//        Create and start server
        new MyServer(PORT, log).start();*/


        A a = new A();
        B b = new B();

       /* a.printA();
        b.printB();*/
        A a1 = (A) b;
        a1.printA();


    }

    static class A {
        private int a = 1;

        private void printA() {
            System.out.println(a);
        }
    }

    static class B extends A {
        int b = 2;
        void printB() {
            System.out.println(b);
        }
    }
}
