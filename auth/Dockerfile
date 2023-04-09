FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR project
COPY . .
RUN ./gradlew bootJar

FROM eclipse-temurin:17-jre-alpine

RUN adduser -D appuser
USER appuser

WORKDIR app

ARG ACTIVE_PROFILES="docker, local"

COPY --from=build project/build/libs/app.jar app.jar

ENV spring_profiles_active=$ACTIVE_PROFILES

EXPOSE 8095

ENTRYPOINT ["java", "-jar", "app.jar"]
