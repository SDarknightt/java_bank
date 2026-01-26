package org.repository;

import org.interfaces.AccountRepository;
import org.model.Account;
import org.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<Long, Account> accounts = new HashMap<>();

    public Account create(Account account) {
        accounts.put(account.getAccountCode(), account);
        return account;
    }

    public Optional<Account> findByCode(long accountCode) {
        return Optional.ofNullable(accounts.get(accountCode));
    }

    public Account update(Account account) {
        accounts.put(account.getAccountCode(), account);
        return account;
    }

    public List<Account> findAllUserAccounts(User user) {
        return this.accounts.values()
                .stream()
                .filter(account -> account.getOwner().getCpf().equals(user.getCpf()))
                .collect(Collectors.toList());
    }

}
