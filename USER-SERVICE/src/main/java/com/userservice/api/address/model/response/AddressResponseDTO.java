package com.userservice.api.address.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

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
