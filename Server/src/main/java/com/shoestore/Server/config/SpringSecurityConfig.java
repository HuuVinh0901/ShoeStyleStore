package com.shoestore.Server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
						.csrf(csrf -> csrf.disable())
						.authorizeRequests(authorize -> {
							authorize.requestMatchers("/admin/**").hasRole("Admin"); // Only Admin can access
							authorize.requestMatchers("/customer/**").hasRole("Customer"); // Only Customer can access
							authorize.requestMatchers("/**").permitAll(); // All other endpoints are public
						})
						.formLogin(form -> form
										.loginPage("/login")
										.defaultSuccessUrl("/home", true) // Default page after login
										.permitAll())
						.logout(logout -> logout
										.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
										.permitAll())
						.httpBasic(Customizer.withDefaults());

		return httpSecurity.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
						.passwordEncoder(passwordEncoder);
	}
}
