package com.user_service.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public  class ErrorDetails {
    private Integer code;
    private String description;

    public ErrorDetails(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}