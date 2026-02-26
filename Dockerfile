# -------- BUILD STAGE --------
FROM maven:3.9.9-eclipse-temurin-22 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# -------- RUNTIME STAGE --------
FROM tomcat:10.1-jdk22-temurin

# Remove apps padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado
COPY --from=build /app/target/taskpilot.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
