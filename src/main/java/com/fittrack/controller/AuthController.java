package com.fittrack.controller;


import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.AuthRequest;
import com.fittrack.dto.RegisterRequest;
import com.fittrack.model.User;
import com.fittrack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest req) {
        User u = userService.register(req);
        u.setPassword(null); // hide password
        return ResponseEntity.ok(new ApiResponse<>(true, "Registered", u));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody AuthRequest req) {
        User u = userService.findByEmail(req.getEmail());
        if (!u.getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(401).body(new ApiResponse<>(false, "Invalid credentials", null));
        }
        u.setPassword(null);
        return ResponseEntity.ok(new ApiResponse<>(true, "Logged in", u));
    }
}