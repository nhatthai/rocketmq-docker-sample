#!/bin/bash
sudo docker run -d -p 9876:9876 --name rmqnamesrv rocketmq-namesrv-image:4.0.0-incubating
sudo docker run -d -p 10911:10911 -p 10909:10909 --name rmqbroker --link rmqnamesrv:rmqnamesrv -e "NAMESRV_ADDR=rmqnamesrv:9876" rocketmq-broker-a-m-image:4.0.0-incubating
sudo docker run -d -p 8080:8080 --name rocketmq-console --link rmqnamesrv:rmqnamesrv --link rmqbroker:rmqbroker -e "ROCKETMQ_CONFIG_NAMESRV_ADDR=127.0.0.1:9876" rocketmq-console:latest
