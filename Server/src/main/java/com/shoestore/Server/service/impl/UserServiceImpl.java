package com.shoestore.Server.service.impl;

import com.shoestore.Server.dto.UserDTO;
import com.shoestore.Server.entities.Role;
import com.shoestore.Server.entities.User;
import com.shoestore.Server.repositories.RoleRepository;
import com.shoestore.Server.repositories.UserRepository;
import com.shoestore.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        Role role = roleRepository.findByName("Customer"); // Mặc định là "Customer"
        if (user.getRole() != null && "Admin".equals(user.getRole().getName())) {
            // Nếu người dùng yêu cầu là Admin, thì tìm vai trò "Admin"
            role = roleRepository.findByName("Admin");
            if (role == null) {
                role = new Role();
                role.setName("Admin");
                roleRepository.save(role);
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

//    @Override
//    public User findById(int id) {
//        return userRepository.findById(id).orElse(null);
//    }

    @Override
    public List<User> getUserByName(String username) {
        return List.of();
    }



    @Override
    public User addUser(User user) {
        User user1 = new User();

        // Set các trường cơ bản
        user1.setName(user.getName());
        user1.setCI(user.getCI());
        user1.setEmail(user.getEmail());
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(String.valueOf(user.getPhoneNumber()));
        user1.setStatus(user.getStatus());
        user1.setAddresses(user.getAddresses());
        // Tìm Role dựa trên roleID từ userDTO
        // Tìm Role dựa trên roleID từ userDTO
        if (user.getRole() != null && user.getRole().getRoleID() != 0) {
            Role role = roleRepository.findById(user.getRole().getRoleID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Role với ID: " + user.getRole().getRoleID()));
            user1.setRole(role);
        } else {
            throw new RuntimeException("Role không được xác định.");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int userID) {
        // Kiểm tra xem người dùng có tồn tại không
        if (!userRepository.existsById(userID)) {
            throw new IllegalArgumentException("User with ID " + userID + " does not exist.");
        }
        userRepository.deleteById(userID);
    }
    @Override
    public List<User> searchUsers(String name, String roleName, String status) {
        name = (name != null && !name.isEmpty()) ? name : null;
        roleName = (roleName != null && !roleName.isEmpty()) ? roleName : null;
        status = (status != null && !status.isEmpty()) ? status : null;

        return userRepository.searchUsers(name, roleName, status);
    }

    @Override
    public User updateUser(int id, UserDTO updatedUser) {
        // Tìm người dùng cũ từ database
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            return null; // Hoặc ném ra exception nếu không tìm thấy
        }

        // Cập nhật các trường cần thiết từ đối tượng updatedUser vào existingUser
        existingUser.setName(updatedUser.getName());
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setCI(updatedUser.getCi());

        // Cập nhật vai trò từ UserDTO
        if (updatedUser.getRole() != null) {
            Role role = roleRepository.findById(updatedUser.getRole().getRoleID()).orElse(null);
            if (role != null) {
                existingUser.setRole(role);
            }
        }

        return userRepository.save(existingUser);
    }



    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
