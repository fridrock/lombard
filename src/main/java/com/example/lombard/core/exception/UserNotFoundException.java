package com.example.lombard.core.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String message){
    super(message);
  }
}
