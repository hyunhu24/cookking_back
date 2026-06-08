# 1단계: Gradle로 jar 빌드
FROM eclipse-temurin:11-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew bootJar -x test --no-daemon

# 2단계: 빌드된 jar만 실행 이미지에 복사
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar
# Railway가 주입하는 PORT 환경변수로 바인딩 (application-server.yml에서 처리)
ENTRYPOINT ["java","-jar","/app/app.jar"]
