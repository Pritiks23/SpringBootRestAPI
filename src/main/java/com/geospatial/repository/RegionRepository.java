package com.geospatial.repository;

import com.geospatial.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    
    Optional<Region> findByName(String name);
    
    boolean existsByName(String name);
    
    @Query("SELECT r FROM Region r LEFT JOIN FETCH r.signals")
    List<Region> findAllWithSignals();
    
    @Query("SELECT r FROM Region r LEFT JOIN FETCH r.signals WHERE r.id = :id")
    Optional<Region> findByIdWithSignals(Long id);
}
