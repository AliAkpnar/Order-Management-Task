FROM openjdk:8-alpine
EXPOSE 8080

ADD target/order-management.jar order-management.jar
ENTRYPOINT ["java","-jar","/order-management.jar"]

