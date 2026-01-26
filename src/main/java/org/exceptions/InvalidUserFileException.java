package org.exceptions;

public class InvalidUserFileException extends PersonalizedException {

    public InvalidUserFileException(String message) {
        super(message);
    }

    public InvalidUserFileException() {
        super("Arquivo do usuário inválido!");
    }
}
