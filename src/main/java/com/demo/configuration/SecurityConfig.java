package com.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain.
     * Defines which requests are authorized, login and logout behavior, and disables CSRF for simplicity.
     *
     * @param http the HttpSecurity to configure.
     * @return the configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // Disable CSRF for simplicity (not recommended for production environments).
                .authorizeRequests()
                .anyRequest().authenticated() // All requests require authentication.
                .and()
                .formLogin()
                .loginPage("/login") // Custom login page endpoint.
                .permitAll() // Allow everyone to access the login page.
                .and()
                .logout()
                .logoutUrl("/logout") // Logout URL endpoint.
                .logoutSuccessUrl("/login?logout") // Redirect to login page with a logout parameter after successful logout.
                .permitAll() // Allow everyone to access the logout endpoint.
                .and()
                .build();
    }

    /**
     * Configures an in-memory user for authentication.
     *
     * @return a UserDetailsService containing a single user.
     */
    @Bean
    public UserDetailsService getUser() {
        UserDetails user = User.builder()
                .username("prasuki") // Username for the user.
                .password(passwordEncoder().encode("raees")) // Password for the user, encoded using BCrypt.
                .roles("ADMIN") // Assign ADMIN role to the user.
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Configures a BCryptPasswordEncoder for encoding passwords.
     * BCrypt is a strong and secure encoding algorithm.
     *
     * @return a PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
