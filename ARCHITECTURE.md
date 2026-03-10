# Architecture Overview

## System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         Client Layer                             │
│  (Browser, Postman, curl, Mobile App, etc.)                     │
└────────────────────────┬────────────────────────────────────────┘
                         │ HTTP/JSON
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Controller Layer                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐          │
│  │   Region     │  │   Signal     │  │   Hotspot    │          │
│  │  Controller  │  │  Controller  │  │  Controller  │          │
│  └──────────────┘  └──────────────┘  └──────────────┘          │
│         │                   │                  │                 │
│         └───────────────────┼──────────────────┘                │
│                             ▼                                    │
│                   GlobalExceptionHandler                         │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                      Service Layer                               │
│  ┌──────────────────────┐  ┌──────────────────────┐            │
│  │   RegionService      │  │   SignalService      │            │
│  │  - createRegion()    │  │  - createSignal()    │            │
│  │  - getAllRegions()   │  │  - getAllSignals()   │            │
│  │  - getRegionById()   │  │  - getSignalById()   │            │
│  │  - updateRegion()    │  │  - updateSignal()    │            │
│  │  - deleteRegion()    │  │  - deleteSignal()    │            │
│  │  - getHotspots()     │  │                      │            │
│  └──────────────────────┘  └──────────────────────┘            │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                   Repository Layer                               │
│  ┌──────────────────────┐  ┌──────────────────────┐            │
│  │  RegionRepository    │  │  SignalRepository    │            │
│  │  (JpaRepository)     │  │  (JpaRepository)     │            │
│  └──────────────────────┘  └──────────────────────┘            │
└────────────────────────────┬────────────────────────────────────┘
                             │ Spring Data JPA
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                      Entity Layer                                │
│  ┌──────────────────┐          ┌──────────────────┐            │
│  │     Region       │          │      Signal      │            │
│  │  - id            │ 1     ∞  │  - id            │            │
│  │  - name          │◄─────────┤  - region        │            │
│  │  - latitude      │          │  - indicatorType │            │
│  │  - longitude     │          │  - score         │            │
│  │  - description   │          │  - description   │            │
│  │  - signals[]     │          │  - createdAt     │            │
│  │  - createdAt     │          │                  │            │
│  │  - updatedAt     │          │                  │            │
│  └──────────────────┘          └──────────────────┘            │
└────────────────────────────┬────────────────────────────────────┘
                             │ Hibernate ORM
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Database Layer                                │
│              H2 In-Memory Database                               │
│  ┌────────────────┐          ┌────────────────┐                │
│  │  regions table │          │ signals table  │                │
│  └────────────────┘          └────────────────┘                │
└─────────────────────────────────────────────────────────────────┘
```

## Request Flow Example

### Creating a Signal

```
1. Client → POST /api/signals
   {
     "regionId": 1,
     "indicatorType": "POPULATION_GROWTH",
     "score": 85.5,
     "description": "Annual growth"
   }

2. SignalController → Validates request → Calls SignalService

3. SignalService → 
   - Checks if region exists (via RegionRepository)
   - Creates Signal entity
   - Saves via SignalRepository

4. SignalRepository → JPA/Hibernate → SQL INSERT

5. Response ← 201 Created
   {
     "success": true,
     "message": "Signal created successfully",
     "data": {
       "id": 1,
       "regionId": 1,
       "regionName": "Silicon Valley",
       "indicatorType": "POPULATION_GROWTH",
       "score": 85.5,
       "description": "Annual growth",
       "createdAt": "2026-03-10T10:30:00"
     }
   }
```

## Data Model Relationships

```
Region (One) ──────────< (Many) Signal
   │                         │
   │ One region can have     │ Each signal belongs
   │ multiple signals        │ to one region
   │                         │
   └─ Total Score = SUM of all signal scores
```

## API Endpoint Categories

### 1. CRUD Operations
```
Regions:  POST, GET, PUT, DELETE /api/regions
Signals:  POST, GET, PUT, DELETE /api/signals
```

### 2. Analysis Operations
```
Hotspots: GET /api/hotspots?limit=N
  → Returns regions sorted by total score
```

### 3. Query Operations
```
GET /api/signals?regionId=1
  → Filter signals by region
```

## Error Handling Flow

```
┌──────────────┐
│   Exception  │
│   Occurs     │
└──────┬───────┘
       │
       ▼
┌─────────────────────────────┐
│ GlobalExceptionHandler      │
├─────────────────────────────┤
│ @RestControllerAdvice       │
└──────┬──────────────────────┘
       │
       ├─► ResourceNotFoundException → 404
       ├─► DuplicateResourceException → 409
       ├─► MethodArgumentNotValidException → 400
       └─► Exception → 500
       │
       ▼
┌────────────────────────┐
│   JSON Error Response  │
│  {                     │
│    "success": false,   │
│    "message": "...",   │
│    "data": null        │
│  }                     │
└────────────────────────┘
```

## Component Responsibilities

### Controllers
- Handle HTTP requests/responses
- Validate input (using @Valid)
- Call service layer
- Return appropriate HTTP status codes

### Services
- Business logic
- Transaction management
- Data transformation (Entity ↔ DTO)
- Orchestrate repository calls

### Repositories
- Data access abstraction
- JPA query methods
- Custom queries (@Query)

### Entities
- Database table representation
- JPA annotations
- Relationship mapping
- Helper methods (getTotalScore)

### DTOs
- Request validation
- Response formatting
- Decouple API from entities

### Exception Handlers
- Centralized error handling
- Consistent error responses
- Proper HTTP status codes

## Technology Stack Integration

```
┌─────────────────────────────────────────┐
│          Spring Boot 3.2.3              │
├─────────────────────────────────────────┤
│  ┌──────────────┐  ┌──────────────┐    │
│  │ Spring Web   │  │ Spring Data  │    │
│  │              │  │     JPA      │    │
│  └──────────────┘  └──────────────┘    │
│         │                  │            │
│         ▼                  ▼            │
│  ┌──────────────┐  ┌──────────────┐    │
│  │   Jackson    │  │  Hibernate   │    │
│  │ (JSON conv.) │  │    (ORM)     │    │
│  └──────────────┘  └──────────────┘    │
│         │                  │            │
│         └──────────┬───────┘            │
│                    ▼                    │
│           ┌──────────────┐              │
│           │ H2 Database  │              │
│           └──────────────┘              │
└─────────────────────────────────────────┘
```

## Sample Data Flow

### Hotspot Detection Algorithm

```
1. Fetch all regions with signals (JOIN query)
2. For each region:
   - Calculate total score = SUM(signal.score)
   - Build HotspotResponse
3. Sort by total score (descending)
4. Limit to requested count
5. Return JSON response
```

## Performance Considerations

- **Lazy Loading**: Signals loaded only when needed
- **JOIN FETCH**: Prevents N+1 query problem
- **In-Memory DB**: Fast for development/testing
- **Transaction Management**: @Transactional ensures consistency
- **DTO Pattern**: Prevents entity exposure and serialization issues
