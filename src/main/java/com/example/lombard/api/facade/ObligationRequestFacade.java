package com.example.lombard.api.facade;


import com.example.lombard.core.exception.SavingPhotoException;
import com.example.lombard.core.model.ObligationRequest;
import com.example.lombard.core.service.ObligationRequestService;
import com.example.lombard.core.service.PhotoService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class ObligationRequestFacade {
  private final ObligationRequestService obligationRequestService;
  private final PhotoService photoService;
  public void getObligationRequests(Model model){
    //TODO remake to get user id from authentication
    Long userId = Long.valueOf(1);
    List<ObligationRequest> obligationRequests = obligationRequestService.getObligationRequests(userId);
    model.addAttribute("obligationRequests", obligationRequests);
  }
  public void getCreateObligationRequestPage(Model model){
    model.addAttribute("obligationRequest", new ObligationRequest());
  }
  @SneakyThrows
  public void createObligationRequest(ObligationRequest obligationRequest, MultipartFile file){
    //TODO: remake to get user id from authentication
    Long userId = Long.valueOf(1);
    var photoName = photoService.savePhoto(file);
    obligationRequest.setPhotoName(photoName);
    obligationRequestService.createObligationRequest(obligationRequest, userId);
  }
}
