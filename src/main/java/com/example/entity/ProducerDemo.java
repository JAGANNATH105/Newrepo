//package com.example.entity;
//
//import com.example.dto.Emp;
//import jakarta.inject.Singleton;
//import jdk.jfr.Enabled;
//import org.apache.kafka.clients.producer.*;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//import java.util.concurrent.ExecutionException;
//
///**
// * kafka producer class
// */
//@Singleton
//@Service
//public class ProducerDemo {
//    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class);
//
//    /**
//     *
//     * @param topic name of kafka topic
//     * @param value message of kafka
//     * @param key kafka key
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    public static void createProducer(String topic, String value, String key) throws ExecutionException, InterruptedException {
//        log.info("I am a Kafka Producer");
//        String bootstrapServers = "localhost:9092";
//
//        // create Producer properties
//        Properties properties = new Properties();
//        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//
//        // create the producer
//        KafkaProducer<String, String> first_producer = new KafkaProducer<>(properties);
//
//        // create a producer record
//        ProducerRecord<String, String> producerRecord =
//                new ProducerRecord<>(topic, key, value);
//
//        first_producer.send(producerRecord, new Callback() {
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                if (e == null) {
//                    log.info("Successfully received the details as: \n" +
//                            "Topic:" + recordMetadata.topic() + "\n" +
//                            "Partition:" + recordMetadata.partition() + "\n" +
//                            "Offset" + recordMetadata.offset() + "\n" +
//                            "Timestamp" + recordMetadata.timestamp());
//                } else {
//                    log.error("Can't produce,getting error", e);
//
//                }
//            }
//        }).get();
//
//        // flush data - synchronous
//        first_producer.flush();
//        // flush and close producer
//        first_producer.close();
//
//    }
//
//}