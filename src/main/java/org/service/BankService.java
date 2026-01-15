package org.service;

import org.exceptions.AccountAlreadyExistsException;
import org.exceptions.AccountNotFoundException;
import org.interfaces.AccountRepository;
import org.model.Account;

import java.util.Optional;

public class BankService {
    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String document, double initialBalance) {
        if (this.accountRepository.getByUserDocument(document).isPresent()) throw new AccountAlreadyExistsException("Já existe uma conta vinculada a este documento!");

        Account newAccount = new Account(document, initialBalance);
        return this.accountRepository.create(newAccount);
    }

    public Account getAccountByDocument(String document) {
        Optional<Account> acc = this.accountRepository.getByUserDocument(document);
        if (acc.isEmpty()) throw new AccountNotFoundException();
        return acc.get();
    }

    public void deposit(String document, double value) {
        Account account = this.getAccountByDocument(document);
        account.deposit(value);
    }

    public void withdraw(String document, double value) {
        Account account = this.getAccountByDocument(document);
        account.withdraw(value);
    }

    public void transfer(String originDocumentOwner, String destinationDocumentOwner, double value) {
        Account originAccount = this.getAccountByDocument(originDocumentOwner);
        Account destinationAccount = this.getAccountByDocument(destinationDocumentOwner);

        // Should have a transaction here
        originAccount.withdraw(value);
        destinationAccount.deposit(value);

        this.accountRepository.update(originAccount);
        this.accountRepository.update(destinationAccount);
    }
}
