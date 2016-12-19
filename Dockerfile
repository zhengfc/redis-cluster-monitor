# 基础镜像：仓库是openjdk，tag:8-jdk-alpine
FROM openjdk:8-jdk-alpine
MAINTAINER zhengfc zhengfc323@gmail.com

ENV WORKDIR=/app
RUN mkdir -p $WORKDIR

# 将打包好的spring程序拷贝到容器中的指定位置
ADD target/monitor-0.1.RELEASE.jar $WORKDIR/monitor-0.1.RELEASE.jar

# 容器对外暴露7777端口
EXPOSE 7777

# 容器启动后需要执行的命令
WORKDIR $WORKDIR
CMD java -jar monitor-0.1.RELEASE.jar