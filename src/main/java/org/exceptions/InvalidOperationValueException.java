package org.exceptions;

public class InvalidOperationValueException extends PersonalizedException {

    public InvalidOperationValueException(String message) {
        super(message);
    }

    public InvalidOperationValueException() {
        super("Valor informado para a operação é inválido!");
    }
}
