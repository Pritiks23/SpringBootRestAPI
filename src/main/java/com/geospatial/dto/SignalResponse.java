package com.geospatial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalResponse {
    
    private Long id;
    private Long regionId;
    private String regionName;
    private String indicatorType;
    private Double score;
    private String description;
    private LocalDateTime createdAt;
}
