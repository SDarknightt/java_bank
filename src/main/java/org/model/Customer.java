package org.model;

public class Customer extends User {

    public Customer(String firstName, String lastName, String cpf) {
        super(firstName, lastName, cpf);
    }

    public static class CustomerBuilder {
        private String firstName;
        private String lastName;
        private String cpf;

        public static CustomerBuilder newBuilder() {
            return new CustomerBuilder();
        }

        public User build() {
            return new Customer(this.firstName, this.lastName, this.cpf);
        }

        public CustomerBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
    }
}
