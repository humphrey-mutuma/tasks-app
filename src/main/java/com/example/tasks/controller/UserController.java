package com.example.tasks.controller;

import com.example.tasks.dto.LoginRequest;
import com.example.tasks.model.User;
import com.example.tasks.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
//    register use
    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }
//    login user
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        boolean isAuthenticated = userService.login(email, password);

        if (isAuthenticated ) {
            return ResponseEntity.ok("Login Successful");
        }else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
//

}
