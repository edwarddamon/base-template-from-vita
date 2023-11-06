#!/bin/bash

# 必须在项目根本录下执行!!!
# 定义要处理的项目列表
modules=(
  "vita-api"
  "vita-auth-center"
  "vita-gateway"
)

# 定义目标远程仓库的地址 && 标签后缀
remoteRepository="ccr.ccs.tencentyun.com"
namespace="vita_namespace"
tagSuffix=`date + %Y%m%d_%H%M`

# echo 颜色和加粗
echoPrefix="\033[32m"
echoSuffix="\033[0m"

for projectName in "${modules[@]}"
do
  # 构建本地镜像
  imageName="${projectName}:${tagSuffix}"
  docker build -t "${imageName}" "./${projectName}"
  echo -e "${echoPrefix} [docker-deploy] >>>>> [${imageName}] 本地镜像构建成功! ${echoSuffix}"

  # 打标签 && 推送镜像到远程仓库
  remoteImage="${remoteRepository}/${namespace}/${projectName}:${tagSuffix}"
  docker tag "${imageName}" "${remoteImage}"
  docker push "${remoteImage}"
  echo -e "${echoPrefix} [docker-deploy] >>>>> [${imageName}] 镜像推送到 [${remoteImage}] 成功! ${echoSuffix}"

  docker rmi ${imageName}
  docker rmi ${remoteImage}
  echo -e "${echoPrefix}  [docker-deploy] >>>>> [${imageName}] 和 [${remoteImage}] 镜像删除成功! ${echoSuffix}"
done

echo -e "${echoPrefix}  [docker-deploy] >>>>> 所有服务推送成功! ${echoSuffix}"
