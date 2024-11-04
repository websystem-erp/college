package com.websystem.College.dto;

import java.util.List;

public class DepartmentDto {
    private Long id;                // Use Long or Integer based on your ID type
    private String name;            // Department name
    private List<SubjectDTO> subjects; // List of subjects in this department

    // Constructor
    public DepartmentDto(Long id, String name, List<SubjectDTO> subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }
}

