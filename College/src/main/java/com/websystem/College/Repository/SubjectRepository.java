package com.websystem.College.Repository;

import com.websystem.College.models.Department;
import com.websystem.College.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByNameAndDepartment(String name, Department department);  // Check for duplicate subjects

}