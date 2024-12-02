FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY ./target/persona-contenedor-1.0-SNAPSHOT.jar persona-contenedor.jar
COPY create-devsu-user-and-db.sh /app/create-devsu-user-and-db.sh
RUN chmod +x /app/create-devsu-user-and-db.sh
EXPOSE 8181
ENTRYPOINT ["bash", "-c", "/app/create-devsu-user-and-db.sh && java -jar /app/persona-contenedor.jar"]
