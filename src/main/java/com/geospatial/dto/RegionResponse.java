package com.geospatial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionResponse {
    
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SignalResponse> signals;
    private Double totalScore;
}
