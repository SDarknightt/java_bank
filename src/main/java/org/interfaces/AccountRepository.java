package org.interfaces;

import org.model.Account;
import org.model.User;

import java.util.Optional;
import java.util.List;

public interface AccountRepository {

    public Account create(Account account);

    public Optional<Account> findByCode(long accountCode);

    public Account update(Account account);

    public List<Account> findAllUserAccounts(User user);
}
