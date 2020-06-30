### Build
```
mvn clean install

cd client-typescript
npm install && npm run build
```

### Run
```
mvn spring-boot:run -pl eureka

mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8090 -pl server
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8091 -pl server

mvn spring-boot:run -pl client-java
```

### Test
```
http :8080/pets
```