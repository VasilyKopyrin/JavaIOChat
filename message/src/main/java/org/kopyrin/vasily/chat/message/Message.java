package org.kopyrin.vasily.chat.message;

public abstract class Message {

    protected final Type type;
    protected final String login;

    public Message(final Type type, final String login) {
        this.type = type;
        this.login = login;
    }

    public Type getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }
}
