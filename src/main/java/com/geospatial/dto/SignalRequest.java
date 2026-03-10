package com.geospatial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalRequest {
    
    @NotNull(message = "Region ID is required")
    private Long regionId;
    
    @NotBlank(message = "Indicator type is required")
    private String indicatorType;
    
    @NotNull(message = "Score is required")
    private Double score;
    
    private String description;
}
