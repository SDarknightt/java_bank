package org.exceptions;

public class AccountNotFoundException extends PersonalizedException {

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException() {
        super("Conta não encontrada!");
    }
}
