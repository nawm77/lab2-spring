FROM maven:3.8.3-openjdk-17-slim as builder
WORKDIR /build
COPY . .
RUN mvn package -DskipTests
FROM bellsoft/liberica-openjdk-alpine-musl
WORKDIR /app
COPY --from=builder /build/target/springlab.jar .
CMD java -jar springlab.jar