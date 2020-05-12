package xmu.middleware.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmu.middleware.kafka.producer.KafkaProducer;

import javax.annotation.Resource;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource(name="kafkaProducer")
    private KafkaProducer producer;

    /**
     * 测试使用topic
      */
    private final String TOPIC = "middleware";

    @RequestMapping("/send")
    public String send(String data){
        producer.sendMessage(TOPIC, data);

        return "发送数据【" + data + "】成功！";
    }
}
