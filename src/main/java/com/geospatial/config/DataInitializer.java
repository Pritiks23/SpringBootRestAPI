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
            log.info("Initializing comprehensive sample data...");
            
            // Create regions
            Region siliconValley = new Region();
            siliconValley.setName("Silicon Valley");
            siliconValley.setLatitude(37.3861);
            siliconValley.setLongitude(-122.0839);
            siliconValley.setDescription("Technology and innovation hub - Leading center for startups and tech giants");
            regionRepository.save(siliconValley);
            
            Region downtown = new Region();
            downtown.setName("Downtown District");
            downtown.setLatitude(37.7749);
            downtown.setLongitude(-122.4194);
            downtown.setDescription("Central business district - Financial and corporate core");
            regionRepository.save(downtown);
            
            Region eastBay = new Region();
            eastBay.setName("East Bay");
            eastBay.setLatitude(37.8044);
            eastBay.setLongitude(-122.2712);
            eastBay.setDescription("Emerging tech corridor - Growing tech ecosystem");
            regionRepository.save(eastBay);
            
            Region missionDistrict = new Region();
            missionDistrict.setName("Mission District");
            missionDistrict.setLatitude(37.7599);
            missionDistrict.setLongitude(-122.4148);
            missionDistrict.setDescription("Vibrant cultural and residential neighborhood");
            regionRepository.save(missionDistrict);
            
            Region sunnyvale = new Region();
            sunnyvale.setName("Sunnyvale");
            sunnyvale.setLatitude(37.3708);
            sunnyvale.setLongitude(-122.0307);
            sunnyvale.setDescription("Major tech corporate headquarters location");
            regionRepository.save(sunnyvale);
            
            Region mountainView = new Region();
            mountainView.setName("Mountain View");
            mountainView.setLatitude(37.3861);
            mountainView.setLongitude(-122.0839);
            mountainView.setDescription("Home of major search and cloud companies");
            regionRepository.save(mountainView);
            
            Region paloAlto = new Region();
            paloAlto.setName("Palo Alto");
            paloAlto.setLatitude(37.4419);
            paloAlto.setLongitude(-122.1430);
            paloAlto.setDescription("Premier venture capital and research hub");
            regionRepository.save(paloAlto);
            
            Region oakland = new Region();
            oakland.setName("Oakland");
            oakland.setLatitude(37.8044);
            oakland.setLongitude(-122.2712);
            oakland.setDescription("Industrial and creative tech hub");
            regionRepository.save(oakland);
            
            // Signals for Silicon Valley (8 signals)
            addSignal(signalRepository, siliconValley, "TECH_INVESTMENT", 95.0, "High tech investment activity");
            addSignal(signalRepository, siliconValley, "POPULATION_GROWTH", 78.5, "Strong population growth");
            addSignal(signalRepository, siliconValley, "POI_COUNT", 88.0, "High density of points of interest");
            addSignal(signalRepository, siliconValley, "STARTUP_DENSITY", 92.0, "Highest startup concentration");
            addSignal(signalRepository, siliconValley, "VENTURE_FUNDING", 98.5, "Record venture capital funding");
            addSignal(signalRepository, siliconValley, "EMPLOYMENT_RATE", 94.0, "Excellent employment opportunities");
            addSignal(signalRepository, siliconValley, "INFRASTRUCTURE_SCORE", 87.0, "Advanced tech infrastructure");
            addSignal(signalRepository, siliconValley, "INNOVATION_INDEX", 96.5, "Leading innovation metrics");
            
            // Signals for Downtown (8 signals)
            addSignal(signalRepository, downtown, "BUSINESS_ACTIVITY", 92.0, "High business development");
            addSignal(signalRepository, downtown, "INFRASTRUCTURE_SCORE", 85.5, "Excellent infrastructure");
            addSignal(signalRepository, downtown, "REAL_ESTATE_PRICE", 88.0, "Premium property values");
            addSignal(signalRepository, downtown, "TRANSIT_ACCESS", 89.0, "Exceptional public transit");
            addSignal(signalRepository, downtown, "CORPORATE_PRESENCE", 91.0, "Major corporate headquarters");
            addSignal(signalRepository, downtown, "FOOT_TRAFFIC", 87.5, "High pedestrian activity");
            addSignal(signalRepository, downtown, "NIGHTLIFE_SCORE", 86.0, "Vibrant entertainment venues");
            addSignal(signalRepository, downtown, "ECONOMIC_GROWTH", 84.5, "Steady economic expansion");
            
            // Signals for East Bay (6 signals)
            addSignal(signalRepository, eastBay, "EMPLOYMENT_RATE", 72.0, "Growing employment opportunities");
            addSignal(signalRepository, eastBay, "POPULATION_GROWTH", 81.0, "Rapid population increase");
            addSignal(signalRepository, eastBay, "AFFORDABILITY_INDEX", 65.0, "More affordable than Valley");
            addSignal(signalRepository, eastBay, "TECH_GROWTH", 75.5, "Emerging tech presence");
            addSignal(signalRepository, eastBay, "MANUFACTURING", 73.0, "Growing manufacturing sector");
            addSignal(signalRepository, eastBay, "EDUCATION_SCORE", 78.5, "Quality educational institutions");
            
            // Signals for Mission District (6 signals)
            addSignal(signalRepository, missionDistrict, "CULTURAL_ACTIVITY", 84.0, "Rich cultural scene");
            addSignal(signalRepository, missionDistrict, "RESTAURANT_DENSITY", 87.0, "Excellent dining options");
            addSignal(signalRepository, missionDistrict, "ARTISTIC_VENUES", 82.0, "Thriving arts community");
            addSignal(signalRepository, missionDistrict, "RESIDENTIAL_GROWTH", 79.0, "Rising residential development");
            addSignal(signalRepository, missionDistrict, "POPULATION_GROWTH", 76.5, "Strong demographic growth");
            addSignal(signalRepository, missionDistrict, "NIGHTLIFE_SCORE", 85.0, "Vibrant nightlife scene");
            
            // Signals for Sunnyvale (7 signals)
            addSignal(signalRepository, sunnyvale, "CORPORATE_OFFICES", 93.0, "Major tech company offices");
            addSignal(signalRepository, sunnyvale, "EMPLOYMENT_RATE", 89.0, "High employment rate");
            addSignal(signalRepository, sunnyvale, "SALARY_LEVELS", 91.0, "Competitive salaries");
            addSignal(signalRepository, sunnyvale, "OFFICE_SPACE", 86.0, "Premium office availability");
            addSignal(signalRepository, sunnyvale, "TECH_COMPANIES", 95.0, "Headquarters of tech giants");
            addSignal(signalRepository, sunnyvale, "PARKING_AVAILABILITY", 72.0, "Adequate parking facilities");
            addSignal(signalRepository, sunnyvale, "COMMUTE_TIME", 78.0, "Moderate commute times");
            
            // Signals for Mountain View (7 signals)
            addSignal(signalRepository, mountainView, "TECH_GIANTS", 97.0, "Headquarters of search giant");
            addSignal(signalRepository, mountainView, "RESEARCH_ACTIVITY", 94.0, "Leading research labs");
            addSignal(signalRepository, mountainView, "INNOVATION_INDEX", 95.0, "Cutting-edge innovation");
            addSignal(signalRepository, mountainView, "EMPLOYMENT_RATE", 92.0, "Excellent job market");
            addSignal(signalRepository, mountainView, "INFRASTRUCTURE_SCORE", 90.0, "World-class infrastructure");
            addSignal(signalRepository, mountainView, "PATENT_ACTIVITY", 96.0, "High patent filings");
            addSignal(signalRepository, mountainView, "EDUCATION_SCORE", 88.0, "Excellent schools");
            
            // Signals for Palo Alto (6 signals)
            addSignal(signalRepository, paloAlto, "VENTURE_CAPITAL", 96.0, "Highest VC concentration");
            addSignal(signalRepository, paloAlto, "STARTUP_SUCCESS", 93.0, "High startup success rate");
            addSignal(signalRepository, paloAlto, "REAL_ESTATE_PRICE", 95.0, "Highest property values");
            addSignal(signalRepository, paloAlto, "EDUCATION_QUALITY", 94.0, "Top-tier universities");
            addSignal(signalRepository, paloAlto, "INNOVATION_INDEX", 94.5, "Peak innovation metrics");
            addSignal(signalRepository, paloAlto, "TECH_TALENT", 92.0, "Concentrated tech talent");
            
            // Signals for Oakland (7 signals)
            addSignal(signalRepository, oakland, "CREATIVE_INDUSTRIES", 81.0, "Growing creative sector");
            addSignal(signalRepository, oakland, "STARTUP_GROWTH", 78.0, "Emerging startup scene");
            addSignal(signalRepository, oakland, "AFFORDABILITY_INDEX", 72.0, "More affordable living");
            addSignal(signalRepository, oakland, "CULTURAL_DIVERSITY", 86.0, "Highly diverse community");
            addSignal(signalRepository, oakland, "ARTS_VENUES", 83.0, "Strong arts infrastructure");
            addSignal(signalRepository, oakland, "WATERFRONT_DEVELOPMENT", 75.0, "Developing waterfront area");
            addSignal(signalRepository, oakland, "PORT_ACTIVITY", 79.0, "Active shipping hub");
            
            log.info("Sample data initialized successfully!");
            log.info("Total Regions: {}", regionRepository.count());
            log.info("Total Signals: {}", signalRepository.count());
        };
    }
    
    private void addSignal(SignalRepository repository, Region region, String type, Double score, String description) {
        Signal signal = new Signal();
        signal.setRegion(region);
        signal.setIndicatorType(type);
        signal.setScore(score);
        signal.setDescription(description);
        repository.save(signal);
    }
}
