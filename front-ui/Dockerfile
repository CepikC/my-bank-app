FROM amazoncorretto:21-alpine
WORKDIR /app
COPY front-ui/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]