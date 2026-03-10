# Geospatial Analysis Service - REST API
[![Watch the video](https://img.youtube.com/vi/N86pguovKHA/0.jpg)](https://www.youtube.com/watch?v=N86pguovKHA)

A Spring Boot REST API for geospatial analysis that stores and analyzes geographic regions with growth indicators.

## Features

- **Region Management**: Create, read, update, and delete geographic regions
- **Signal Tracking**: Store growth indicators (population growth, POI counts, etc.) for regions
- **Hotspot Detection**: Retrieve regions with the highest combined scores
- **H2 In-Memory Database**: Fast data storage for development and testing
- **Comprehensive Error Handling**: Proper HTTP status codes and JSON responses

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Maven**

## Project Structure

```
src/main/java/com/geospatial/
├── controller/          # REST controllers
│   ├── RegionController.java
│   ├── SignalController.java
│   └── HotspotController.java
├── service/            # Business logic
│   ├── RegionService.java
│   └── SignalService.java
├── repository/         # Data access layer
│   ├── RegionRepository.java
│   └── SignalRepository.java
├── entity/            # JPA entities
│   ├── Region.java
│   └── Signal.java
├── dto/              # Data transfer objects
│   ├── RegionRequest.java
│   ├── RegionResponse.java
│   ├── SignalRequest.java
│   ├── SignalResponse.java
│   ├── HotspotResponse.java
│   └── ApiResponse.java
├── exception/        # Exception handling
│   ├── ResourceNotFoundException.java
│   ├── DuplicateResourceException.java
│   └── GlobalExceptionHandler.java
└── GeospatialAnalysisApplication.java
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Navigate to project directory
cd SpringBootRestAPI

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Regions API (`/api/regions`)

#### Create a Region
```http
POST /api/regions
Content-Type: application/json

{
  "name": "Downtown District",
  "latitude": 37.7749,
  "longitude": -122.4194,
  "description": "Central business district"
}
```

#### Get All Regions
```http
GET /api/regions
```

#### Get Region by ID
```http
GET /api/regions/{id}
```

#### Update Region
```http
PUT /api/regions/{id}
Content-Type: application/json

{
  "name": "Downtown District",
  "latitude": 37.7749,
  "longitude": -122.4194,
  "description": "Updated description"
}
```

#### Delete Region
```http
DELETE /api/regions/{id}
```

### 2. Signals API (`/api/signals`)

#### Create a Signal
```http
POST /api/signals
Content-Type: application/json

{
  "regionId": 1,
  "indicatorType": "POPULATION_GROWTH",
  "score": 85.5,
  "description": "Annual population growth rate"
}
```

#### Get All Signals
```http
GET /api/signals
```

#### Get Signals by Region
```http
GET /api/signals?regionId=1
```

#### Get Signal by ID
```http
GET /api/signals/{id}
```

#### Update Signal
```http
PUT /api/signals/{id}
Content-Type: application/json

{
  "regionId": 1,
  "indicatorType": "POPULATION_GROWTH",
  "score": 90.0,
  "description": "Updated growth rate"
}
```

#### Delete Signal
```http
DELETE /api/signals/{id}
```

### 3. Hotspots API (`/api/hotspots`)

#### Get Top Hotspots
```http
GET /api/hotspots?limit=10
```

Returns regions sorted by total score (sum of all signals) in descending order.

## Sample Usage

### Example Workflow

```bash
# 1. Create regions
curl -X POST http://localhost:8080/api/regions \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Silicon Valley",
    "latitude": 37.3861,
    "longitude": -122.0839,
    "description": "Tech hub"
  }'

# 2. Add signals to the region
curl -X POST http://localhost:8080/api/signals \
  -H "Content-Type: application/json" \
  -d '{
    "regionId": 1,
    "indicatorType": "TECH_INVESTMENT",
    "score": 95.0,
    "description": "High tech investment activity"
  }'

curl -X POST http://localhost:8080/api/signals \
  -H "Content-Type: application/json" \
  -d '{
    "regionId": 1,
    "indicatorType": "POPULATION_GROWTH",
    "score": 78.5,
    "description": "Strong population growth"
  }'

# 3. Get top hotspots
curl http://localhost:8080/api/hotspots?limit=5
```

## Response Format

All API responses follow this format:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... }
}
```

### Error Response Example
```json
{
  "success": false,
  "message": "Region not found with ID: 999",
  "data": null
}
```

## HTTP Status Codes

- `200 OK` - Successful GET, PUT, DELETE
- `201 Created` - Successful POST
- `400 Bad Request` - Validation errors
- `404 Not Found` - Resource not found
- `409 Conflict` - Duplicate resource
- `500 Internal Server Error` - Server error

## H2 Console

Access the H2 database console at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:geospatialdb`
- Username: `sa`
- Password: (leave empty)

## Common Indicator Types

- `POPULATION_GROWTH` - Population growth rate
- `POI_COUNT` - Points of interest count
- `TECH_INVESTMENT` - Technology investment level
- `BUSINESS_ACTIVITY` - Business development activity
- `INFRASTRUCTURE_SCORE` - Infrastructure quality
- `EMPLOYMENT_RATE` - Employment growth rate

## License

This project is open source and available under the MIT License.
