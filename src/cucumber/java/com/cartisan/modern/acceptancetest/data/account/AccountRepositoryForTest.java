package com.cartisan.modern.acceptancetest.data.account;

import com.cartisan.modern.account.domain.Account;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AccountRepositoryForTest extends Repository<Account, Long> {
    List<Account> findAll();

    void deleteAll();
}
