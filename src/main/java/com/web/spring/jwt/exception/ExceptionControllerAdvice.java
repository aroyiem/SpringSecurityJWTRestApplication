/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.exception;

import com.web.spring.jwt.bean.ErrorResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author subho
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDenied(HttpServletRequest req, Exception e) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        error.setMessage("Please contact your administrator");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
