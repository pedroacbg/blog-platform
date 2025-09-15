FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /src
COPY . .
RUN mvn -B -ntp -DskipTests package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -XX:+UseZGC"
COPY --from=build /src/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
