package xmu.middleware.kafka.consumer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 读取类
 *
 * @author Jason
 */
@Configuration
@Data
public class KafkaConsumerProps {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String defaultGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String enableAutoCommit;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private String autoCommitInterval;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;

    public KafkaConsumerProps() {
    }
}
