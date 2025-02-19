package com.auanjulio.hotelbookingapi.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Usuário não encontrado!");
    }
}
