package com.example.lombard.api.controllers;


import com.example.lombard.api.facade.ObligationRequestFacade;
import com.example.lombard.core.model.ObligationRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Controller
@RequestMapping("/obligation-requests")
public class ObligationRequestController {
  private final ObligationRequestFacade obligationRequestFacade;

  @GetMapping
  public String getObligationRequests(Model model){
    obligationRequestFacade.getObligationRequests(model);
    return "obligation_requests";
  }

  @GetMapping("/create")
  public String getCreateObligationRequestPage(Model model){
    obligationRequestFacade.getCreateObligationRequestPage(model);
    return "create_obligation_request";
  }

  @PostMapping("/create")
  public String createObligationRequest(@RequestParam("image")MultipartFile image,
                                        @ModelAttribute("obligationRequest") @Valid ObligationRequest obligationRequest,
                                        BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return "create_obligation_request";
    }
    obligationRequestFacade.createObligationRequest(obligationRequest, image);
    return "redirect:/obligation-requests";
  }
}
