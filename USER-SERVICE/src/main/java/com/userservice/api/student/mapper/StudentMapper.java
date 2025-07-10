package com.userservice.api.student.mapper;

import com.userservice.api.student.model.table.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface StudentMapper {

    // Read operations only (SELECT queries)
    @Select("SELECT id, user_id, address, student_id, grade, section, parent_name, parent_phone " +
            "FROM students WHERE id = #{id}")
    Student selectStudentById(@Param("id") UUID id);
    
    @Select("SELECT id, user_id, address, student_id, grade, section, parent_name, parent_phone " +
            "FROM students WHERE user_id = #{userId}")
    Student selectStudentByUserId(@Param("userId") UUID userId);
    
    @Select("SELECT id, user_id, address, student_id, grade, section, parent_name, parent_phone " +
            "FROM students WHERE student_id = #{studentId}")
    Student selectStudentByStudentId(@Param("studentId") String studentId);
    
    @Select("SELECT id, user_id, address, student_id, grade, section, parent_name, parent_phone " +
            "FROM students ORDER BY id DESC")
    List<Student> selectAllStudents();
    
    @Select("SELECT id, user_id, address, student_id, grade, section, parent_name, parent_phone " +
            "FROM students WHERE grade = #{grade} ORDER BY id DESC")
    List<Student> selectStudentsByGrade(@Param("grade") String grade);
    
    @Select("SELECT id, user_id, address, student_id, grade, section, parent_name, parent_phone " +
            "FROM students WHERE section = #{section} ORDER BY id DESC")
    List<Student> selectStudentsBySection(@Param("section") String section);
    
    // Check existence
    @Select("SELECT EXISTS(SELECT 1 FROM students WHERE student_id = #{studentId})")
    boolean existsByStudentId(@Param("studentId") String studentId);
    
    @Select("SELECT EXISTS(SELECT 1 FROM students WHERE user_id = #{userId})")
    boolean existsByUserId(@Param("userId") UUID userId);
    
    // Count operations
    @Select("SELECT COUNT(*) FROM students")
    long countStudents();
    
    @Select("SELECT COUNT(*) FROM students WHERE grade = #{grade}")
    long countStudentsByGrade(@Param("grade") String grade);
} 