package com.jhonecmd.mailsender.service;

import com.jhonecmd.mailsender.exception.StudentAlreadyExists;
import com.jhonecmd.mailsender.model.StudentEntity;
import com.jhonecmd.mailsender.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void execute(StudentEntity studentEntity) {
        studentRepository.findByEmail(studentEntity.getEmail()).ifPresent((student) -> {
            throw new StudentAlreadyExists();
        });
        studentRepository.save(studentEntity);
        return;
    }
}
