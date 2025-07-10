package com.userservice.api.student.repository;

import com.userservice.api.student.model.table.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    // This repository is used only for write operations (INSERT, UPDATE, DELETE)
    // Read operations are handled by MyBatis mappers with raw SQL queries
} 