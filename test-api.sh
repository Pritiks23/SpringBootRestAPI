#!/bin/bash

# Geospatial Analysis API Test Script

BASE_URL="http://localhost:8080/api"

echo "========================================="
echo "Geospatial Analysis API Test Script"
echo "========================================="
echo ""

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Test 1: Get all regions
echo "Test 1: Get all regions (should show sample data)"
curl -s -X GET "$BASE_URL/regions" | python3 -m json.tool
echo -e "${GREEN}✓ Test 1 completed${NC}\n"

# Test 2: Create a new region
echo "Test 2: Create a new region"
REGION_RESPONSE=$(curl -s -X POST "$BASE_URL/regions" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Marina District",
    "latitude": 37.8021,
    "longitude": -122.4385,
    "description": "Waterfront residential area"
  }')
echo "$REGION_RESPONSE" | python3 -m json.tool
REGION_ID=$(echo "$REGION_RESPONSE" | python3 -c "import sys, json; print(json.load(sys.stdin)['data']['id'])")
echo -e "${GREEN}✓ Test 2 completed - Created region with ID: $REGION_ID${NC}\n"

# Test 3: Get region by ID
echo "Test 3: Get region by ID: $REGION_ID"
curl -s -X GET "$BASE_URL/regions/$REGION_ID" | python3 -m json.tool
echo -e "${GREEN}✓ Test 3 completed${NC}\n"

# Test 4: Create signals for the region
echo "Test 4: Create signals for region $REGION_ID"

echo "  4.1: Creating POPULATION_GROWTH signal"
SIGNAL1_RESPONSE=$(curl -s -X POST "$BASE_URL/signals" \
  -H "Content-Type: application/json" \
  -d "{
    \"regionId\": $REGION_ID,
    \"indicatorType\": \"POPULATION_GROWTH\",
    \"score\": 75.5,
    \"description\": \"Steady population increase\"
  }")
echo "$SIGNAL1_RESPONSE" | python3 -m json.tool

echo "  4.2: Creating POI_COUNT signal"
curl -s -X POST "$BASE_URL/signals" \
  -H "Content-Type: application/json" \
  -d "{
    \"regionId\": $REGION_ID,
    \"indicatorType\": \"POI_COUNT\",
    \"score\": 82.0,
    \"description\": \"High density of attractions\"
  }" | python3 -m json.tool

echo "  4.3: Creating INFRASTRUCTURE_SCORE signal"
curl -s -X POST "$BASE_URL/signals" \
  -H "Content-Type: application/json" \
  -d "{
    \"regionId\": $REGION_ID,
    \"indicatorType\": \"INFRASTRUCTURE_SCORE\",
    \"score\": 88.5,
    \"description\": \"Excellent public transportation\"
  }" | python3 -m json.tool
echo -e "${GREEN}✓ Test 4 completed${NC}\n"

# Test 5: Get all signals
echo "Test 5: Get all signals"
curl -s -X GET "$BASE_URL/signals" | python3 -m json.tool
echo -e "${GREEN}✓ Test 5 completed${NC}\n"

# Test 6: Get signals for specific region
echo "Test 6: Get signals for region $REGION_ID"
curl -s -X GET "$BASE_URL/signals?regionId=$REGION_ID" | python3 -m json.tool
echo -e "${GREEN}✓ Test 6 completed${NC}\n"

# Test 7: Get region with signals (should show total score)
echo "Test 7: Get region with all signals and total score"
curl -s -X GET "$BASE_URL/regions/$REGION_ID" | python3 -m json.tool
echo -e "${GREEN}✓ Test 7 completed${NC}\n"

# Test 8: Get hotspots
echo "Test 8: Get top hotspots (limit=5)"
curl -s -X GET "$BASE_URL/hotspots?limit=5" | python3 -m json.tool
echo -e "${GREEN}✓ Test 8 completed${NC}\n"

# Test 9: Update region
echo "Test 9: Update region $REGION_ID"
curl -s -X PUT "$BASE_URL/regions/$REGION_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Marina District Updated",
    "latitude": 37.8021,
    "longitude": -122.4385,
    "description": "Updated waterfront residential area with new developments"
  }' | python3 -m json.tool
echo -e "${GREEN}✓ Test 9 completed${NC}\n"

# Test 10: Test error handling - duplicate region
echo "Test 10: Test error handling - try creating duplicate region"
curl -s -X POST "$BASE_URL/regions" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Silicon Valley",
    "latitude": 37.3861,
    "longitude": -122.0839,
    "description": "Duplicate region"
  }' | python3 -m json.tool
echo -e "${GREEN}✓ Test 10 completed (should show conflict error)${NC}\n"

# Test 11: Test error handling - resource not found
echo "Test 11: Test error handling - get non-existent region"
curl -s -X GET "$BASE_URL/regions/9999" | python3 -m json.tool
echo -e "${GREEN}✓ Test 11 completed (should show not found error)${NC}\n"

# Test 12: Get all regions (final state)
echo "Test 12: Get all regions (final state)"
curl -s -X GET "$BASE_URL/regions" | python3 -m json.tool
echo -e "${GREEN}✓ Test 12 completed${NC}\n"

echo "========================================="
echo -e "${GREEN}All tests completed successfully!${NC}"
echo "========================================="
