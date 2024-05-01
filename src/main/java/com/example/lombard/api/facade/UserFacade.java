package com.example.lombard.api.facade;


import com.example.lombard.core.model.User;
import com.example.lombard.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class UserFacade {
  private final UserService userService;

  public void getCreateUserPage(Model model){
    var user = new User();
    model.addAttribute("user", user);
  }
  public void createUser(User user){
    userService.saveUser(user);
  }
}
