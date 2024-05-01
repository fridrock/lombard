package com.example.lombard.api.controllers;


import com.example.lombard.api.facade.UserFacade;
import com.example.lombard.core.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
  private final UserFacade userFacade;

  @GetMapping("/create")
  public String getCreateUserPage(Model model) {
    userFacade.getCreateUserPage(model);
    return "create_user";
  }


  @PostMapping("/create")
  public String getCreateUserPage(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "create_user";
    }
    userFacade.createUser(user);
    return "redirect:/";
  }
}
