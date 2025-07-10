package com.userservice.api.address.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    private java.util.UUID id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String landmark;

    private String addressType;

    private Double latitude;

    private Double longitude;
}
