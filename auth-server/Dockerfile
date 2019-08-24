FROM openjdk:8-jdk-alpine
#EXPOSE 8089:8089
ADD target/gateway-auth-server-1.0-SNAPSHOT.jar gateway-auth-server-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "gateway-auth-server-1.0-SNAPSHOT.jar"]