package com.example.tasks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required!")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

//    constructors
    public User() {}
    public User(Long id, String username, String email, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    //    getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Username is required!") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required!") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Email is required!") @Email(message = "Invalid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required!") @Email(message = "Invalid email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required!") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required!") String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
