FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY src/Main.java src/WebServer.java ./src/
COPY src/model/ ./src/model/
COPY src/exception/ ./src/exception/
COPY src/payment/ ./src/payment/
COPY src/service/ ./src/service/
COPY src/ui/ ./src/ui/

WORKDIR /app/src
RUN javac Main.java WebServer.java model/*.java exception/*.java payment/*.java service/*.java

ENV PORT=8080
EXPOSE 8080
CMD ["java", "Main", "--web"]