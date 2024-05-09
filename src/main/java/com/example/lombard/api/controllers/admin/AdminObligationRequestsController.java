package com.example.lombard.api.controllers.admin;


import com.example.lombard.api.facade.AdminFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/obligation-requests")
public class AdminObligationRequestsController {
  private final AdminFacade adminFacade;

  @GetMapping
  public String getObligationRequestsPage(Model model){
    adminFacade.getObligationRequestsPage(model);
    return "admin_obligation_requests";
  }
  @GetMapping("/accept/{obligationId}")
  public String acceptObligationRequest(@PathVariable("obligationId") Long obligationId){
    adminFacade.acceptObligationRequest(obligationId);
    return "redirect:/admin/obligation-requests";
  }
  @GetMapping("/decline/{obligationId}")
  public String declineObligationRequest(@PathVariable("obligationId") Long obligationId){
    adminFacade.declineObligationRequest(obligationId);
    return "redirect:/admin/obligation-requests";
  }
}
