package com.websystem.College.Services;

import com.websystem.College.Repository.DepartmentRepository;
import com.websystem.College.models.Department;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new EntityExistsException("Department with name " + department.getName() + " already exists.");
        }
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> getDepartmentsByAdmin(Long adminid) {
        return departmentRepository.findByAdminId(adminid);
    }
}