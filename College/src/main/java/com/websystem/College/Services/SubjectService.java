package com.websystem.College.Services;

import com.websystem.College.Repository.SubjectRepository;
import com.websystem.College.dto.SubjectDTO;
import com.websystem.College.models.Subject;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        if (subjectRepository.existsByNameAndDepartment(subject.getName(), subject.getDepartment())) {
            throw new EntityExistsException("Subject " + subject.getName() + " already exists in this department.");
        }
        if (subject.getFaculty() == null) {
            throw new IllegalArgumentException("A faculty member must be assigned to the subject.");
        }
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
