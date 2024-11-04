package com.websystem.College.Services;

import com.websystem.College.Repository.CollegeEmployeeRepository;
import com.websystem.College.Repository.DepartmentRepository;
import com.websystem.College.dto.LecturerDTO;
import com.websystem.College.dto.SubjectDTO;
import com.websystem.College.models.CollegeEmployee;
import com.websystem.College.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    @Autowired
    private CollegeEmployeeRepository collegeEmployeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public Optional<CollegeEmployee> findByEmail(String email) {
        return collegeEmployeeRepository.findByEmail(email);
    }

    // Method to add college employee
    public CollegeEmployee addCollegeEmployee(CollegeEmployee employee) {
        return collegeEmployeeRepository.save(employee);
    }

    public List<CollegeEmployee> getEmployeesForAdmin(Long adminId) {
        return collegeEmployeeRepository.findByAdminId(adminId);
    }

    public Optional<CollegeEmployee> findById(Long id) {
        return collegeEmployeeRepository.findById(id);
    }

    public List<LecturerDTO> getAllLecturers() {
        return collegeEmployeeRepository.findAll().stream()
                .map(lecturer -> new LecturerDTO(
                        lecturer.getId(),
                        lecturer.getName(),
                        lecturer.getEmail(),
                        lecturer.getRole(),
                        lecturer.getCampusType(),
                        lecturer.getSubjects().stream()
                                .map(subject -> new SubjectDTO(subject.getId(), subject.getName()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public List<CollegeEmployee> getEmployeesByDepartmentId(Long departmentId) {
        return collegeEmployeeRepository.findByDepartmentId(departmentId);
    }

    public CollegeEmployee addEmployee(CollegeEmployee employee, Long departmentId) {
        // Fetch the department based on the provided departmentId
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Set the department to the employee
        employee.setDepartment(department);

        // Save the employee to the database
        return collegeEmployeeRepository.save(employee);
    }

}