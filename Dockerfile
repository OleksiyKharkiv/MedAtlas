# Используем образ eclipse-temurin:21-jdk
FROM eclipse-temurin:21-jdk

# Устанавливаем необходимые пакеты
RUN apt-get update \
    && apt-get install -y ca-certificates curl git --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

# Устанавливаем переменные среды Maven
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG /root/.m2

# Копируем настройки Maven
COPY --from=maven:3.9.7-eclipse-temurin-11 /usr/share/maven /usr/share/maven
COPY --from=maven:3.9.7-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.7-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

# Устанавливаем символическую ссылку для mvn
RUN ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# Задаем точку входа и команду по умолчанию для Maven
ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn"]