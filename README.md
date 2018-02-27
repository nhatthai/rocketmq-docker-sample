# RocketMQ Sample
rocketmq-docker-sample for 4.0.0-incubating version

+ rocketmq-namesrv: RocketMQ Name Server
+ rocketmq-broker-a-m: RocketMQ Broker
+ pub-service: Publish & Subscribe Messages
+ rocketmq-console: UI for visualization Pub & Sub

### Usage:

#### Pub Service
```
$ cd pub-service
$ mvn clean package
```

#### RocketMQ Console
```
$ cd rocketmq-console
$ mvn clean package
```

#### docker-compose:
```
docker-compose up -d
```

### Testing Pub Sub Service

#### Testing Pub & Sub Service
http://localhost:9909/pubsub-messages

#### Testing Publish Mesage
http://localhost:9909/pub-message

#### RocketMQ Console
http://localhost:8080

### Reference
[RockerMQ Docker Sample - Github](https://github.com/jingxizheng/rocketmq-docker-sample)
