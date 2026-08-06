package com.jhonecmd.mailsender.service;

import com.jhonecmd.mailsender.exception.StudentAlreadyExists;
import com.jhonecmd.mailsender.mail.StudentComponent;
import com.jhonecmd.mailsender.model.StudentEntity;
import com.jhonecmd.mailsender.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentComponent studentComponent;

    public void execute(StudentEntity studentEntity) {
        studentRepository.findByEmail(studentEntity.getEmail()).ifPresent((student) -> {
            throw new StudentAlreadyExists();
        });

        studentComponent.sendSimpleWelcomeEmail(studentEntity);
        studentRepository.save(studentEntity);
        return;
    }
}
