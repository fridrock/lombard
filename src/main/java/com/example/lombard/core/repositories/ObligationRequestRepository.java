package com.example.lombard.core.repositories;

import com.example.lombard.core.model.ObligationRequest;
import com.example.lombard.core.model.ObligationRequestStatus;
import com.example.lombard.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ObligationRequestRepository extends JpaRepository<ObligationRequest, Long> {
  List<ObligationRequest> findByStatus(ObligationRequestStatus status);
  @Modifying
  @Query("update ObligationRequest o set o.status=?1 where o.obligationRequestId=?2")
  void setStatus(ObligationRequestStatus status, Long obligationId);

}
