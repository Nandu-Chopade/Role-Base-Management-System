package com.rolebase.security.controller;

import com.rolebase.security.model.User;
import com.rolebase.security.security.AuthService;
import com.rolebase.security.servicesimpl.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private ForgotPasswordService forgotPasswordService;
    public AuthController(AuthService authService, ForgotPasswordService forgotPasswordService) {
        this.authService = authService;
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authService.authenticateUser(username, password);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        return forgotPasswordService.generateResetToken(email);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        return forgotPasswordService.resetPassword(token, newPassword);
    }
}
