
FROM gradle:8.9-jdk17-alpine AS build
WORKDIR /home/gradle/project
COPY services/profile /home/gradle/project
RUN gradle clean shadowJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/profile-*-all.jar /app/app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
CMD ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
