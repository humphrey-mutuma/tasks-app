package com.example.tasks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required!")
    private String title;
    @NotBlank(message = "Description is required!")
    private String  description;
    @NotBlank(message = "Risk level is required! (LOW, MEDIUM, HIGH)")
    private String riskLevel; // Low, Medium, High
    @NotBlank(message = "Due data is required!")
    private LocalDate dueDate;

//    Constructors
    public Task() {}

    public Task( String title, String description, String riskLevel, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.riskLevel = riskLevel;
        this.dueDate = dueDate;
    }

//    getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

}
