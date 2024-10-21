package com.example.tasks.service;

import com.example.tasks.model.User;
import com.example.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // Create an instance of BCryptPasswordEncoder
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
     public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

//    register user
    public User registerUser(User user) {
      // Hash user password
       user.setPassword(passwordEncoder.encode(user.getPassword()));

        // assign default role
        user.setRoles(new HashSet<>(Set.of("USER")));
        return userRepository.save(user);
    }
//    login
    public boolean login(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
          // hash passwords ...
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
//    find user by username
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
//    find user by email
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
//    update user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setRoles(updatedUser.getRoles());
//                    hash pass
                    if (!updatedUser.getPassword().isEmpty() && updatedUser.getPassword() != null) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                     return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found with id" + id));
    }

//    delete user
    public boolean deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
