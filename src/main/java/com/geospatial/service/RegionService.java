package com.geospatial.service;

import com.geospatial.dto.HotspotResponse;
import com.geospatial.dto.RegionRequest;
import com.geospatial.dto.RegionResponse;
import com.geospatial.dto.SignalResponse;
import com.geospatial.entity.Region;
import com.geospatial.exception.ResourceNotFoundException;
import com.geospatial.exception.DuplicateResourceException;
import com.geospatial.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegionService {
    
    private final RegionRepository regionRepository;
    
    @Transactional
    public RegionResponse createRegion(RegionRequest request) {
        log.info("Creating region with name: {}", request.getName());
        
        if (regionRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Region with name '" + request.getName() + "' already exists");
        }
        
        Region region = new Region();
        region.setName(request.getName());
        region.setLatitude(request.getLatitude());
        region.setLongitude(request.getLongitude());
        region.setDescription(request.getDescription());
        
        Region savedRegion = regionRepository.save(region);
        log.info("Region created successfully with ID: {}", savedRegion.getId());
        
        return mapToResponse(savedRegion);
    }
    
    @Transactional(readOnly = true)
    public List<RegionResponse> getAllRegions() {
        log.info("Fetching all regions");
        return regionRepository.findAllWithSignals().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public RegionResponse getRegionById(Long id) {
        log.info("Fetching region with ID: {}", id);
        Region region = regionRepository.findByIdWithSignals(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found with ID: " + id));
        return mapToResponse(region);
    }
    
    @Transactional
    public RegionResponse updateRegion(Long id, RegionRequest request) {
        log.info("Updating region with ID: {}", id);
        
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found with ID: " + id));
        
        if (!region.getName().equals(request.getName()) && regionRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Region with name '" + request.getName() + "' already exists");
        }
        
        region.setName(request.getName());
        region.setLatitude(request.getLatitude());
        region.setLongitude(request.getLongitude());
        region.setDescription(request.getDescription());
        
        Region updatedRegion = regionRepository.save(region);
        log.info("Region updated successfully with ID: {}", updatedRegion.getId());
        
        return mapToResponse(updatedRegion);
    }
    
    @Transactional
    public void deleteRegion(Long id) {
        log.info("Deleting region with ID: {}", id);
        
        if (!regionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Region not found with ID: " + id);
        }
        
        regionRepository.deleteById(id);
        log.info("Region deleted successfully with ID: {}", id);
    }
    
    @Transactional(readOnly = true)
    public List<HotspotResponse> getHotspots(Integer limit) {
        log.info("Fetching top {} hotspots", limit);
        
        List<Region> regions = regionRepository.findAllWithSignals();
        
        return regions.stream()
                .map(this::mapToHotspotResponse)
                .sorted((h1, h2) -> Double.compare(h2.getTotalScore(), h1.getTotalScore()))
                .limit(limit != null ? limit : 10)
                .collect(Collectors.toList());
    }
    
    private RegionResponse mapToResponse(Region region) {
        List<SignalResponse> signalResponses = region.getSignals().stream()
                .map(signal -> SignalResponse.builder()
                        .id(signal.getId())
                        .regionId(region.getId())
                        .regionName(region.getName())
                        .indicatorType(signal.getIndicatorType())
                        .score(signal.getScore())
                        .description(signal.getDescription())
                        .createdAt(signal.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
        
        return RegionResponse.builder()
                .id(region.getId())
                .name(region.getName())
                .latitude(region.getLatitude())
                .longitude(region.getLongitude())
                .description(region.getDescription())
                .createdAt(region.getCreatedAt())
                .updatedAt(region.getUpdatedAt())
                .signals(signalResponses)
                .totalScore(region.getTotalScore())
                .build();
    }
    
    private HotspotResponse mapToHotspotResponse(Region region) {
        return HotspotResponse.builder()
                .regionId(region.getId())
                .regionName(region.getName())
                .latitude(region.getLatitude())
                .longitude(region.getLongitude())
                .description(region.getDescription())
                .totalScore(region.getTotalScore())
                .signalCount(region.getSignals().size())
                .build();
    }
}
