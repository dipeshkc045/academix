package com.userservice.api.staff.repository;

import com.userservice.api.staff.model.table.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {
    // This repository is used only for write operations (INSERT, UPDATE, DELETE)
    // Read operations are handled by MyBatis mappers with raw SQL queries
}
