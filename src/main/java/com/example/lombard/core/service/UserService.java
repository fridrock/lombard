package com.example.lombard.core.service;

import com.example.lombard.core.exception.UserNotFoundException;
import com.example.lombard.core.model.User;
import com.example.lombard.core.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  public void saveUser(User user){
    userRepository.save(user);
  }
  public User getUser(Long userId){
    return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No such user"));
  }
  public User findUserByUsername(String username){
    return userRepository.findByUserName(username);
  }
}
