package com.userservice.api.staff.mapper;

import com.userservice.api.staff.model.table.Staff;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface StaffMapper {

    // Read operations only (SELECT queries)
    @Select("SELECT id, user_id, department, qualification, designation, employee_id, work_schedule, address_id " +
            "FROM staffs WHERE id = #{id}")
    Staff selectStaffById(@Param("id") UUID id);
    
    @Select("SELECT id, user_id, department, qualification, designation, employee_id, work_schedule, address_id " +
            "FROM staffs WHERE user_id = #{userId}")
    Staff selectStaffByUserId(@Param("userId") UUID userId);
    
    @Select("SELECT id, user_id, department, qualification, designation, employee_id, work_schedule, address_id " +
            "FROM staffs WHERE employee_id = #{employeeId}")
    Staff selectStaffByEmployeeId(@Param("employeeId") String employeeId);
    
    @Select("SELECT id, user_id, department, qualification, designation, employee_id, work_schedule, address_id " +
            "FROM staffs ORDER BY id DESC")
    List<Staff> selectAllStaff();
    
    @Select("SELECT id, user_id, department, qualification, designation, employee_id, work_schedule, address_id " +
            "FROM staffs WHERE department = #{department} ORDER BY id DESC")
    List<Staff> selectStaffByDepartment(@Param("department") String department);
    
    @Select("SELECT id, user_id, department, qualification, designation, employee_id, work_schedule, address_id " +
            "FROM staffs WHERE designation = #{designation} ORDER BY id DESC")
    List<Staff> selectStaffByDesignation(@Param("designation") String designation);
    
    // Check existence
    @Select("SELECT EXISTS(SELECT 1 FROM staffs WHERE employee_id = #{employeeId})")
    boolean existsByEmployeeId(@Param("employeeId") String employeeId);
    
    @Select("SELECT EXISTS(SELECT 1 FROM staffs WHERE user_id = #{userId})")
    boolean existsByUserId(@Param("userId") UUID userId);
    
    // Count operations
    @Select("SELECT COUNT(*) FROM staffs")
    long countStaff();
    
    @Select("SELECT COUNT(*) FROM staffs WHERE department = #{department}")
    long countStaffByDepartment(@Param("department") String department);
} 