package xmu.middleware.kafka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmu.middleware.kafka.producer.KafkaProducer;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource(name = "kafkaProducer")
    private KafkaProducer producer;

    /**
     * 测试使用topic
     */
    private final String TOPIC = "middleware";
    private final String topic1 = "single";
    private final String topic2 = "batch";

    /**
     * 单条生产单条消费
     *
     * @param data data
     * @return string
     */
    @RequestMapping("/send/single")
    public String send(String data) {
        producer.sendMessage(topic1, data);

        return "发送数据【" + data + "】成功！";
    }

    /**
     * 批量生产批量消费
     * @return
     */
    @RequestMapping("/send/batch")
    public String sendBatch0() {

        List<String> producerRecord = new LinkedList<>();
        // 设计一个消息模板
        String msgTemplate = "this is massage";
        Integer cnt = 0;
        while (cnt < 100) {
            // 生成100条 模板+数字序号 格式的消息
            producerRecord.add(msgTemplate + cnt.toString());
            cnt++;
        }
        // 批量生产
        producer.sendMessage(topic2, producerRecord);

        return "发送数据【" + producerRecord + "】成功！";
    }

}
