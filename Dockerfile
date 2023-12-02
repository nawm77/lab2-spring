FROM maven:3.8.3-openjdk-17-slim as builder
WORKDIR /build
COPY . .
RUN mvn clean package
FROM bellsoft/liberica-openjdk-alpine-musl
WORKDIR /app
COPY --from=builder /build/target/springlab.jar .
RUN apk update && apk upgrade
RUN apk add curl
CMD java -jar springlab.jar