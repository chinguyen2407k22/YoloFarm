package org.example.yolofarmbe.Exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(){
        super("Password Incorrect!");
    }
}
