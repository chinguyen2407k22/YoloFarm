package org.example.yolofarmbe.Exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("Username already exists");
    }
}
