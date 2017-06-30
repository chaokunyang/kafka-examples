package com.timeyang.confluent.consumer;

import io.confluent.kafka.serializers.KafkaAvroDecoder;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.utils.VerifiableProperties;
import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.common.errors.SerializationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author yangck
 */
public class OldConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "group2");
        props.put("schema.registry.url", "http://localhost:8081");

        String topic = "topic1";
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);

        VerifiableProperties vProps = new VerifiableProperties(props);
        KafkaAvroDecoder keyDecoder = new KafkaAvroDecoder(vProps);
        KafkaAvroDecoder valueDecoder = new KafkaAvroDecoder(vProps);

        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));

        Map<String, List<KafkaStream<Object, Object>>> consumerMap = consumer.createMessageStreams(
                topicCountMap, keyDecoder, valueDecoder);
        KafkaStream stream = consumerMap.get(topic).get(0);
        ConsumerIterator it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata messageAndMetadata = it.next();
            try {
                String key = (String) messageAndMetadata.key();
                IndexedRecord value = (IndexedRecord) messageAndMetadata.message();
                System.out.printf("key: %s, value: %s", key, value);
                System.out.println();
                // ...
            } catch(SerializationException e) {
                // may need to do something with it
            }
        }
    }
}
