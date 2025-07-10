package com.userservice.api.user.repository;

import com.userservice.api.user.model.table.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // This repository is used only for write operations (INSERT, UPDATE, DELETE)
    // Read operations are handled by MyBatis mappers with raw SQL queries
} 