package com.cartisan.modern.user.domain;

import com.cartisan.modern.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInitializer {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void createDefaultUser() {
        userRepository.deleteAll();
        userRepository.save(new User("user", "password"));
    }
}
