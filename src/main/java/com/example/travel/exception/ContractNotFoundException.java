package com.example.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContractNotFoundException extends RuntimeException {

    public ContractNotFoundException(String message) {
        super(message);
    }
}
