package org.kopyrin.vasily.chat.message;

public class Broadcast extends Quit {

    protected final String msg;

    public Broadcast(final Type type, final String login, final String msg) {
        super(type, login);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
