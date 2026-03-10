# Geospatial Analysis Service

> A fully functional Spring Boot REST API for geospatial analysis, growth tracking, and hotspot detection.

## 🚀 Quick Links

- **[Quick Start Guide](QUICKSTART.md)** - Get started in 5 minutes
- **[Setup Instructions](SETUP.md)** - Detailed installation guide
- **[API Documentation](README.md)** - Complete API reference
- **[Architecture](ARCHITECTURE.md)** - System design and flow
- **[Project Summary](PROJECT_SUMMARY.md)** - Overview and features

## 📋 What's Included

### ✅ Complete REST API
- **Regions Management**: CRUD operations for geographic regions
- **Signals Tracking**: Store and manage growth indicators
- **Hotspot Detection**: Identify high-potential areas by score

### ✅ Production-Ready Features
- Input validation
- Error handling with proper HTTP codes
- Consistent JSON responses
- Database relationships
- Sample data initialization
- Comprehensive documentation

### ✅ Testing Tools
- Automated test script (`test-api.sh`)
- Postman collection
- Sample API calls
- H2 console access

## 🎯 Use Cases

- **Urban Planning**: Track city development indicators
- **Investment Analysis**: Identify growth regions
- **Real Estate**: Location scoring for development
- **Business Intelligence**: Optimal expansion locations

## 🛠️ Technology

- Java 17
- Spring Boot 3.2.3
- Spring Web (REST API)
- Spring Data JPA (Database)
- H2 Database (SQL)
- Lombok (Code simplification)
- Maven (Build tool)

## 📊 API Endpoints

| Endpoint | Purpose |
|----------|---------|
| `/api/regions` | Manage geographic regions |
| `/api/signals` | Track growth indicators |
| `/api/hotspots` | Get top-scoring regions |

## 🏃 Running the Application

### Option 1: VS Code
1. Install Java Extension Pack
2. Open project folder
3. Run `GeospatialAnalysisApplication.java`

### Option 2: Command Line
```bash
cd SpringBootRestAPI
mvn spring-boot:run
```

**Server runs at:** http://localhost:8080

## 🧪 Quick Test

```bash
# Test if running
curl http://localhost:8080/api/regions

# Create a region
curl -X POST http://localhost:8080/api/regions \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tech Hub",
    "latitude": 37.7749,
    "longitude": -122.4194,
    "description": "Innovation center"
  }'

# Get hotspots
curl http://localhost:8080/api/hotspots?limit=5
```

## 📁 Project Structure

```
SpringBootRestAPI/
├── src/main/java/com/geospatial/
│   ├── controller/          # REST endpoints
│   ├── service/            # Business logic
│   ├── repository/         # Database access
│   ├── entity/            # Data models
│   ├── dto/               # API objects
│   ├── exception/         # Error handling
│   └── config/            # Configuration
├── src/main/resources/
│   └── application.properties
├── pom.xml
├── README.md              # Full API docs
├── QUICKSTART.md          # Quick start
├── SETUP.md               # Installation
├── ARCHITECTURE.md        # System design
├── PROJECT_SUMMARY.md     # Overview
├── test-api.sh           # Test script
└── Geospatial-API.postman_collection.json
```

## 📚 Documentation Guide

### New to the Project?
1. Start with **[QUICKSTART.md](QUICKSTART.md)**
2. Learn the API from **[README.md](README.md)**
3. Understand design via **[ARCHITECTURE.md](ARCHITECTURE.md)**

### Need to Install?
- See **[SETUP.md](SETUP.md)** for Java/Maven installation

### Want Details?
- Check **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** for complete overview

### Testing?
- Run `./test-api.sh` for automated tests
- Import `Geospatial-API.postman_collection.json` into Postman

## 🎓 Learning Resources

### Sample Data Included
The app starts with 3 regions and 7 signals:
- Silicon Valley (tech hub)
- Downtown District (business center)
- East Bay (emerging area)

### H2 Console
View data directly: http://localhost:8080/h2-console
- URL: `jdbc:h2:mem:geospatialdb`
- User: `sa`
- Password: (empty)

## 💡 Example Workflow

```bash
# 1. Start the application
mvn spring-boot:run

# 2. View existing regions
curl http://localhost:8080/api/regions

# 3. Create a new region
curl -X POST http://localhost:8080/api/regions \
  -H "Content-Type: application/json" \
  -d '{"name": "New Area", "latitude": 37.5, "longitude": -122.5, "description": "Growing region"}'

# 4. Add growth indicators
curl -X POST http://localhost:8080/api/signals \
  -H "Content-Type: application/json" \
  -d '{"regionId": 1, "indicatorType": "POPULATION_GROWTH", "score": 85.0, "description": "High growth"}'

# 5. Find hotspots
curl http://localhost:8080/api/hotspots?limit=5
```

## ✨ Key Features

### Smart Scoring
Regions automatically calculate total scores from all their signals.

### Proper REST Design
- `POST` creates resources (201 Created)
- `GET` retrieves data (200 OK)
- `PUT` updates resources (200 OK)
- `DELETE` removes resources (200 OK)
- `404` for not found
- `409` for conflicts
- `400` for validation errors

### Comprehensive Error Handling
```json
{
  "success": false,
  "message": "Region not found with ID: 999",
  "data": null
}
```

### Input Validation
All requests validated automatically:
- Required fields checked
- Data types enforced
- Clear error messages

## 🔧 Customization

### Add New Indicator Types
Edit `DataInitializer.java` to add custom types:
- TECH_INVESTMENT
- POPULATION_GROWTH
- POI_COUNT
- BUSINESS_ACTIVITY
- INFRASTRUCTURE_SCORE
- EMPLOYMENT_RATE
- (Add your own!)

### Change Database
Replace H2 with PostgreSQL/MySQL in `pom.xml` and `application.properties`

### Add Features
The layered architecture makes it easy to extend:
- Add new endpoints in controllers
- Add business logic in services
- Add queries in repositories

## 📞 Need Help?

1. **Can't install Java/Maven?** → See [SETUP.md](SETUP.md)
2. **How do I run it?** → See [QUICKSTART.md](QUICKSTART.md)
3. **What endpoints exist?** → See [README.md](README.md)
4. **How does it work?** → See [ARCHITECTURE.md](ARCHITECTURE.md)

## ✅ Status

**PROJECT COMPLETE** - Ready to run!

- ✅ All endpoints implemented
- ✅ Database configured
- ✅ Sample data loaded
- ✅ Error handling complete
- ✅ Documentation finished
- ✅ Tests provided
- ✅ Production-ready structure

## 📄 License

This project is open source and available under the MIT License.

---

**Ready to start?** Open [QUICKSTART.md](QUICKSTART.md) and begin in minutes!
