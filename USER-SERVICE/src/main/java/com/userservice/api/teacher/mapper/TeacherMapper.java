package com.userservice.api.teacher.mapper;

import com.userservice.api.teacher.model.table.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TeacherMapper {

    // Read operations only (SELECT queries)
    @Select("SELECT id, user_id, department, qualification, address, employee_id, subject, experience " +
            "FROM teachers WHERE id = #{id}")
    Teacher selectTeacherById(@Param("id") UUID id);
    
    @Select("SELECT id, user_id, department, qualification, address, employee_id, subject, experience " +
            "FROM teachers WHERE user_id = #{userId}")
    Teacher selectTeacherByUserId(@Param("userId") UUID userId);
    
    @Select("SELECT id, user_id, department, qualification, address, employee_id, subject, experience " +
            "FROM teachers WHERE employee_id = #{employeeId}")
    Teacher selectTeacherByEmployeeId(@Param("employeeId") String employeeId);
    
    @Select("SELECT id, user_id, department, qualification, address, employee_id, subject, experience " +
            "FROM teachers ORDER BY id DESC")
    List<Teacher> selectAllTeachers();
    
    @Select("SELECT id, user_id, department, qualification, address, employee_id, subject, experience " +
            "FROM teachers WHERE department = #{department} ORDER BY id DESC")
    List<Teacher> selectTeachersByDepartment(@Param("department") String department);
    
    @Select("SELECT id, user_id, department, qualification, address, employee_id, subject, experience " +
            "FROM teachers WHERE subject = #{subject} ORDER BY id DESC")
    List<Teacher> selectTeachersBySubject(@Param("subject") String subject);
    
    // Check existence
    @Select("SELECT EXISTS(SELECT 1 FROM teachers WHERE employee_id = #{employeeId})")
    boolean existsByEmployeeId(@Param("employeeId") String employeeId);
    
    @Select("SELECT EXISTS(SELECT 1 FROM teachers WHERE user_id = #{userId})")
    boolean existsByUserId(@Param("userId") UUID userId);
    
    // Count operations
    @Select("SELECT COUNT(*) FROM teachers")
    long countTeachers();
    
    @Select("SELECT COUNT(*) FROM teachers WHERE department = #{department}")
    long countTeachersByDepartment(@Param("department") String department);
} 