package com.websystem.College.Controllers;

import com.websystem.College.Services.DepartmentService;
import com.websystem.College.dto.DepartmentDto;
import com.websystem.College.dto.SubjectDTO;
import com.websystem.College.models.Department;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/college/departments")
@CrossOrigin  // Adjust this URL to your React app URL
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        return departmentService.getAllDepartments().stream()
                .map(department -> new DepartmentDto(department.getId(), department.getName(),
                        department.getSubjects().stream()
                                .map(subject -> new SubjectDTO(subject.getId(), subject.getName()))
                                .collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/by-admin/{adminid}")
    public ResponseEntity<List<Department>> getDepartmentsByAdmin(@PathVariable Long adminid) {
        try {
            List<Department> departments = departmentService.getDepartmentsByAdmin(adminid);
            return ResponseEntity.ok(departments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
