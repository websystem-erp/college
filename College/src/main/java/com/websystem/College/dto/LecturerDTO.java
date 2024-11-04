package com.websystem.College.dto;

import java.util.List;

public class LecturerDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String campusType;
    private List<SubjectDTO> subjects;

    public LecturerDTO(Long id, String name, String email, String role, String campusType, List<SubjectDTO> subjects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.campusType = campusType;
        this.subjects = subjects;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getCampusType() {
        return campusType;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }
}
