# Setup Instructions

## Prerequisites Installation

### 1. Install Java 17 or Higher

**Option A: Using Homebrew (Recommended for macOS)**
```bash
# Install Homebrew if not installed
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Java 17
brew install openjdk@17

# Link Java
sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk

# Add to PATH (add to ~/.zshrc)
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

**Option B: Download from Oracle**
- Visit: https://www.oracle.com/java/technologies/downloads/
- Download JDK 17 for macOS
- Install the .dmg file

### 2. Install Maven

```bash
# Using Homebrew
brew install maven
```

### 3. Verify Installation

```bash
java -version    # Should show Java 17.x.x
mvn -version     # Should show Maven 3.x.x
```

## Running the Application

### Method 1: Using Maven

```bash
cd /Users/pritikavipin/Documents/SpringBootRestAPI

# Clean and build
mvn clean package -DskipTests

# Run the application
mvn spring-boot:run
```

### Method 2: Using VS Code with Java Extension Pack

1. **Install Extensions:**
   - Extension Pack for Java (by Microsoft)
   - Spring Boot Extension Pack (by VMware)

2. **Open Project:**
   - Open the `SpringBootRestAPI` folder in VS Code
   - Wait for Java extensions to load

3. **Run Application:**
   - Open `GeospatialAnalysisApplication.java`
   - Click "Run" or "Debug" button above the `main` method
   - Or press F5 to debug

### Method 3: Using IntelliJ IDEA

1. **Open Project:**
   - File → Open → Select `SpringBootRestAPI` folder
   - Wait for Maven import to complete

2. **Run Application:**
   - Navigate to `GeospatialAnalysisApplication.java`
   - Right-click → Run 'GeospatialAnalysisApplication'

## Verifying the Application

Once running, test the API:

```bash
# Check if server is running
curl http://localhost:8080/api/regions

# Create a test region
curl -X POST http://localhost:8080/api/regions \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Region",
    "latitude": 37.7749,
    "longitude": -122.4194,
    "description": "Test description"
  }'
```

## Troubleshooting

### Java Not Found
```bash
# Check JAVA_HOME
echo $JAVA_HOME

# Set JAVA_HOME if empty (add to ~/.zshrc)
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

### Maven Not Found
```bash
# Install via Homebrew
brew install maven
```

### Port 8080 Already in Use
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or change port in application.properties
server.port=8081
```

## Next Steps

After the application starts successfully:

1. Access H2 Console: http://localhost:8080/h2-console
2. Test API endpoints using the examples in README.md
3. Use tools like Postman or curl to interact with the API
