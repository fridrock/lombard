package com.example.lombard.api.exception;

import com.example.lombard.core.exception.LessMoneyException;
import com.example.lombard.core.exception.NotFoundException;
import com.example.lombard.core.exception.UserNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CustomControllerAdvice {
  @ExceptionHandler(AccessDeniedException.class)
  public String handleError(){
    return "redirect:/admin/confirm";
  }
  @ExceptionHandler(NotFoundException.class)
  public String notFoundPage(){
    return "errors/not_found";
  }
  @ExceptionHandler(LessMoneyException.class)
  public String lessMoney(){
    return "redirect:/account";
  }
  @ExceptionHandler(UserNotFoundException.class)
  public String userNotFound(){
    return "redirect:users/create";
  }
  @ExceptionHandler(IOException.class)
  public String errorsWithIo(){
    return "index";
  }

}
