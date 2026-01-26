package org.model;
import org.exceptions.InvalidOperationValueException;

public class Account {

    private final long accountCode;
    private final User owner;
    private double balance;

    public Account(User owner, double initialBalance) {
        if (initialBalance < 0) throw new InvalidOperationValueException();
        this.owner = owner;
        this.accountCode = generateAccountCode();
        deposit(initialBalance);
    }

    public Account(User owner) {
        this(owner, 0);
    }

    public static long generateAccountCode() {
        // FIX: Possibility of collision, but for learning is okay
        return (long) (Math.random() * 9999);
    }

    public long getAccountCode() {
        return this.accountCode;
    }

    public User getOwner() {
        return this.owner;
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
        return Long.valueOf(this.accountCode).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!object.getClass().getName().contains("Account")) return false;
        Account account = (Account) object;
        return this.getAccountCode() == account.getAccountCode();
    }

    @Override
    public String toString() {
        return "ID: " + this.getAccountCode() + " " + "BALANCE: " + String.format("%.2f", this.getBalance());
    }
}
