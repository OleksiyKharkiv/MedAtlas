# Сборочный этап
FROM maven:3.8.5-openjdk-20-slim AS build

# Копируем pom.xml и исходный код
COPY pom.xml /build/pom.xml
COPY src /build/src
WORKDIR /build

# Собираем проект, пропуская тесты
RUN mvn clean package -DskipTests

# Выполняемый этап
FROM ubuntu:23.04

# Устанавливаем рабочую директорию
WORKDIR /app

# Обновляем пакеты и устанавливаем OpenJDK 20
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive \
    apt-get -y install openjdk-20-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Копируем собранный JAR файл из сборочного этапа
COPY --from=build /build/target/MedAtlas-0.0.1-SNAPSHOT.jar /app/MedAtlas-0.0.1-SNAPSHOT.jar

# Создаем пользователя и группу
RUN groupadd --gid 10001 javauser && useradd --uid 10001 --gid 10001 javauser
RUN chown -R javauser:javauser /app

# Переходим на пользователя javauser
USER javauser

# Открываем порт 8080
EXPOSE 8080

# Устанавливаем команду по умолчанию для запуска приложения
CMD ["java", "-Dnet.bytebuddy.experimental=true", "-jar", "MedAtlas-0.0.1-SNAPSHOT.jar"]