package com.cartisan.modern.account.repository;

import com.cartisan.modern.account.domain.Account;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface AccountRepository extends Repository<Account, Long> {
    void save(Account account);
}
