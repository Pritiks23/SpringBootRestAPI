package com.geospatial.config;

import com.geospatial.entity.Region;
import com.geospatial.entity.Signal;
import com.geospatial.repository.RegionRepository;
import com.geospatial.repository.SignalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {
    
    @Bean
    CommandLineRunner initDatabase(RegionRepository regionRepository, SignalRepository signalRepository) {
        return args -> {
            log.info("Initializing sample data...");
            
            // Create sample regions
            Region siliconValley = new Region();
            siliconValley.setName("Silicon Valley");
            siliconValley.setLatitude(37.3861);
            siliconValley.setLongitude(-122.0839);
            siliconValley.setDescription("Technology and innovation hub");
            regionRepository.save(siliconValley);
            
            Region downtown = new Region();
            downtown.setName("Downtown District");
            downtown.setLatitude(37.7749);
            downtown.setLongitude(-122.4194);
            downtown.setDescription("Central business district");
            regionRepository.save(downtown);
            
            Region eastBay = new Region();
            eastBay.setName("East Bay");
            eastBay.setLatitude(37.8044);
            eastBay.setLongitude(-122.2712);
            eastBay.setDescription("Emerging tech corridor");
            regionRepository.save(eastBay);
            
            // Create signals for Silicon Valley
            Signal sv1 = new Signal();
            sv1.setRegion(siliconValley);
            sv1.setIndicatorType("TECH_INVESTMENT");
            sv1.setScore(95.0);
            sv1.setDescription("High tech investment activity");
            signalRepository.save(sv1);
            
            Signal sv2 = new Signal();
            sv2.setRegion(siliconValley);
            sv2.setIndicatorType("POPULATION_GROWTH");
            sv2.setScore(78.5);
            sv2.setDescription("Strong population growth");
            signalRepository.save(sv2);
            
            Signal sv3 = new Signal();
            sv3.setRegion(siliconValley);
            sv3.setIndicatorType("POI_COUNT");
            sv3.setScore(88.0);
            sv3.setDescription("High density of points of interest");
            signalRepository.save(sv3);
            
            // Create signals for Downtown
            Signal dt1 = new Signal();
            dt1.setRegion(downtown);
            dt1.setIndicatorType("BUSINESS_ACTIVITY");
            dt1.setScore(92.0);
            dt1.setDescription("High business development");
            signalRepository.save(dt1);
            
            Signal dt2 = new Signal();
            dt2.setRegion(downtown);
            dt2.setIndicatorType("INFRASTRUCTURE_SCORE");
            dt2.setScore(85.5);
            dt2.setDescription("Excellent infrastructure");
            signalRepository.save(dt2);
            
            // Create signals for East Bay
            Signal eb1 = new Signal();
            eb1.setRegion(eastBay);
            eb1.setIndicatorType("EMPLOYMENT_RATE");
            eb1.setScore(72.0);
            eb1.setDescription("Growing employment opportunities");
            signalRepository.save(eb1);
            
            Signal eb2 = new Signal();
            eb2.setRegion(eastBay);
            eb2.setIndicatorType("POPULATION_GROWTH");
            eb2.setScore(81.0);
            eb2.setDescription("Rapid population increase");
            signalRepository.save(eb2);
            
            log.info("Sample data initialized successfully!");
            log.info("Total Regions: {}", regionRepository.count());
            log.info("Total Signals: {}", signalRepository.count());
        };
    }
}
