package com.jhonecmd.mailsender.controller;

import com.jhonecmd.mailsender.dto.StudentDTO;
import com.jhonecmd.mailsender.exception.StudentAlreadyExists;
import com.jhonecmd.mailsender.model.StudentEntity;
import com.jhonecmd.mailsender.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public ResponseEntity<Object> save(@Valid @RequestBody StudentDTO studentDTO) {
        try {
            StudentEntity student = StudentEntity.builder().name(studentDTO.getName())
                    .email(studentDTO.getEmail()).password(studentDTO.getPassword()).build();

            studentService.execute(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("id: " + student.getId());

        } catch (StudentAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
