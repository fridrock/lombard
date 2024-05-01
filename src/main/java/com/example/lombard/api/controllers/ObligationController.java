package com.example.lombard.api.controllers;

import com.example.lombard.api.dto.MakeContributionDTO;
import com.example.lombard.api.facade.ObligationFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/obligations")
public class ObligationController {
  private final ObligationFacade obligationFacade;
  @GetMapping
  public String getObligations(Model model){
    obligationFacade.getObligations(model);
    return "obligations";
  }
  @GetMapping("/make-contribution/{obligationId}")
  public String getMakeContributionPage(@PathVariable("obligationId") Long obligationId, Model model){
    obligationFacade.getMakeContributionPage(obligationId, model);
    return "make_contribution";
  }
  @PatchMapping("/make-contribution/{obligationId}")
  public String makeContribution(@PathVariable("obligationId") Long obligationId,
                                 Model model,
                                 @ModelAttribute("contributionDTO") @Valid MakeContributionDTO dto,
                                 BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      obligationFacade.setObligation(model, obligationId);
      return "make_contribution";
    }
    obligationFacade.makeContribution(obligationId, dto);
    return "redirect:/obligations";
  }
}
