package com.four19ja.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler{

//    @ExceptionHandler(value
//            = { IllegalArgumentException.class, IllegalStateException.class, MissingServletRequestParameterException.class })
//    protected ResponseEntity<Object> handleConflict(
//            RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }

    @ExceptionHandler(value
            = { DataIntegrityViolationException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request, DataIntegrityViolationException e) {
        String bodyOfResponse = e.getMostSpecificCause().getMessage();
//        ResponseEntity<Object> res =  new ResponseEntity<Object>();
        System.out.println(ex);
        System.out.println(request);
        System.out.println(e.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.FORBIDDEN);

//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Handles missing parameters in controller methods.
     *
     * @param e the exception caught
     * @return an error message
     */
    @ExceptionHandler
            (MissingServletRequestParameterException.class)
    public @ResponseBody
    String handleMissingParams(MissingServletRequestParameterException e) {
        String name = e.getParameterName();
        return name + " parameter is missing";
        // Actual exception handling
    }

    /**
     * Handles type mismatches in controller methods.
     *
     * @param e the exception caught
     * @return an error message
     */
    @ExceptionHandler(TypeMismatchException.class)
    public @ResponseBody
    String handleTypeMismatchException(TypeMismatchException e) {
        String input = e.getValue().toString();
        String type = e.getRequiredType().toString();
        return "Incorrect type for input: " + input + ". Type should be: " + type;
    }
}