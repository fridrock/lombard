package com.example.lombard.api.facade;


import com.example.lombard.api.dto.ConfirmAdminDTO;
import com.example.lombard.core.model.Role;
import com.example.lombard.core.security.SecurityContextHolderUtils;
import com.example.lombard.core.service.ObligationRequestService;
import com.example.lombard.core.service.ProductService;
import com.example.lombard.core.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
@PropertySource("classpath:application.yml")
public class AdminFacade {
  private final ObligationRequestService obligationRequestService;
  private final UserService userService;
  private final ProductService productService;
  private final String secret = "secretadmin";

  public void getConfirmPage(Model model) {
    model.addAttribute("confirmAdminDTO", new ConfirmAdminDTO());
  }

  public void getObligationRequestsPage(Model model) {
    var createdObligationRequests = obligationRequestService.getCreatedObligationRequests();
    model.addAttribute("obligationRequests", createdObligationRequests);
  }

  public void confirmAdmin(String confirmKey, Model model) {
    Long userId = SecurityContextHolderUtils.getUserId();
    if (confirmKey.equals(secret)) {
      var user = userService.getUser(userId);
      user.setRole(Role.ADMIN);
      userService.saveUser(user);
      SecurityContextHolderUtils.updateRole(Role.ADMIN);
    } else {
      model.addAttribute("errorConfirming", "Wrong confirm code");
    }
  }

  public void acceptObligationRequest(Long obligationId) {
    obligationRequestService.setAcceptedStatus(obligationId);
  }

  public void declineObligationRequest(Long obligationId) {
    obligationRequestService.setDeclinedStatus(obligationId);
  }

  public void updateProducts() {
    productService.updateProducts();
  }
}
