package com.shoestore.client.configs;

import com.shoestore.client.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;  // Inject success handler

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    System.out.println("Security Filter Chain");
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/page/Admin/**").hasRole("Admin")
                    .requestMatchers("/page/Customer/**").hasRole("Customer")
                    .anyRequest().permitAll()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .successHandler(customAuthenticationSuccessHandler)
                    .permitAll()
            )
            .logout(logout -> logout.permitAll());

    return http.build();
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailService)
            .passwordEncoder(passwordEncoder);
  }
}

