package org.view;

import org.exceptions.InvalidUserFileException;
import org.exceptions.PersonalizedException;
import org.interfaces.AccountRepository;
import org.model.Account;
import org.model.Costumer;
import org.model.User;
import org.repository.InMemoryAccountRepository;
import org.service.BankService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class ViewBank {
    Scanner scanner = new Scanner(System.in);
    BankService bankService;
    User loggedUser;

    public ViewBank(BankService bankService) {
        this.bankService = bankService;
    }

    public static void main(String[] args) {
        AccountRepository repository = new InMemoryAccountRepository();
        BankService service = new BankService(repository);
        ViewBank view = new ViewBank(service);
        view.loggedUser = getUserFromMemory();
        System.out.println(view.loggedUser);
        view.start();
    }

    public void start() {
        boolean closeApp = false;

        while (!closeApp) {
            showMenu();
            int option = (int) getLongValue();

            try {
                switch (option) {
                    case 1 -> createAccountFlow();
                    case 2 -> showAccountFlow();
                    case 3 -> withdrawFlow();
                    case 4 -> depositFlow();
                    case 5 -> transferFlow();
                    case 0 -> closeApp = true;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException | PersonalizedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }

        System.out.println("Aplicação encerrada.");
    }

    // ---- USER INFO ----
    private static User getUserFromMemory() {
        Path userDocument = Path.of("src/main/java/org/document/user-document.txt");
        try (BufferedReader bufferedReader = Files.newBufferedReader(userDocument, StandardCharsets.UTF_8);) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] docs = line.split(",");
                if (docs.length == 3) {
                    return Costumer.CostumerBuilder.newBuilder()
                                            .setFirstName(docs[0].trim())
                                            .setLastName(docs[1].trim())
                                            .setCpf(docs[2].trim())
                                            .build();
                }
            }
            throw new InvalidUserFileException();
        } catch (IOException | InvalidUserFileException e) {
            throw new InvalidUserFileException();
        }
    }

    // ---- MENU ----
    private void showMenu() {
        System.out.println("----- BANK SYSTEM -----");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Listar contas");
        System.out.println("3 - Sacar");
        System.out.println("4 - Depositar");
        System.out.println("5 - Transferir");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    // ---- FLOWS ----
    private void createAccountFlow() {
        System.out.print("Saldo inicial: ");
        double balance = getDoubleValue();

        Account account = bankService.createAccount(this.loggedUser, balance);
        formatAccountInfo(account);
    }

    private void showAccountFlow() {
        List<Account> account = bankService.getAllUserAccounts(this.loggedUser);
        formatAccountInfo(account);
    }

    private void depositFlow() {
        System.out.print("Informe o número da conta: ");
        long accountCode = getLongValue();

        System.out.print("Informe o valor para depósito: ");
        double value = getDoubleValue();

        bankService.deposit(accountCode, value);
        System.out.println("Depósito realizado com sucesso!");
    }

    private void withdrawFlow() {
        System.out.print("Informe o número da conta: ");
        long accountCode = getLongValue();

        System.out.print("Informe o valor para saque: ");
        double value = getDoubleValue();

        bankService.withdraw(accountCode, value);
        System.out.println("Saque realizado com sucesso!");
    }

    private void transferFlow() {
        System.out.print("Informe o número da conta de origem: ");
        long origin = getLongValue();

        System.out.print("Informe o número da conta de destino: ");
        long destination = getLongValue();

        System.out.print("Informe o valor da transferência: ");
        double value = getDoubleValue();

        bankService.transfer(origin, destination, value);
        System.out.println("Transferência realizada com sucesso!");
    }

    // ---- HELPERS ----
    private long getLongValue() {
        while (true) {
            try {
                return Long.parseLong(this.getStringValue());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
            }
        }
    }

    private double getDoubleValue() {
        while (true) {
            try {
                return Double.parseDouble(this.getStringValue());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
            }
        }
    }

    private String getStringValue() {
        return scanner.nextLine().trim();
    }

    private void formatAccountInfo(Account account) {
        System.out.println("----- ACCOUNT INFO -----");
        System.out.println(account);
    }

    private void formatAccountInfo(List<Account> accounts) {
        for (Account account : accounts) {
            this.formatAccountInfo(account);
        }
    }
}
