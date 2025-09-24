package com.fittrack.util;


import com.fittrack.model.User;
import com.fittrack.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class DataLoader {
    private final UserRepository userRepository;


    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void load() {
        if (userRepository.count() == 0) {
            User u = new User();
            u.setName("Demo User");
            u.setEmail("demo@fittrack.app");
            u.setPassword("password");
            u.setAge(25);
            u.setHeight(175.0);
            u.setWeight(70.0);
            userRepository.save(u);
        }
    }
}