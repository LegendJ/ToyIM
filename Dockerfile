#
# Java 1.8 & Maven Dockerfile
FROM maven:3-jdk-8

MAINTAINER just@Netease "Legendj228@gmail.com"

# 添加卷
VOLUME /tmp

ADD ./toyim-0.0.1-SNAPSHOT.jar app.jar

# tomcat
EXPOSE 8081
# netty port
EXPOSE 2333
ENV JAVA_OPTS=""
# 执行jar启动服务
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]