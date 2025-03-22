package com.rolebase.security.servicesimpl;

import com.rolebase.security.exception.UserNotFountException;
import com.rolebase.security.model.User;
import com.rolebase.security.repository.UserRepository;
import com.rolebase.security.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }


    /**
     * @param user
     * @return
     */
    @Override
    public User addUserDetails(User user) {
        return userRepository.save(user);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFountException("userNotFound with Id " + userId));
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User updateUserDetails(User user) {
        return userRepository.save(user);
    }

    /**
     * @param userId
     */
    @Override
    public void deleteUserDetails(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @param username
     * @return  User
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("user not fount with username " + username));
    }
}
