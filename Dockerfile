FROM maven:3.9.8-amazoncorretto-17 AS build
WORKDIR /build
COPY src ./src
COPY pom.xml ./

RUN mvn clean package -e -DskipTests

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /build/target/EatSmartAPI*jar ./EatSmartAPI.jar
EXPOSE 9090
CMD ["java","-jar","EatSmartAPI.jar"]