package com.rolebase.security.services;

import com.rolebase.security.model.User;

import java.util.List;

public interface UserService {

    public User addUserDetails(User user);
    public User getUserDetails(Long userId);
    public List<User> getAllUserDetails();
    public User updateUserDetails(User user);
    public void deleteUserDetails(Long userIdl);
    public User findUserByUsername(String username);

}
