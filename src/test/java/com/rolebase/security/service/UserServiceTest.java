package com.rolebase.security.service;

import com.rolebase.security.model.Role;
import com.rolebase.security.model.User;
import com.rolebase.security.repository.UserRepository;
import com.rolebase.security.services.UserService;
import com.rolebase.security.servicesimpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;



    @Test
    void testSaveUser(){

        // Arrange
        User user = User.builder().id(1L)
                .username("nanduchopade")
                .fullName("Nandu Ashok Chopade")
                .email("nanduchopade@gmail.com")
                .mobileNumber("8788779605")
                .password("nandu@123")
                .role(Role.ADMIN)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

         // Act

        User savedUser = userServiceImpl.addUserDetails(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(1L ,  savedUser.getId());
        assertEquals("nanduchopade", savedUser.getUsername());
        assertEquals("Nandu Ashok Chopade", savedUser.getFullName());
        assertEquals("nanduchopade@gmail.com", savedUser.getEmail());
        assertEquals("8788779605", savedUser.getMobileNumber());
        assertEquals("nandu@123", savedUser.getPassword());
        assertEquals(Role.ADMIN, savedUser.getRole());

        // Verify that the repository method was called once
        verify(userRepository, times(1)).save(any(User.class));

    }

     @Test
     void testGetUserDetails(){

        //Arrange
        User user = User.builder().id(2L).username("vikasnaik")
                .fullName("Vikas Naik")
                .email("vikasnaik@gmail.com").mobileNumber("8788779605").password("vikas@123").build();

        when(userRepository.findById(2l)).thenReturn(Optional.of(user));

         // Act
         User userResult =  userServiceImpl.getUserDetails(2L);

        //Assert
         assertEquals("vikasnaik" , userResult.getUsername());
         assertEquals("Vikas Naik" , userResult.getFullName());
         assertEquals("vikasnaik@gmail.com", userResult.getEmail());
         assertEquals("8788779605", userResult.getMobileNumber());
         assertEquals("vikas@123", userResult.getPassword());




     }


     @Test
     void testGetAllUser(){
        List<User> listOfUserDetails = List.of(

                //Arrange
                User.builder().id(1L).username("vikasnaik")
                        .fullName("Vikas Naik")
                        .email("vikasnaik@gmail.com")
                        .mobileNumber("8788779605")
                        .password("vikas@123")
                        .build(),


                User.builder().id(2L)
                .username("nanduchopade")
                .fullName("Nandu Ashok Chopade")
                .email("nanduchopade@gmail.com")
                .mobileNumber("8788779605")
                .password("nandu@123")
                .role(Role.ADMIN)
                .build()
        );

         when(userRepository.findAll()).thenReturn(listOfUserDetails);

         //Act
         List<User> listUser = userServiceImpl.getAllUserDetails();

         assertEquals(1L , listUser.get(0).getId());
         assertEquals("vikasnaik" , listUser.get(0).getUsername());
         assertEquals("Vikas Naik" , listUser.get(0).getFullName());
         assertEquals("vikasnaik@gmail.com", listUser.get(0).getEmail());
         assertEquals("8788779605", listUser.get(0).getMobileNumber());
         assertEquals("vikas@123", listUser.get(0).getPassword());

         assertEquals(2L ,  listUser.get(1).getId());
         assertEquals("nanduchopade", listUser.get(1).getUsername());
         assertEquals("Nandu Ashok Chopade", listUser.get(1).getFullName());
         assertEquals("nanduchopade@gmail.com", listUser.get(1).getEmail());
         assertEquals("8788779605", listUser.get(1).getMobileNumber());
         assertEquals("nandu@123", listUser.get(1).getPassword());
         assertEquals(Role.ADMIN, listUser.get(1).getRole());


     }

    @Test
    void testDeleteUser() {
        // Act
        userServiceImpl.deleteUserDetails(1L);

        // Assert
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindUserByUsername() {

      User user =  User.builder()
                .username("nanduchopade")
                .fullName("Nandu Ashok Chopade")
                .email("nanduchopade@gmail.com")
                .mobileNumber("8788779605")
                .password("nandu@123")
                .role(Role.ADMIN)
                .build();

        when(userRepository.findByUsername("nanduchopade")).thenReturn(Optional.of(user));


        User userResult = userServiceImpl.findUserByUsername("nanduchopade");

        assertEquals("nanduchopade" , userResult.getUsername());

    }


}
