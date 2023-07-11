package com.adsh.kafkaspringboot.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "my-replicated-topic", groupId = "myGroup1")
    public void listenGroup(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(value);
        System.out.println(record);

        ack.acknowledge();
    }

    @KafkaListener(groupId = "myGroup1", topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
            @TopicPartition(topic = "topic2", partitions = "0",
                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "1"))
    }, concurrency = "3")
    //Number of consumers under the same group,
    // recommended to be less than or equal to the number of partitions
    public void listenGroupPro(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(value);
        System.out.println(record);

        ack.acknowledge();
    }
}
