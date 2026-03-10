package com.geospatial.controller;

import com.geospatial.dto.ApiResponse;
import com.geospatial.dto.HotspotResponse;
import com.geospatial.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotspots")
@RequiredArgsConstructor
public class HotspotController {
    
    private final RegionService regionService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<HotspotResponse>>> getHotspots(
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<HotspotResponse> hotspots = regionService.getHotspots(limit);
        return ResponseEntity.ok(ApiResponse.success("Top hotspots retrieved successfully", hotspots));
    }
}
