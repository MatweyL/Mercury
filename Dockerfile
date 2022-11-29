FROM openjdk:11
COPY target/mercury-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]