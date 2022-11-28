package com.example.repository;

import com.example.entity.Employees;
import io.micronaut.context.annotation.Bean;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Bean
public interface EmployeeRepository  {
    public String addemployee(Employees employees);


    public List<Employees> getAllEmployee();

    public Employees findById(int id);

    public String updateEmployee(Employees employees, int id);
    public String deleteById(int id);
}