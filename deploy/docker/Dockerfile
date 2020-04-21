FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY site.jar site.jar
ENTRYPOINT ["java","-jar","/site.jar"]