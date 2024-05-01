package com.example.lombard.api.facade;


import com.example.lombard.core.service.ObligationRequestService;
import com.example.lombard.core.service.ObligationService;
import com.example.lombard.core.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class AdminFacade {
  private final ObligationRequestService obligationRequestService;
  private final ProductService productService;
  public void getObligationRequestsPage(Model model){
    var createdObligationRequests = obligationRequestService.getCreatedObligationRequests();
    model.addAttribute("obligationRequests", createdObligationRequests);
  }

  public void acceptObligationRequest(Long obligationId){
    obligationRequestService.setAcceptedStatus(obligationId);
  }
  public void declineObligationRequest(Long obligationId){
    obligationRequestService.setDeclinedStatus(obligationId);
  }
  public void updateProducts(){
    productService.updateProducts();
  }
}
