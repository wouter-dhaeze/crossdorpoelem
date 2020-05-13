FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY cdo-site.jar cdo-site.jar
ENTRYPOINT ["java","-jar","/cdo-site.jar"]