package com.websystem.College.Repository;

import com.websystem.College.models.CollegeEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeEmployeeRepository extends JpaRepository<CollegeEmployee, Long> {
    // Additional query methods if needed
    Optional<CollegeEmployee> findByEmail(String email);

    List<CollegeEmployee> findByAdminId(Long adminId);

    List<CollegeEmployee> findByDepartmentId(Long departmentId);
}
