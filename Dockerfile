FROM eclipse-temurin:21-jre

COPY JAR/firmadigital-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
