FROM amazoncorretto:21-alpine
WORKDIR /app
COPY externalized-config/target/*.jar app.jar
COPY externalized-config/src/main/resources/config-repo /app/config-repo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]