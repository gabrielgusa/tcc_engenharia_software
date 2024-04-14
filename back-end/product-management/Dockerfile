FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=build /app/target/product-management-0.0.1-SNAPSHOT.jar /app/product-management.jar

CMD [ "java", "-jar", "/app/product-management.jar", "--spring.profiles.active=dev" ]