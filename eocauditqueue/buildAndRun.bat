@echo off
call mvn clean package
call docker build -t co.com.claro.inspira/eocauditqueue .
call docker rm -f eocauditqueue
call docker run -d -p 9080:9080 -p 9443:9443 --name eocauditqueue co.com.claro.inspira/eocauditqueue