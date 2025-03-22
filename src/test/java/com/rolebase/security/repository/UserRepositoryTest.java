package com.rolebase.security.repository;


import com.rolebase.security.model.Role;
import com.rolebase.security.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest  // for repository testing with H2
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
                .username("nanduchopade")
                .fullName("Nandu Ashok Chopade")
                .email("nanduchopade1998@gmail.com")
                .mobileNumber("8788779605")
                .password("nandu@123")
                .role(Role.ADMIN)
                .build();
    }


    @Test
    void testSaveUser() {
        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals("nanduchopade", savedUser.getUsername());

        // Verify persistence
        Optional<User> fetchedUser = userRepository.findById(savedUser.getId());
        assertTrue(fetchedUser.isPresent());
        assertEquals("nanduchopade", fetchedUser.get().getUsername());
    }






}

