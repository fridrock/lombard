package com.example.lombard.core.repositories;

import com.example.lombard.core.model.Obligation;
import com.example.lombard.core.model.ObligationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long>{
  @Modifying
  @Query("update Obligation o set o.contributed=?1 where o.obligationId=?2")
  void setContributed(Double newContributed, Long obligationId);
  @Query("select o from Obligation o where o.startDebt<=?1")
  List<Obligation> findBeforeDate(Date date);
}
