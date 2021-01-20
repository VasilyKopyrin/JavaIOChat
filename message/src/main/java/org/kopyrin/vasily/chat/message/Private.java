package org.kopyrin.vasily.chat.message;

public class Private extends Broadcast {

    private final String to;

    public Private(final Type type, final String login, final String msg, final String to) {
        super(type, login, msg);
        this.to = to;
    }

    public String getTo() {
        return to;
    }
}
