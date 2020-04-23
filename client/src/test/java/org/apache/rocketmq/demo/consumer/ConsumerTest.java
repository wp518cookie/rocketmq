package org.apache.rocketmq.demo.consumer;

import org.apache.log4j.PropertyConfigurator;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author wuping
 * @date 2020-04-20
 */

public class ConsumerTest {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        consumeSyncMessage();
    }

    private static void consumeSyncMessage() throws InterruptedException, MQClientException {
        PropertyConfigurator.configure("/Users/wp/Documents/code/source-code-new/mq/rocketmq/client/src/test/resources/log4j.properties");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketmq_consumer_demo");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("134.175.57.59:9876");

        consumer.subscribe("SyncTopic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(msgs.size());
                MessageExt messageExt = msgs.get(0);
                System.out.println(messageExt);
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer started.%n");
    }
}
