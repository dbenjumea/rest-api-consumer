package com.dbenjumea.restapiconsumer.exception;

import org.springframework.http.HttpStatus;

public class BeerRestApiServiceException extends Error{

    public BeerRestApiServiceException(String message) {
        super(message);
    }

}
