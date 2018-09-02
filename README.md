# simple-web-api

This is simple massaging application.

## Build and Run
```
./mvnw clean package
./mvnw spring-boot:run
```

## How to use

### Show messages
```
curl -X GET localhost:8080/messages
```
### Create a new message
```
curl -X POST localhost:8080/messages -H "Content-Type: application/json" -d "{\"text\": \"hogehoge\"}"
```
