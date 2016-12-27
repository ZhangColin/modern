package com.cartisan.modern.user;

import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends Repository<User, Long> {
    void save(User user);
    void deleteAll();
}
