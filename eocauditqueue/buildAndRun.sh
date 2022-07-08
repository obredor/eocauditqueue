#!/bin/sh
mvn clean package && docker build -t co.com.claro.inspira/eocauditqueue .
docker rm -f eocauditqueue || true && docker run -d -p 9080:9080 -p 9443:9443 --name eocauditqueue co.com.claro.inspira/eocauditqueue