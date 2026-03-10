package com.geospatial.repository;

import com.geospatial.entity.Signal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignalRepository extends JpaRepository<Signal, Long> {
    
    List<Signal> findByRegionId(Long regionId);
    
    List<Signal> findByIndicatorType(String indicatorType);
}
