# 基础镜像：仓库是openjdk，tag:8-jdk-alpine
FROM openjdk:8-jdk-alpine
MAINTAINER zhengfc zhengfc323@gmail.com

# 添加/bin/bash
# RUN apk add --no-cache \
#    bash \
#    su-exec

VOLUME /tmp
ADD target/monitor-0.1.RELEASE.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]