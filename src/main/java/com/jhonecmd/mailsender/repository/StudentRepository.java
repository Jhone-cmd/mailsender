package com.jhonecmd.mailsender.repository;

import com.jhonecmd.mailsender.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
}
