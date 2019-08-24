FROM openjdk:8-jdk-alpine
#EXPOSE 8081:8081
ADD target/chefsick-service-menu-1.0-SNAPSHOT.jar menu.jar
ENTRYPOINT ["java", "-jar", "menu.jar"]