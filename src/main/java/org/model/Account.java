package org.model;

import org.exceptions.InvalidOperationValueException;

public class Account {
    private static int incrementId = 0;
    private final int id;
    private final String ownerDocument;
    private double balance;

    public Account(String document, double initialBalance) {
        if (initialBalance < 0) throw new InvalidOperationValueException();
        this.ownerDocument = document;
        this.balance = initialBalance;
        this.id = getNewId();
    }

    public Account(String document) {
        this(document, 0);
    }

    private int getNewId() {
        incrementId = incrementId + 1;
        return incrementId;
    }

    public int getId() {
        return this.id;
    }

    public String getOwnerDocument() {
        return this.ownerDocument;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double money) {
        if (money <= 0) {
            throw new InvalidOperationValueException("Valor inválido para depósito!");
        }
        this.balance += money;
    }

    public void withdraw(double value) {
        if (value <= 0) {
            throw new InvalidOperationValueException("Valor inválido para saque!");
        } else if (value > this.balance) {
            throw new InvalidOperationValueException("Valor informado é maior que o saldo disponível!");
        }
        this.balance -= value;
    }

    @Override
    public int hashCode() {
        return this.ownerDocument.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!object.getClass().getName().contains("Account")) return false;
        Account account = (Account) object;
        return this.getOwnerDocument().equals(account.getOwnerDocument());
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " " + "BALANCE: " + String.format("%.2f", this.getBalance());
    }
}
