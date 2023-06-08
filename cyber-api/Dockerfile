FROM williamyeh/java8

MAINTAINER Edward

ENV PRO_NAME="cyber"
ENV JAVA_OPTS="-Xms512m -Xmx512m"
ENV ACTIVE_PROFILE="test"

# 镜像时区设置为东八区
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone

# 创建一个目录存放jar包
RUN mkdir -p /opt/${PRO_NAME}

# 设置开放端口号
EXPOSE 8023

# 添加jar包，存放路径以及重命名
ADD target/*.jar /opt/${PRO_NAME}/cyber-api.jar

# 添加进入docker容器后的目录
WORKDIR /opt/${PRO_NAME}

ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -jar -Dspring.profiles.active=${ACTIVE_PROFILE} /opt/${PRO_NAME}/cyber-api.jar"]

# 启动命令
# docker run -d -p 8023:8023 -e "JAVA_OPTS=-Xms125m -Xmx512m" -e "ACTIVE_PROFILE=dev" -v /opt/cyber/logs:/opt/cyber/logs/ --name cyber-api 镜像ID