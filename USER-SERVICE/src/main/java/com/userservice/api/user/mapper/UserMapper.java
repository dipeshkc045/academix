package com.userservice.api.user.mapper;

import com.userservice.api.user.model.table.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {

    // Read operations only (SELECT queries)
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE id = #{id}")
    User selectUserById(@Param("id") UUID id);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE username = #{username}")
    User selectUserByUsername(@Param("username") String username);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE email = #{email}")
    User selectUserByEmail(@Param("email") String email);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE username = #{username} OR email = #{email}")
    User selectUserByUsernameOrEmail(@Param("username") String username, @Param("email") String email);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users ORDER BY created_at DESC")
    List<User> selectAllUsers();
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE role = #{role} ORDER BY created_at DESC")
    List<User> selectUsersByRole(@Param("role") User.UserRole role);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE status = #{status} ORDER BY created_at DESC")
    List<User> selectUsersByStatus(@Param("status") User.UserStatus status);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE role = #{role} AND status = #{status} ORDER BY created_at DESC")
    List<User> selectUsersByRoleAndStatus(@Param("role") User.UserRole role, @Param("status") User.UserStatus status);
    
    @Select("SELECT id, username, password, email, first_name, last_name, phone_number, role, status, created_at, updated_at, last_login " +
            "FROM users WHERE username ILIKE CONCAT('%', #{keyword}, '%') OR email ILIKE CONCAT('%', #{keyword}, '%') " +
            "OR first_name ILIKE CONCAT('%', #{keyword}, '%') OR last_name ILIKE CONCAT('%', #{keyword}, '%') " +
            "ORDER BY created_at DESC")
    List<User> searchUsers(@Param("keyword") String keyword);
    
    // Check existence
    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE username = #{username})")
    boolean existsByUsername(@Param("username") String username);
    
    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE email = #{email})")
    boolean existsByEmail(@Param("email") String email);
    
    // Count operations
    @Select("SELECT COUNT(*) FROM users")
    long countUsers();
    
    @Select("SELECT COUNT(*) FROM users WHERE role = #{role}")
    long countUsersByRole(@Param("role") User.UserRole role);
    
    @Select("SELECT COUNT(*) FROM users WHERE status = #{status}")
    long countUsersByStatus(@Param("status") User.UserStatus status);
} 