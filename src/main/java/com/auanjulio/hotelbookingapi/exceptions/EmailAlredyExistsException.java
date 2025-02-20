package com.auanjulio.hotelbookingapi.exceptions;

public class EmailAlredyExistsException extends RuntimeException {

    public EmailAlredyExistsException() {
        super("E-mail já cadastrado no sistema");
    }
}
