package com.cartisan.modern;

import com.cartisan.modern.user.User;
import com.cartisan.modern.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInitializer {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void afterPropertiesSet(){
        userRepository.deleteAll();
        userRepository.save(new User("user", "password"));
    }
}
