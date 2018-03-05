package example;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Name service controller.
 * Nhat Thai
 */
@RestController
public class PubController {
    private Logger logger = LoggerFactory.getLogger(PubController.class);
    private String testTopic = "TestTopic";

    @Resource
    private RMQConfigure rMQConfigure;

	@RequestMapping("/pub-message")
	public String publishMessage() throws MQClientException, InterruptedException {
        ProducerMsg pub = new ProducerMsg();
        pub.sendMessages();
        return "Done Publish Message";
    }

    @RequestMapping(value = "/pub-sub-message")
    public boolean runPubSubMessage() throws MQClientException, RemotingException, InterruptedException {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(testTopic + "Group");
            consumer.setNamesrvAddr(rMQConfigure.getNamesrvAddr());
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe(testTopic, "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                    ConsumeConcurrentlyContext context) {
                    logger.info("receiveMessage msgSize={}", msgs.size());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();

        } catch (Exception e) {}

        final DefaultMQProducer producer = new DefaultMQProducer(testTopic + "Group");
        producer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        producer.setNamesrvAddr(rMQConfigure.getNamesrvAddr());
        producer.start();

        new Thread(new Runnable() {

            @Override public void run() {

                try {
                    Message msg = new Message(testTopic,
                        "TagA", "KEYS",
                        ("Hello RocketMQ ").getBytes()
                    );
                    Thread.sleep(1000L);
                    SendResult sendResult = producer.send(msg);
                    System.out.printf("%s%n", sendResult);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception ignore) {
                    }
                }
            }
        }).start();
        return true;
    }
}
