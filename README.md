# RocketMQ Sample
rocketmq-docker-sample for 4.0.0-incubating version

+ rocketmq-namesrv: RocketMQ Name Server
+ rocketmq-broker-a-m: RocketMQ Broker
+ pub-service: Publish & Subcribe Messages

### Usage:

#### docker-compose:
```
docker-compose up -d
```

### Optional:

#### Java runtime memory size settings
* [runserver.sh](base/apache-rocketmq-all/bin/runserver.sh)
* [runbroker.sh](base/apache-rocketmq-all/bin/runbroker.sh)
* [tools.sh](base/apache-rocketmq-all/bin/tools.sh)

#### BrokerIp and ListenPort settings
* [docker-compose.yml](docker-compose.yml)

### Testing Pub Sub Service

#### Testing Pub&Sub Service
http://localhost:9909/pubsub-messages

#### Testing Publish Mesage
http://localhost:9909/pub-message

### Reference
[RockerMQ Docker Sample - Github](https://github.com/jingxizheng/rocketmq-docker-sample)