package com.example.lombard.api.controllers.admin;


import com.example.lombard.api.facade.AdminFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
  private final AdminFacade adminFacade;
  @GetMapping
  public String getMainPage(){
    return "admin";
  }
  @GetMapping("/update-products")
  public String updateProducts(){
    adminFacade.updateProducts();
    return "redirect:/admin";
  }
}
