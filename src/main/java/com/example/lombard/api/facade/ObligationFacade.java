package com.example.lombard.api.facade;


import com.example.lombard.api.dto.MakeContributionDTO;
import com.example.lombard.core.exception.LessMoneyException;
import com.example.lombard.core.security.SecurityContextHolderUtils;
import com.example.lombard.core.service.ObligationService;
import com.example.lombard.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class ObligationFacade {
  private final ObligationService obligationService;
  private final UserService userService;
  public void getObligations(Model model){
    Long userId = SecurityContextHolderUtils.getUserId();
    model.addAttribute("obligations",obligationService.getObligations(userId));
  }
  public void setObligation(Model model, Long obligationId){
    model.addAttribute("obligation", obligationService.findById(obligationId));
  }
  public void getMakeContributionPage(Long obligationId, Model model){
    setObligation(model, obligationId);
    model.addAttribute("contributionDTO", new MakeContributionDTO(Double.valueOf(0)));
  }
  public void makeContribution(Long obligationId, MakeContributionDTO dto){
    Long userId = SecurityContextHolderUtils.getUserId();
    var user = userService.getUser(userId);
    var obligation = obligationService.findById(obligationId);
    final Double contributionSum = Math.min(dto.getContribution(), obligation.getDebt()-obligation.getContributed());
    if(user.getAccount()<dto.getContribution()){
      throw new LessMoneyException("Less money for making contribution");
    }else{
      user.setAccount(user.getAccount()-contributionSum);
      userService.saveUser(user);
    }
    obligationService.makeContribution(obligationId, contributionSum);
  }
}
