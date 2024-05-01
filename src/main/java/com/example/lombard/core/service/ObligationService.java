package com.example.lombard.core.service;


import com.example.lombard.core.exception.NotFoundException;
import com.example.lombard.core.exception.UserNotFoundException;
import com.example.lombard.core.model.Obligation;
import com.example.lombard.core.repositories.ObligationRepository;
import com.example.lombard.core.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ObligationService {
  private final UserRepository userRepository;
  private final ObligationRepository obligationRepository;
  private final PhotoService photoService;
  public List<Obligation> getObligations(Long userId){
    // TODO make error handling
    var user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Error getting user"));
    return user.getObligations();
  }

  public Obligation findById(Long obligationId){
    // TODO make error handling
    return obligationRepository.findById(obligationId).orElseThrow(()->new NotFoundException("Error getting obligation"));
  }
  @SneakyThrows
  @Transactional
  public void makeContribution(Long obligationId, Double contribution){
    // TODO make error handling
    var obligation =
        obligationRepository.findById(obligationId).orElseThrow(()->new NotFoundException("Error getting obligation"));
    var newContributed = obligation.getContributed()+contribution;
    if(newContributed>=obligation.getDebt()){
      photoService.deleteFileByName(obligation.getPhotoName());
      obligationRepository.deleteById(obligationId);
    }else{
      obligationRepository.setContributed(obligation.getContributed()+contribution, obligationId);
    }
  }
}
