FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=build /app/target/store-management-0.0.1-SNAPSHOT.jar /app/store-management.jar

CMD [ "java", "-jar", "/app/store-management.jar", "--spring.profiles.active=dev" ]