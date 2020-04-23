package org.apache.rocketmq.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author wuping
 * @date 2020-04-20
 */

@Slf4j
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("/Users/wp/Documents/code/source-code-new/mq/rocketmq/logappender/src/test/resources/log4j2.xml");
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq_demo");
        producer.setNamesrvAddr("134.175.57.59:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("SyncTopic", "TagA", ("Hello RocketMq " + i)
                    .getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
