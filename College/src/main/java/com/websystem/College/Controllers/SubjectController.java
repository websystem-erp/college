package com.websystem.College.Controllers;

import com.websystem.College.Services.SubjectService;
import com.websystem.College.dto.SubjectDTO;
import com.websystem.College.models.Subject;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/college/departments/subjects")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    private final Logger logger = LoggerFactory.getLogger(SubjectController.class);
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        try {
            Subject savedSubject = subjectService.createSubject(subject);
            return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects().stream()
                .map(subject -> new SubjectDTO(subject.getId(), subject.getName()))
                .collect(Collectors.toList());

        // Log the response
        logger.info("Fetched {} subjects", subjects.size());

        return ResponseEntity.ok(subjects);
    }
}