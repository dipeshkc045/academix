package com.userservice.api.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.userservice.api.student.model.table.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

} 