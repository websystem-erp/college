package com.websystem.College.Controllers;

import com.websystem.College.Repository.CollegeEmployeeRepository;
import com.websystem.College.Services.EmployeeService;
import com.websystem.College.dto.CollegeEmployeeRequest;
import com.websystem.College.dto.LecturerDTO;
import com.websystem.College.models.CollegeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/college/employees")
@CrossOrigin
public class CollegeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder; // For password encryption
    private final CollegeEmployeeRepository collegeEmployeeRepository;

    @Autowired
    public CollegeController(EmployeeService employeeService, PasswordEncoder passwordEncoder, CollegeEmployeeRepository collegeEmployeeRepository) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
        this.collegeEmployeeRepository = collegeEmployeeRepository;
    }

    // Endpoint for adding a college employee
    @PostMapping("/add")
    public ResponseEntity<CollegeEmployee> addEmployee(@RequestBody CollegeEmployeeRequest request) {
        try {
            CollegeEmployee employee = new CollegeEmployee();
            employee.setName(request.getName());
            employee.setEmail(request.getEmail());
            employee.setRole(request.getRole());

            // Encode the password using BCrypt
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            employee.setPassword(encodedPassword); // Save the encoded password
            employee.setAdminId(request.getAdminId());
            employee.setCampusType(request.getCampusType());

            // Call the service method with departmentId
            CollegeEmployee newEmployee = employeeService.addEmployee(employee, request.getDepartmentId());
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<CollegeEmployee> getEmployeeByEmail(@RequestParam String email) {
        Optional<CollegeEmployee> employee = employeeService.findByEmail(email);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/viewemployees")
    public ResponseEntity<List<CollegeEmployee>> getEmployees(@RequestParam Long adminId) {
        List<CollegeEmployee> employees = employeeService.getEmployeesForAdmin(adminId);
        return ResponseEntity.ok(employees);
    }

    // Get employee by ID
    @GetMapping("/viewemployees/{id}")
    public ResponseEntity<CollegeEmployee> getEmployeeById(@PathVariable Long id) {
        Optional<CollegeEmployee> employee = employeeService.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update employee
    @PutMapping("/editemployees/{id}")
    public ResponseEntity<CollegeEmployee> updateEmployee(@PathVariable Long id, @RequestBody CollegeEmployee updatedEmployee) {
        Optional<CollegeEmployee> existingEmployee = employeeService.findById(id);
        if (existingEmployee.isPresent()) {
            CollegeEmployee employee = existingEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setRole(updatedEmployee.getRole());
            employee.setDepartment(updatedEmployee.getDepartment());

            // Encode the new password if provided
            if (updatedEmployee.getPassword() != null && !updatedEmployee.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(updatedEmployee.getPassword());
                employee.setPassword(encodedPassword); // Update password if provided
            }

            CollegeEmployee savedEmployee = employeeService.addCollegeEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return collegeEmployeeRepository.findById(id).map(employee -> {
            collegeEmployeeRepository.delete(employee);
            return ResponseEntity.ok("Employee deleted successfully.");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found."));
    }

    @GetMapping
    public List<LecturerDTO> getAllLecturers() {
        return employeeService.getAllLecturers();
    }

    @GetMapping("/{departmentId}")
    public List<CollegeEmployee> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        return employeeService.getEmployeesByDepartmentId(departmentId);
    }

}
