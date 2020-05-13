package xmu.middleware.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * 消息生产者
 *
 * @author Jason
 */
@Component("kafkaProducer")
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 使用KafkaTemplate向Kafka推送数据
     *
     * @param topic topic
     * @param value massage
     */
    public void sendMessage(String topic, String value) {
        ListenableFuture<SendResult<String, String>> resultListenableFuture = kafkaTemplate.send(topic, value);
        resultListenableFuture.addCallback(
                successCallback -> logger.info("发送成功：topic= " + topic + " value= " + value),
                failureCallback -> logger.info("发送失败：topic= " + topic + " value= " + value));
    }

    public void sendMessage(String topic, List<String> msgList) {
        msgList.forEach(msg -> sendMessage(topic, msg));
    }
}
