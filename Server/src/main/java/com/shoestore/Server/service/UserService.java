package com.shoestore.Server.service;

import com.shoestore.Server.dto.UserDTO;
import com.shoestore.Server.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUserByName(String username);
    public void save(User user);

    public User findByEmail(String email);

    public List<User> findAll();

    public User addUser(User user);
    public void deleteUser(int userID);
    public List<User> getAllUsers();
    public User findUserById(int id);
    public List<User> searchUsers(String status, String tenRole, String username);
    public User updateUser(int id, UserDTO user);
    public void saveUser(User user);
    public User findById(int id);
    public User getUserById(int id);
}
