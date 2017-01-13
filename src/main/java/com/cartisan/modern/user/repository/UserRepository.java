package com.cartisan.modern.user.repository;

import com.cartisan.modern.user.domain.User;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends Repository<User, Long> {
    void save(User user);
    void deleteAll();
}
