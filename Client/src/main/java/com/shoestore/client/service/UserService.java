package com.shoestore.client.service;

import com.shoestore.client.dto.request.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> getUserByName(String username);

    void save(UserDTO user);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();

    // Phương thức để xác thực người dùng qua email và password
    ResponseEntity<UserDTO> authenticateUser(UserDTO userDTO);

    public UserDTO getUserById(int id);
    public List<UserDTO> searchUsers(String name,String roleName,String status);
    public boolean delete(int id);
    public List<UserDTO> getUsersFromServer() ;
    public UserDTO saveUser(UserDTO userDTO);
    public UserDTO updateUser(int id, UserDTO updatedUser);
}
