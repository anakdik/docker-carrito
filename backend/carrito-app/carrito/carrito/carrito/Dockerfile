FROM eclipse-temurin:21-jdk-jammy

ENV LANG=C.UTF-8
ENV LANGUAGE=C.UTF-8
ENV LC_ALL=C.UTF-8

WORKDIR /app

RUN apt-get update && \
    apt-get install -y netcat-openbsd && \
    rm -rf /var/lib/apt/lists/*

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
COPY wait-for-oracle.sh wait-for-oracle.sh
RUN chmod +x wait-for-oracle.sh

# ... (después de copiar el script)
COPY wait-for-oracle.sh wait-for-oracle.sh
RUN apt-get update && apt-get install -y dos2unix && dos2unix wait-for-oracle.sh
RUN chmod +x wait-for-oracle.sh
# ...

RUN ./mvnw clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8

EXPOSE 8080
ENTRYPOINT ["sh", "./wait-for-oracle.sh", "oracle-db", "1521", "java", "-jar", "target/carrito-0.0.1-SNAPSHOT.jar"]
