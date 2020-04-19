FROM openjdk:8-jre-alpine

MAINTAINER luoliang

ENV DUBBO_IP_TO_REGISTRY 172.27.0.15
ENV DUBBO_PORT_TO_REGISTRY 20880

ADD target/dubbo-provider-1.0.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]

EXPOSE 20880

