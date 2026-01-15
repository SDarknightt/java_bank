package org.exceptions;

public class AccountAlreadyExistsException extends PersonalizedException {

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException() {
        super("Conta já existente!");
    }
}
