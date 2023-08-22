FROM amazoncorretto:8

ADD target/eurekaServer.jar webapp.jar

EXPOSE 7001

CMD ["java","-jar","webapp.jar"]