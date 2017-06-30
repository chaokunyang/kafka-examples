package com.timeyang.confluent.producer;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author yangck
 */
public class AvroProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9292");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put("schema.registry.url", "http://localhost:8081");
        KafkaProducer producer = new KafkaProducer(props);

        String key = "key1";
        String userSchema = "{\"type\":\"record\"," +
                "\"name\":\"myrecord\"," +
                "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(userSchema);
        GenericRecord avroRecord = new GenericData.Record(schema);
        avroRecord.put("f1", "value1");

        ProducerRecord<Object, Object> record = new ProducerRecord<>("topic1", key, avroRecord);
        try {

            System.out.println("------------callback-------------");
            for(int i = 0; i < 1000; i++) {
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.format("topic: %s, partition: %s, offset: %s, checksum: %s, timestamp: %s",
                                metadata.topic(), metadata.partition(), metadata.offset(), metadata.checksum(), metadata.timestamp());
                        System.out.println();
                    }
                });
            }

            System.out.println(producer.send(record).get());

        } catch(SerializationException e) {
            // may need to do something with it
        }

        Thread.sleep(2000); // 等待生产者回调被调用
        producer.close();
    }

}
