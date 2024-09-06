package com.example.uniclub06.exception;

import com.example.uniclub06.Reponse.BaseReponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {

    @ExceptionHandler(SaveFileException.class)
    public ResponseEntity<?> handleException(Exception e) {
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(500);
        baseReponse.setMessage(e.getMessage());
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }

    @ExceptionHandler(AuthenException.class)
    public ResponseEntity<?> handleException1(Exception e) {
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(200);
        baseReponse.setMessage(e.getMessage());
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }

}
