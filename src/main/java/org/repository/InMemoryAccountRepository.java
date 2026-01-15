package org.repository;

import org.interfaces.AccountRepository;
import org.model.Account;

import java.util.*;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account create(Account account) {
        accounts.put(account.getOwnerDocument(), account);
        return account;
    }

    public Optional<Account> getByUserDocument(String document) {
        return Optional.ofNullable(accounts.get(document));
    }


    public Account update(Account account) {
        accounts.put(account.getOwnerDocument(), account);
        return account;
    }

}
