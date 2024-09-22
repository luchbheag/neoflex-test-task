FROM adoptopenjdk/maven-openjdk11 AS build
WORKDIR /usr/app
COPY . /usr/app
RUN mvn package

FROM eclipse-temurin:17
WORKDIR /usr/app
COPY --from=build /usr/app/target/*.jar /usr/app/app.jar
CMD ["java", "-jar", "/usr/app/app.jar"]