package com.shoestore.client.security;

import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDTO user = userService.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		String roleName = (user.getRole() != null) ? "ROLE_" + user.getRole().getName() : "ROLE_Customer";
		return new org.springframework.security.core.userdetails.User(
						user.getEmail(),
						user.getPassword(),
						Collections.singletonList(new SimpleGrantedAuthority(roleName))
		);
	}


	public boolean checkPassword(String inputPassword, String storedPassword) {
		return passwordEncoder.matches(inputPassword, storedPassword);
	}
}
