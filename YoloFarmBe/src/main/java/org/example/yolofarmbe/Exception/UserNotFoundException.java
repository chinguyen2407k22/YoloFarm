package org.example.yolofarmbe.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Username does not exists!");
    }
}
