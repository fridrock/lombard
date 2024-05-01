package com.example.lombard.core.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "obligations")
public class Obligation {
  @Id
  @Column(name = "obligation_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long obligationId;
  private String description;
  private Date startDebt = new Date(Calendar.getInstance().getTimeInMillis());
  private Double debt;
  private Double contributed = 0.0;
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;
  @Column(name = "photo_name")
  private String photoName;
}
