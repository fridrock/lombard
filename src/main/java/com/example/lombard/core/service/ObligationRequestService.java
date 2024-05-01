package com.example.lombard.core.service;


import com.example.lombard.core.exception.UserNotFoundException;
import com.example.lombard.core.model.Obligation;
import com.example.lombard.core.model.ObligationRequest;
import com.example.lombard.core.model.ObligationRequestStatus;
import com.example.lombard.core.repositories.ObligationRepository;
import com.example.lombard.core.repositories.ObligationRequestRepository;
import com.example.lombard.core.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@AllArgsConstructor
@Service
public class ObligationRequestService {
  private final ObligationRequestRepository obligationRequestRepository;
  private final UserRepository userRepository;
  private final ObligationRepository obligationRepository;
  public List<ObligationRequest> getObligationRequests(Long userId){
    //TODO make error handling
    var user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Нет такого пользователя"));
    return user.getRequests();
  }

  public void createObligationRequest(ObligationRequest obligationRequest, Long userId){
    //TODO make error handling
    var user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Нет такого пользователя"));
    obligationRequest.setUser(user);
    obligationRequestRepository.save(obligationRequest);
  }

  public List<ObligationRequest> getCreatedObligationRequests(){
    return obligationRequestRepository.findByStatus(ObligationRequestStatus.CREATED);
  }

  @Transactional
  public void setAcceptedStatus(Long obligationId){
    obligationRequestRepository.setStatus(ObligationRequestStatus.ACCEPTED, obligationId);
    var obligationRequest = obligationRequestRepository.findById(obligationId)
        .orElseThrow(()->new RuntimeException("No such obligationRequest"));
    var obligation = new Obligation();
    obligation.setUser(obligationRequest.getUser());
    obligation.setDescription(obligationRequest.getDescription());
    obligation.setDebt(obligationRequest.getOfferedPrice()*1.5);
    obligation.setPhotoName(obligationRequest.getPhotoName());
    //TODO remove after tested
    var calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-6);
    obligation.setStartDebt(new Date(calendar.getTimeInMillis()));
    obligationRepository.save(obligation);
  }

  @Transactional
  public void setDeclinedStatus(Long obligationId){
    obligationRequestRepository.setStatus(ObligationRequestStatus.DECLINED, obligationId);
  }

}
