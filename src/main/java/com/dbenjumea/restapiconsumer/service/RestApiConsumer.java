package com.dbenjumea.restapiconsumer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestApiConsumer<T> {

    HttpHeaders createHttpHeaders();
    HttpEntity<T[]> createHttpEntity(HttpHeaders headers);
    List<T> getList();
    void postNew(T object);
    boolean checkStatus(ResponseEntity<T[]> response);
    HttpStatus getStatus(ResponseEntity<T[]> response);
}
