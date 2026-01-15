package org.model;

public class Client extends User {
    private String category;

    public Client(String firstName, String lastName, String cpf, String category) {
        super(firstName, lastName, cpf);
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
