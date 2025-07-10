package com.userservice.api.teacher.model.table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private String department;
    private String qualification;
    private String address;
    
    // Additional teacher-specific fields
    @Column(name = "employee_id", unique = true)
    private String employeeId;
    
    private String subject;
    private String experience;
} 