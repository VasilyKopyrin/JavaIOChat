package org.kopyrin.vasily.chat.message;

public class Auth extends Quit {

    protected String pass;

    public Auth(final Type type, final String login, final String pass) {
        super(type, login);
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }
}
