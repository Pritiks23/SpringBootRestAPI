package com.geospatial.controller;

import com.geospatial.dto.ApiResponse;
import com.geospatial.dto.RegionRequest;
import com.geospatial.dto.RegionResponse;
import com.geospatial.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionController {
    
    private final RegionService regionService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<RegionResponse>> createRegion(@Valid @RequestBody RegionRequest request) {
        RegionResponse response = regionService.createRegion(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Region created successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<RegionResponse>>> getAllRegions() {
        List<RegionResponse> regions = regionService.getAllRegions();
        return ResponseEntity.ok(ApiResponse.success(regions));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RegionResponse>> getRegionById(@PathVariable Long id) {
        RegionResponse response = regionService.getRegionById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RegionResponse>> updateRegion(
            @PathVariable Long id,
            @Valid @RequestBody RegionRequest request) {
        RegionResponse response = regionService.updateRegion(id, request);
        return ResponseEntity.ok(ApiResponse.success("Region updated successfully", response));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.ok(ApiResponse.success("Region deleted successfully", null));
    }
}
