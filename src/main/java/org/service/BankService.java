package org.service;

import org.exceptions.AccountNotFoundException;
import org.interfaces.AccountRepository;
import org.model.Account;
import org.model.User;

import java.util.List;
import java.util.Optional;

public class BankService {
    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(User user, double initialBalance) {
        Account newAccount = new Account(user, initialBalance);
        return this.accountRepository.create(newAccount);
    }

    public List<Account> getAllUserAccounts(User user) {
        List<Account> accounts = this.accountRepository.findAllUserAccounts(user);
        if (accounts.isEmpty()) throw new AccountNotFoundException("Nenhuma conta foi encontrada!");
        return accounts;
    }

    public void deposit(long accountCode, double value) {
        Optional<Account> account = this.accountRepository.findByCode(accountCode);
        if (account.isEmpty()) throw new AccountNotFoundException();
        account.get().deposit(value);
    }

    public void withdraw(long accountCode, double value) {
        Optional<Account> account = this.accountRepository.findByCode(accountCode);
        if (account.isEmpty()) throw new AccountNotFoundException();
        account.get().withdraw(value);
    }

    public void transfer(long originAccountCode, long destinationAccountCode, double value) {
        Account originAccount = this.accountRepository.findByCode(originAccountCode)
                .orElseThrow(() -> new AccountNotFoundException("Conta de origem não encontrada!"));

        Account destinationAccount = this.accountRepository.findByCode(destinationAccountCode)
                .orElseThrow(() -> new AccountNotFoundException("Conta de destino não encontrada!"));

        // Should have a transaction here
        originAccount.withdraw(value);
        destinationAccount.deposit(value);

        this.accountRepository.update(originAccount);
        this.accountRepository.update(destinationAccount);
    }
}
