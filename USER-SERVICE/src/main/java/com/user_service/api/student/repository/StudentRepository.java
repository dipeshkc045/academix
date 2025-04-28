package com.user_service.api.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user_service.api.student.model.table.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

} 