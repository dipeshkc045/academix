package com.userservice.api.staff.model.table;

import com.userservice.api.address.model.table.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "staffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private String department;
    private String qualification;
    private String designation;

    @OneToOne(fetch = FetchType.LAZY)  // Recommended for performance
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "work_schedule")
    private String workSchedule;
}