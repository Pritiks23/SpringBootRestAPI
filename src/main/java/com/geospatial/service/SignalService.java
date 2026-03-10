package com.geospatial.service;

import com.geospatial.dto.SignalRequest;
import com.geospatial.dto.SignalResponse;
import com.geospatial.entity.Region;
import com.geospatial.entity.Signal;
import com.geospatial.exception.ResourceNotFoundException;
import com.geospatial.repository.RegionRepository;
import com.geospatial.repository.SignalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignalService {
    
    private final SignalRepository signalRepository;
    private final RegionRepository regionRepository;
    
    @Transactional
    public SignalResponse createSignal(SignalRequest request) {
        log.info("Creating signal for region ID: {}", request.getRegionId());
        
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new ResourceNotFoundException("Region not found with ID: " + request.getRegionId()));
        
        Signal signal = new Signal();
        signal.setRegion(region);
        signal.setIndicatorType(request.getIndicatorType());
        signal.setScore(request.getScore());
        signal.setDescription(request.getDescription());
        
        Signal savedSignal = signalRepository.save(signal);
        log.info("Signal created successfully with ID: {}", savedSignal.getId());
        
        return mapToResponse(savedSignal);
    }
    
    @Transactional(readOnly = true)
    public List<SignalResponse> getAllSignals() {
        log.info("Fetching all signals");
        return signalRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public SignalResponse getSignalById(Long id) {
        log.info("Fetching signal with ID: {}", id);
        Signal signal = signalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Signal not found with ID: " + id));
        return mapToResponse(signal);
    }
    
    @Transactional(readOnly = true)
    public List<SignalResponse> getSignalsByRegionId(Long regionId) {
        log.info("Fetching signals for region ID: {}", regionId);
        
        if (!regionRepository.existsById(regionId)) {
            throw new ResourceNotFoundException("Region not found with ID: " + regionId);
        }
        
        return signalRepository.findByRegionId(regionId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public SignalResponse updateSignal(Long id, SignalRequest request) {
        log.info("Updating signal with ID: {}", id);
        
        Signal signal = signalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Signal not found with ID: " + id));
        
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new ResourceNotFoundException("Region not found with ID: " + request.getRegionId()));
        
        signal.setRegion(region);
        signal.setIndicatorType(request.getIndicatorType());
        signal.setScore(request.getScore());
        signal.setDescription(request.getDescription());
        
        Signal updatedSignal = signalRepository.save(signal);
        log.info("Signal updated successfully with ID: {}", updatedSignal.getId());
        
        return mapToResponse(updatedSignal);
    }
    
    @Transactional
    public void deleteSignal(Long id) {
        log.info("Deleting signal with ID: {}", id);
        
        if (!signalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Signal not found with ID: " + id);
        }
        
        signalRepository.deleteById(id);
        log.info("Signal deleted successfully with ID: {}", id);
    }
    
    private SignalResponse mapToResponse(Signal signal) {
        return SignalResponse.builder()
                .id(signal.getId())
                .regionId(signal.getRegion().getId())
                .regionName(signal.getRegion().getName())
                .indicatorType(signal.getIndicatorType())
                .score(signal.getScore())
                .description(signal.getDescription())
                .createdAt(signal.getCreatedAt())
                .build();
    }
}
