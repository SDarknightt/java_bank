package org.view;

import org.exceptions.PersonalizedException;
import org.interfaces.AccountRepository;
import org.model.Account;
import org.repository.InMemoryAccountRepository;
import org.service.BankService;

import java.util.Scanner;

public class ViewBank {
    Scanner scanner = new Scanner(System.in);
    BankService bankService;

    public ViewBank(BankService bankService) {
        this.bankService = bankService;
    }

    public static void main(String[] args) {
        AccountRepository repository = new InMemoryAccountRepository();
        BankService service = new BankService(repository);
        ViewBank view = new ViewBank(service);

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

    // ---- MENU ----
    private void showMenu() {
        System.out.println("----- BANK SYSTEM -----");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Ver detalhes da conta");
        System.out.println("3 - Sacar");
        System.out.println("4 - Depositar");
        System.out.println("5 - Transferir");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    // ---- FLOWS ----
    private void createAccountFlow() {
        System.out.print("Documento do usuário: ");
        String document = getStringValue();

        System.out.print("Saldo inicial: ");
        double balance = getDoubleValue();

        Account account = bankService.createAccount(document, balance);
        formatAccountInfo(account);
    }

    private void showAccountFlow() {
        System.out.print("Documento do usuário: ");
        String document = getStringValue();

        Account account = bankService.getAccountByDocument(document);
        formatAccountInfo(account);
    }

    private void depositFlow() {
        System.out.print("Documento do usuário: ");
        String document = getStringValue();

        System.out.print("Valor para depósito: ");
        double value = getDoubleValue();

        bankService.deposit(document, value);
        System.out.println("Depósito realizado com sucesso.");
    }

    private void withdrawFlow() {
        System.out.print("Documento do usuário: ");
        String document = getStringValue();

        System.out.print("Valor para saque: ");
        double value = getDoubleValue();

        bankService.withdraw(document, value);
        System.out.println("Saque realizado com sucesso.");
    }

    private void transferFlow() {
        System.out.print("Documento da conta origem: ");
        String origin = getStringValue();

        System.out.print("Documento da conta destino: ");
        String destination = getStringValue();

        System.out.print("Valor da transferência: ");
        double value = getDoubleValue();

        bankService.transfer(origin, destination, value);
        System.out.println("Transferência realizada com sucesso.");
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
}
