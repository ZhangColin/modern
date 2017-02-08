package com.cartisan.modern.account.repository;

import com.cartisan.modern.account.domain.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AccountRepository extends Repository<Account, Long> {
    List<Account> findAll();

    void save(Account account);

    @Query("SELECT CASE WHEN COUNT(account) > 0 THEN 'true' ELSE 'false' END from Account account where account.name = ?1")
    boolean existsByName(String name);
}
