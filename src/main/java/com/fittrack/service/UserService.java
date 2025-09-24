package com.fittrack.service;


import com.fittrack.dto.RegisterRequest;
import com.fittrack.model.User;
import com.fittrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
@Transactional
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(RegisterRequest req) {
        Optional<User> existing = userRepository.findByEmail(req.getEmail());
        if (existing.isPresent()) throw new RuntimeException("Email already registered");


        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
// NOTE: in production, hash passwords (BCrypt)
        u.setPassword(req.getPassword());
        return userRepository.save(u);
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}