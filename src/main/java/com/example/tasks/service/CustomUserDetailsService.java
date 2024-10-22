package com.example.tasks.service;

import com.example.tasks.model.User;
import com.example.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> userOptional = userRepository.findByUsername(username); // Modify this method as per your repository
        // Check if the user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();  // Extract the User object from Optional

            // Convert Set<String> to String[]
            String[] rolesArray = user.getRoles().toArray(new String[0]);

            // Return a UserDetails object
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())  // Accessing User entity's methods
                    .password(user.getPassword())  // Accessing User entity's methods
                    .roles(rolesArray)        // Assuming 'roles' is a String array in User entity
                    .build();
        } else {
            // If user not found, throw an exception
            throw new UsernameNotFoundException("User not found");
        }
}}
