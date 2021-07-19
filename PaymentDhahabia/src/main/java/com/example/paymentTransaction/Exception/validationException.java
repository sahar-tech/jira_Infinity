package com.example.paymentTransaction.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class validationException extends RuntimeException{

    public validationException(String typException){

        super(typException);

    }

}
