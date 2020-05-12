package xmu.middleware.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * 消息消费者
 * @author Jason
 */
@Component("kafkaConsumer")
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(id="test",topics={"middleware"})
    public void listen(String data){
        System.out.println("KafkaConsumer收到消息：" + data);
        logger.info(MessageFormat.format("KafkaConsumer收到消息：{0}", data));
    }
}
