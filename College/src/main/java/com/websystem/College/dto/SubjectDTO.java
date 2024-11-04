package com.websystem.College.dto;


public class SubjectDTO {
    private Long id;
    private String name;
    private Long departmentId; // Only the ID of the department
    private Long facultyId; // Only the ID of the faculty

    public SubjectDTO(Long id, String name) {
        this.id = id;
        this.name=name;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}