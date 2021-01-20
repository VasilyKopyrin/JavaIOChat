package org.kopyrin.vasily.chat.message;

public class Reg extends Auth {

    private final String pass2;

    public Reg(final Type type, final String login, final String pass, final String pass2) {
        super(type, login, pass);
        this.pass2 = pass2;
    }

    public String getPass2() {
        return pass2;
    }
}
