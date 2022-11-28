package com.example.kafka.consumer;

import com.example.entity.Employees;
import com.example.repository.EmployeeRepository;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class KafkaConsumer {
    @Inject
    EmployeeRepository employeeRepository;

    /**
     * @param employees
     */
    @Topic("EMPLOYEE_TOPIC")
    public  void Consumes(Employees employees)
    {
        employeeRepository.addemployee(employees);
    }
}
