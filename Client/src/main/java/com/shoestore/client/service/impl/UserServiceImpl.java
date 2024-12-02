package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.dto.response.UserResponseDTO;
import com.shoestore.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private static final String SERVER_BASE_URL = "http://localhost:8080/";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserDTO> authenticateUser(UserDTO userDTO) {
        // Gửi yêu cầu POST đến server để xác thực người dùng
        String apiUrl = SERVER_BASE_URL + "auth/login?email=" + userDTO.getEmail() + "&password=" + userDTO.getPassword();
        return restTemplate.postForEntity(apiUrl, null, UserDTO.class);
    }

    public void save(UserDTO userDTO) {
        // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
        String emailCheckUrl = SERVER_BASE_URL + "auth/findByEmail?email=" + userDTO.getEmail();
        ResponseEntity<UserDTO> response = restTemplate.exchange(emailCheckUrl, HttpMethod.GET, null, UserDTO.class);
        if (response.getBody() == null) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);
            String apiUrl = SERVER_BASE_URL + "auth/register";
            restTemplate.postForEntity(apiUrl, userDTO, Void.class);
        } else {
            // Nếu email đã tồn tại, trả về thông báo lỗi
            throw new IllegalArgumentException("Email already exists");
        }
    }

    @Override
    public UserDTO findByEmail(String email) {
        String url = SERVER_BASE_URL + "auth/findByEmail?email=" + email;
        ResponseEntity<UserDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, UserDTO.class);
        return response.getBody();
    }

    @Override
    public List<UserDTO> findAll() {
        String url = SERVER_BASE_URL + "findAll";
        UserDTO[] userArray = restTemplate.getForObject(url, UserDTO[].class);
        return List.of(userArray);
    }

    @Override
    public List<UserDTO> getUserByName(String username) {
        String url = SERVER_BASE_URL + "getUserByName?username=" + username;
        UserDTO[] userArray = restTemplate.getForObject(url, UserDTO[].class);
        return List.of(userArray);
    }



    // này của phương
    @Override
    public UserDTO getUserById(int id) {
        String apiUrl = "http://localhost:8080/auth/users/" + id; // URL API để lấy thông tin người dùng theo ID

        try {
            // Gửi yêu cầu GET tới API và nhận lại đối tượng UserDTO
            ResponseEntity<UserDTO> response = restTemplate.exchange(
                    apiUrl, HttpMethod.GET, null, UserDTO.class
            );

            // Kiểm tra phản hồi từ server
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody(); // Trả về đối tượng UserDTO
            } else {
                throw new RuntimeException("Lỗi khi lấy thông tin người dùng: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin người dùng: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserDTO> searchUsers(String name, String roleName, String status) {
        return List.of();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

//    @Override
//    public List<UserDTO> getUsersFromServer() {
//        String apiUrl = "http://localhost:8080/auth/users/list";
//
//        ResponseEntity<UserResponseDTO> response = restTemplate.exchange(
//                apiUrl, HttpMethod.GET, null,
//                UserResponseDTO.class
//        );
//
//        List<UserDTO> users = response.getBody().getUsers();
//        return users;
//    }

    @Override
    public List<UserDTO> getUsersFromServer() {
        String apiUrl = "http://localhost:8080/auth/users/list";

        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDTO>>() {}
        );

        return response.getBody();
    }


    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        String apiUrl = "http://localhost:8080/auth/users/add"; // URL API để thêm user

        try {
            // Gửi yêu cầu POST tới API
            ResponseEntity<UserDTO> response = restTemplate.postForEntity(
                    apiUrl, userDTO, UserDTO.class
            );

            // Kiểm tra phản hồi từ server
            if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
                return response.getBody(); // Trả về đối tượng UserDTO đã lưu
            } else {
                throw new RuntimeException("Lỗi khi lưu người dùng: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu người dùng: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDTO updateUser(int id, UserDTO updatedUser) {
        String apiUrl = SERVER_BASE_URL + "auth/users/" + id; // URL API để cập nhật người dùng

        try {
            // Gửi yêu cầu PUT tới API
            ResponseEntity<UserDTO> response = restTemplate.exchange(
                    apiUrl, HttpMethod.PUT,
                    new HttpEntity<>(updatedUser), // Body gửi lên server
                    UserDTO.class
            );

            // Kiểm tra phản hồi từ server
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody(); // Trả về đối tượng UserDTO sau khi cập nhật
            } else {
                throw new RuntimeException("Lỗi khi cập nhật người dùng: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật người dùng: " + e.getMessage(), e);
        }
    }

}
