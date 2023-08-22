FROM openjdk:8.0
ARG JAR_FILE
COPY ${JAR_FILE} myapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/myapp.jar"]
