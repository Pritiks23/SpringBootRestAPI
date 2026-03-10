package com.geospatial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotspotResponse {
    
    private Long regionId;
    private String regionName;
    private Double latitude;
    private Double longitude;
    private String description;
    private Double totalScore;
    private Integer signalCount;
}
