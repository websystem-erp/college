package com.websystem.College.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "college_employees")
public class CollegeEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;          // e.g., Lecturer, Assistant Professor, etc.
    private String password;
    private Long adminId;
    private String campusType;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getCampusType() {
        return campusType;
    }

    public void setCampusType(String campusType) {
        this.campusType = campusType;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
