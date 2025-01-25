package com.pointdafamilia.pointdafamilia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pointdafamilia.pointdafamilia.dtos.ErrorResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BebidaAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDto> handleComidaAlreadyRegistered(ComidaAlreadyRegisteredException exc, HttpServletRequest request){
        ErrorResponseDto response = new ErrorResponseDto(
            exc.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(BebidaAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDto> handleComidaNotFound(ComidaNotFoundException exc, HttpServletRequest request){
        ErrorResponseDto response = new ErrorResponseDto(
            exc.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exc, HttpServletRequest request){
        String message = String.format("Invalid value for '%s': '%s'", exc.getName(), exc.getValue());
        ErrorResponseDto response = new ErrorResponseDto(
            message,
            HttpStatus.BAD_REQUEST.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } 

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolation(ConstraintViolationException exc, HttpServletRequest request){
        ErrorResponseDto response = new ErrorResponseDto(
            exc.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }     

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadable(HttpMessageNotReadableException exc, HttpServletRequest request){
        ErrorResponseDto response = new ErrorResponseDto(
            exc.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }  

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException exc, HttpServletRequest request){
        ErrorResponseDto response = new ErrorResponseDto(
            exc.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exc, HttpServletRequest request){
        ErrorResponseDto response = new ErrorResponseDto(
            exc.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    } 

}