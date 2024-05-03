package com.example.lombard.api.controllers.admin;


import com.example.lombard.api.dto.ConfirmAdminDTO;
import com.example.lombard.api.facade.AdminFacade;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
  private final AdminFacade adminFacade;

  @GetMapping("/confirm")
  public String getConfirmPage(Model model){
    adminFacade.getConfirmPage(model);
    return "admin_confirm";
  }

  @PostMapping("/confirm")
  public String confirmAdmin(Model model,
                             @ModelAttribute("confirmAdminDTO") @Valid ConfirmAdminDTO confirmAdminDTO,
                             BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return "admin_confirm";
    }
    adminFacade.confirmAdmin(confirmAdminDTO.getConfirmKey(), model);
    if(model.getAttribute("errorConfirming")!=null){
      return "admin_confirm";
    }else{
      return "redirect:/admin";
    }
  }

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
