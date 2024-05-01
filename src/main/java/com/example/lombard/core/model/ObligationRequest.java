package com.example.lombard.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "obligation_request")
public class ObligationRequest {
  @Id
  @Column(name = "obligation_request_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long obligationRequestId;
  @NotBlank
  private String description;
  @Column(name = "offered_price")
  @Min(1)
  private Double offeredPrice;
  @Enumerated(EnumType.STRING)
  private ObligationRequestStatus status = ObligationRequestStatus.CREATED;
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;
  @Column(name = "photo_name")
  private String photoName;
}
