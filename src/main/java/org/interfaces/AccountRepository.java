package org.interfaces;

import org.model.Account;

import java.util.Optional;

public interface AccountRepository {

    public Account create(Account account);

    public Optional<Account> getByUserDocument(String document);

    public Account update(Account account);
}
