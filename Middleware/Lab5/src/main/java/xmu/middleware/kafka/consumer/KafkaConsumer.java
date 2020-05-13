package xmu.middleware.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

/**
 * 消息消费者
 *
 * @author Jason
 */
@Component("kafkaConsumer")
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ConsumerFactory consumerFactory;

    @KafkaListener(topics = {"single"}, containerFactory = "kafkaListenerContainerFactory0")
    public void kafkaConsumerTest(String msg) {
        logger.info("接收到消息--" + msg);
    }

    @KafkaListener(topics = {"batch"}, containerFactory = "kafkaListenerContainerFactory0")
    public void listenPartition0(List<ConsumerRecord<String, String>> records) {
        System.out.println(records.size());
        for (ConsumerRecord<String, String> consumerRecord : records) {
            String value = consumerRecord.value();
            logger.info("a 消息：partition " + consumerRecord.partition() + " value " + consumerRecord.value());
        }
    }

    @KafkaListener(topics = {"batch"}, containerFactory = "kafkaListenerContainerFactory1")
    public void listenPartition2(List<ConsumerRecord<String, String>> records) {
        System.out.println(records.size());
        for (ConsumerRecord<String, String> consumerRecord : records) {
            String value = consumerRecord.value();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("c 消息：partition " + consumerRecord.partition() + " value " + consumerRecord.value() + " thread id " + Thread.currentThread().getName());
        }
    }
}
