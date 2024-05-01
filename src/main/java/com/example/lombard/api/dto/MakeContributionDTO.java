package com.example.lombard.api.dto;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MakeContributionDTO {
  @Min(1)
  private Double contribution;
}
