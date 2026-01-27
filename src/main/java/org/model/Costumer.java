package org.model;

public class Costumer extends User {

    public Costumer(String firstName, String lastName, String cpf) {
        super(firstName, lastName, cpf);
    }

    public static class CostumerBuilder {
        private String firstName;
        private String lastName;
        private String cpf;

        public static CostumerBuilder newBuilder() {
            return new CostumerBuilder();
        }

        public User build() {
            return new Costumer(this.firstName, this.lastName, this.cpf);
        }

        public CostumerBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CostumerBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CostumerBuilder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
    }
}
