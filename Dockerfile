# ---- etap 1: budowanie ----
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# najpierw same zależności — lepszy cache warstw
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# potem kod źródłowy
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---- etap 2: uruchomienie ----
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Headless Chromium dla serwerowego renderowania PDF (Playwright).
# Pobiera przeglądarkę do /ms-playwright i instaluje jej zależności systemowe.
# Uruchamiamy CLI Playwrighta z fat-jara przez Spring Boot PropertiesLauncher,
# więc nie potrzebujemy tu Mavena.
ENV PLAYWRIGHT_BROWSERS_PATH=/ms-playwright
RUN java -Dloader.main=com.microsoft.playwright.CLI \
        -cp app.jar org.springframework.boot.loader.launch.PropertiesLauncher \
        install --with-deps chromium \
    && rm -rf /var/lib/apt/lists/*

EXPOSE 8080
# MaxRAMPercentage pilnuje, żeby JVM nie zjadła całego RAM-u kontenera
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]