package com.rolebase.security.servicesimpl;

import com.rolebase.security.exception.UserNotFountException;
import com.rolebase.security.model.User;
import com.rolebase.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j  // ✅ For logging
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    private static final int TOKEN_EXPIRY_MINUTES = 15;  // ✅ Use constant

    public String generateResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFountException("User with email " + email + " not found"));

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setTokenExpiry(LocalDateTime.now().plusMinutes(TOKEN_EXPIRY_MINUTES));

        userRepository.save(user);
        sendResetEmail(user.getEmail(), resetToken);

        log.info("Password reset token generated for user: {}", email);  // ✅ Logging
        return "Password reset email sent successfully!";
    }

    private void sendResetEmail(String email, String token) {
        String resetLink = "http://localhost:8080/auth/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Hello,\n\nClick the link below to reset your password:\n" + resetLink +
                "\n\nThis link will expire in " + TOKEN_EXPIRY_MINUTES + " minutes.\n\nIf you didn’t request a password reset, please ignore this email.\n\nRegards,\nYour Support Team");

        try {
            mailSender.send(message);
            log.info("Password reset email sent to {}", email);  // ✅ Logging
        } catch (Exception e) {
            log.error("Error sending password reset email to {}: {}", email, e.getMessage());
        }
    }

    public String resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new UserNotFountException("Invalid or expired token"));

        if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            return "Token has expired. Please request a new password reset.";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiry(null);
        userRepository.save(user);

        log.info("Password reset successful for user: {}", user.getEmail());  // ✅ Logging

        // 🚀 If using JWT, invalidate previous tokens here
        return "Password reset successful. Please log in again.";
    }
}
