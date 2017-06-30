package com.timeyang.kafka.demo;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author chaokunyang
 * @create 2017-06-09 13:49
 */
public class ProducerExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 1000; i++) {
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
        }

        System.out.println("------------callback-------------");
        for(int i = 0; i < 1000; i++) {
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)), (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.format("topic: %s, partition: %s, offset: %s, checksum: %s, timestamp: %s",
                            metadata.topic(), metadata.partition(), metadata.offset(), metadata.checksum(), metadata.timestamp());
                    System.out.println();
                }
            });
        }

        System.out.println("------------partition order-------------");
        // Callbacks for records being sent to the same partition are guaranteed to execute in order. That is, in the following example callback1 is guaranteed to execute before callback2:
        producer.send(new ProducerRecord<>("my-topic-2", 1, "key1", "value1"), (metadata, exception) -> {
            System.out.format("topic: %s, partition: %s, offset: %s, checksum: %s, timestamp: %s",
                    metadata.topic(), metadata.partition(), metadata.offset(), metadata.checksum(), metadata.timestamp());
            System.out.println();
        });
        producer.send(new ProducerRecord<>("my-topic-2", 1, "key2", "value2"), (metadata, exception) -> {
            System.out.format("topic: %s, partition: %s, offset: %s, checksum: %s, timestamp: %s",
                    metadata.topic(), metadata.partition(), metadata.offset(), metadata.checksum(), metadata.timestamp());
            System.out.println();
        });
        // Note that callbacks will generally execute in the I/O thread of the producer and so should be reasonably fast or they will delay the sending of messages from other threads. If you want to execute blocking or computationally expensive callbacks it is recommended to use your own Executor in the callback body to parallelize processing.

        producer.close();
    }
}
