package com.example.demo.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(MyExceptionHandler.class.getName());

    @ExceptionHandler({RepeatitionException.class})
    public ResponseEntity<CustomErrorResponce> exsitingOfClient() {
        LOGGER.error("Such user is present");
        CustomErrorResponce errors = new CustomErrorResponce(LocalDateTime.now(), 500, "Such user is exist");
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.valueOf(errors.getStatus()));
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<CustomErrorResponce> absentOfClient() {
        LOGGER.error("User Not found");
        CustomErrorResponce errors = new CustomErrorResponce(LocalDateTime.now(), 500, "User Not found");
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.valueOf(errors.getStatus()));
    }
}
