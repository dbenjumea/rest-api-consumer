package com.dbenjumea.restapiconsumer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestApiConsumer<T> {

    HttpHeaders createHttpHeaders();
    HttpEntity<T[]> createHttpEntityGetList(HttpHeaders headers);
    HttpEntity<T> createHttpEntityPost(T body, HttpHeaders headers);
    List<T> getListWithHeaders();
    List<T> getList();
    void postNewWithHeaders(T object);
    void postNew(T object);
    boolean checkStatus(ResponseEntity<T[]> response);
    HttpStatus getStatus(ResponseEntity<T[]> response);
}
