# 1단계: Gradle로 jar 빌드 (gradle 이미 설치된 이미지 사용 → wrapper 다운로드 불필요)
FROM gradle:7.6.1-jdk11 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar -x test --no-daemon

# 2단계: 빌드된 jar만 실행 이미지에 복사
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar
# Railway가 주입하는 PORT 환경변수로 바인딩 (application-server.yml에서 처리)
ENTRYPOINT ["java","-jar","/app/app.jar"]
