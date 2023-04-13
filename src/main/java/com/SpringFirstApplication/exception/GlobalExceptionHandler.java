package com.SpringFirstApplication.exception;

import com.SpringFirstApplication.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){

        ErrorDetails er=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogNotFoundException(BlogApiException exceptions,WebRequest webRequest){
        ErrorDetails er1= new ErrorDetails(new Date(),exceptions.getMessage(),webRequest.getDescription(false));

  return  new ResponseEntity<>(er1,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception excep,WebRequest webRequest){

        ErrorDetails er2= new ErrorDetails(new Date(),excep.getMessage(),webRequest.getDescription(false));
    return new ResponseEntity<>(er2,HttpStatus.INTERNAL_SERVER_ERROR);

    }



}
