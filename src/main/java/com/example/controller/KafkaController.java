package com.example.controller;

import com.example.entity.Employees;
import com.example.kafka.producer.KafkaProducer;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.springframework.stereotype.Controller;

@Controller("/kafka")
public class KafkaController {
    @Inject
    KafkaProducer kafkaProducer;
    @Post( "/send")
    public String produce(@Body Employees employees)
    {
        kafkaProducer.send("EMPLOYEE_TOPIC",employees);
        return "object produces";
    }
}
