package org.model;

public class User {
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

    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private String cpf;

        public static UserBuilder newBuilder() {
            return new UserBuilder();
        }

        public User build() {
            return new User(this.firstName, this.lastName, this.cpf);
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
    }

    @Override
    public String toString() {
        return "[Nome Completo] " + this.getFullName() + "\n[CPF] " + this.getCpf();
    }
}
