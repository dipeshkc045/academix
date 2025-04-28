package com.user_service.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponse<T> {

    private String status;
    private String message;
    private T data;
    private Integer code;
    private ErrorDetails error;


    public ServiceResponse(Status status, String message, T data, Integer code) {
        this.status = status.name();
        this.message = message;
        this.data = data;
        this.code = code != null ? code : 200;
        this.error = null;
    }


    public ServiceResponse(Status status, String message, Integer code, ErrorDetails error) {
        this.status = status.name();
        this.message = message;
        this.data = null;
        this.code = code != null ? code : 400;
        this.error = error;
    }


    public ServiceResponse(String message, ErrorDetails error) {
        this.status = Status.FAILURE.name();
        this.message = message;
        this.data = null;
        this.code = error != null ? error.getCode() : 400;
        this.error = error;
    }
}
