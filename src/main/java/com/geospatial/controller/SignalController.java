package com.geospatial.controller;

import com.geospatial.dto.ApiResponse;
import com.geospatial.dto.SignalRequest;
import com.geospatial.dto.SignalResponse;
import com.geospatial.service.SignalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signals")
@RequiredArgsConstructor
public class SignalController {
    
    private final SignalService signalService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<SignalResponse>> createSignal(@Valid @RequestBody SignalRequest request) {
        SignalResponse response = signalService.createSignal(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Signal created successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<SignalResponse>>> getAllSignals(
            @RequestParam(required = false) Long regionId) {
        List<SignalResponse> signals;
        if (regionId != null) {
            signals = signalService.getSignalsByRegionId(regionId);
        } else {
            signals = signalService.getAllSignals();
        }
        return ResponseEntity.ok(ApiResponse.success(signals));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SignalResponse>> getSignalById(@PathVariable Long id) {
        SignalResponse response = signalService.getSignalById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SignalResponse>> updateSignal(
            @PathVariable Long id,
            @Valid @RequestBody SignalRequest request) {
        SignalResponse response = signalService.updateSignal(id, request);
        return ResponseEntity.ok(ApiResponse.success("Signal updated successfully", response));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSignal(@PathVariable Long id) {
        signalService.deleteSignal(id);
        return ResponseEntity.ok(ApiResponse.success("Signal deleted successfully", null));
    }
}
