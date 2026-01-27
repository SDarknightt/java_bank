package org.model;

public abstract class User {
    private String firstName;
    private String lastName;
    private String cpf;

    public User(String firstName, String lastName, String cpf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "[Nome Completo] " + this.getFullName() + "\n[CPF] " + this.getCpf();
    }
}
