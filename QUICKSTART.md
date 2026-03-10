# Quick Start Guide

## Running the Application (Choose One Method)

### Method 1: Using VS Code (Easiest)

1. **Install Required Extensions:**
   - Open VS Code
   - Install "Extension Pack for Java" by Microsoft
   - Install "Spring Boot Extension Pack" by VMware

2. **Open and Run:**
   - Open the `SpringBootRestAPI` folder in VS Code
   - Navigate to `src/main/java/com/geospatial/GeospatialAnalysisApplication.java`
   - Click the "Run" button that appears above the `main` method
   - Or press `F5` to debug

3. **Verify:**
   - Look for "Started GeospatialAnalysisApplication" in the terminal
   - Application runs on: http://localhost:8080

### Method 2: Using Maven Command Line

**Prerequisites:** Java 17+ and Maven must be installed (see SETUP.md)

```bash
cd /Users/pritikavipin/Documents/SpringBootRestAPI

# Build and run
mvn spring-boot:run
```

### Method 3: Using IntelliJ IDEA

1. Open IntelliJ IDEA
2. File → Open → Select `SpringBootRestAPI` folder
3. Wait for Maven import
4. Right-click `GeospatialAnalysisApplication.java` → Run

## Testing the API

### Quick Test with curl

```bash
# Test if API is running
curl http://localhost:8080/api/regions

# You should see sample data with 3 regions
```

### Run Complete Test Suite

```bash
# Make sure application is running first!
cd /Users/pritikavipin/Documents/SpringBootRestAPI
./test-api.sh
```

### Sample API Calls

#### 1. Get All Regions
```bash
curl http://localhost:8080/api/regions
```

#### 2. Create a New Region
```bash
curl -X POST http://localhost:8080/api/regions \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tech Park",
    "latitude": 37.4419,
    "longitude": -122.1430,
    "description": "Innovation center"
  }'
```

#### 3. Add a Signal
```bash
curl -X POST http://localhost:8080/api/signals \
  -H "Content-Type: application/json" \
  -d '{
    "regionId": 1,
    "indicatorType": "TECH_INVESTMENT",
    "score": 95.0,
    "description": "High tech investment"
  }'
```

#### 4. Get Top Hotspots
```bash
curl http://localhost:8080/api/hotspots?limit=5
```

## Understanding the Response

All API responses follow this format:

```json
{
  "success": true,
  "message": "Success message",
  "data": { ... }
}
```

## Sample Data

The application comes pre-loaded with:
- **3 Regions**: Silicon Valley, Downtown District, East Bay
- **7 Signals**: Various growth indicators across regions

## Database Access

**H2 Console:** http://localhost:8080/h2-console

Connection details:
- JDBC URL: `jdbc:h2:mem:geospatialdb`
- Username: `sa`
- Password: (leave empty)

## Available Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/regions | Get all regions |
| POST | /api/regions | Create region |
| GET | /api/regions/{id} | Get region by ID |
| PUT | /api/regions/{id} | Update region |
| DELETE | /api/regions/{id} | Delete region |
| GET | /api/signals | Get all signals |
| GET | /api/signals?regionId={id} | Get signals by region |
| POST | /api/signals | Create signal |
| GET | /api/signals/{id} | Get signal by ID |
| PUT | /api/signals/{id} | Update signal |
| DELETE | /api/signals/{id} | Delete signal |
| GET | /api/hotspots?limit={n} | Get top N hotspots |

## Import Postman Collection

1. Open Postman
2. Click Import
3. Select `Geospatial-API.postman_collection.json`
4. All endpoints will be ready to test!

## Troubleshooting

**Application won't start?**
- Check if Java 17+ is installed: `java -version`
- Check if port 8080 is available: `lsof -i :8080`

**See SETUP.md for detailed installation instructions**

## Next Steps

- Explore the code in `src/main/java/com/geospatial/`
- Modify `DataInitializer.java` to add your own sample data
- Check README.md for detailed API documentation
