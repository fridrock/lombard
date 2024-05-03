package com.example.lombard.api.facade;


import com.example.lombard.core.model.User;
import com.example.lombard.core.service.UserDetailsServiceImpl;
import com.example.lombard.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.net.PasswordAuthentication;

@AllArgsConstructor
@Component
public class UserFacade {
  private final UserService userService;
  private final UserDetailsServiceImpl userDetailsService;
  private final PasswordEncoder passwordEncoder;
  public void setEmptyUser(Model model){
    var user = new User();
    model.addAttribute("user", user);
  }
  public void createUser(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);
  }
}
