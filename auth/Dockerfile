FROM eclipse-temurin:17-jre-alpine

WORKDIR app

ENV spring_profiles_active=docker,local

COPY build/libs/app.jar app.jar

EXPOSE 8095

ENTRYPOINT ["java", "-jar", "app.jar"]
