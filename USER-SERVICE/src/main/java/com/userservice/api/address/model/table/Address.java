package com.userservice.api.address.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.userservice.api.staff.model.table.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String street;

    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String landmark;
    private String addressType; // e.g., "Home", "Office"
    private Double latitude;
    private Double longitude;

    @JsonBackReference  // Prevents infinite recursion in JSON
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Staff staff;
}