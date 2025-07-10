package com.userservice.api.teacher.repository;

import com.userservice.api.teacher.model.table.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    // This repository is used only for write operations (INSERT, UPDATE, DELETE)
    // Read operations are handled by MyBatis mappers with raw SQL queries
}
