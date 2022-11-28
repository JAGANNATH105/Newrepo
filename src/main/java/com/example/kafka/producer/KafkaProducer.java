package com.example.kafka.producer;

import com.example.entity.Employees;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;



@KafkaClient
public interface KafkaProducer {
    public void send(@Topic String topic, Employees employees);

}
