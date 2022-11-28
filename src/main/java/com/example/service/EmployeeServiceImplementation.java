package com.example.service;

import com.example.Configuration.aeroMapperConfig;
import com.example.entity.Employees;
import com.example.repository.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Singleton
public class EmployeeServiceImplementation implements EmployeeService{
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    aeroMapperConfig mapper;
//    @Inject
//    ProducerDemo producerDemo;

    /**
     *
     * @param employees object of employee from controller
     * @return employee type object
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public String addemployee(Employees employees) throws ExecutionException, InterruptedException {
        employeeRepository.addemployee(employees);
//        final Object BOOTSTRAP_SERVERS = "localhost:9092";
//        final String TOPIC = "EMPLOYEE_TOPIC";
//        String key1 = "id_";
//        String emp =employees.getId()+","+employees.getaName();
//
//
//
//        producerDemo.createProducer(String.valueOf(KafkaTopic.EMPLOYEE_TOPIC), emp, key1);

        return "Employee saved successfully..!=";
    }

    /**
     *
     * @return List of employee
     */
    @Override
    public List<Employees> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

    @Override
    public Employees findById(int id) {
        return employeeRepository.findById(id);
    }

    /**
     *
     * @param id employee_id by which we want to update employee
     * @param employees employee object
     * @return employee type of object
     */
    @Override
    public String updateEmployee(Employees employees, int id) {
        Employees a=employeeRepository.findById(id);
        a.setaName(employees.getaName());
        a.setaEmail(employees.getaEmail());
        a.setaDob(employees.getaDob());
        a.setaSal(employees.getaSal());
        return "Employee Updated..!="+a.getId();
    }
    /**
     *
     * @param id employee_id which we want to deleted
     * @return employee deleted successfully
     */
    @Override
    public String deleteById(int id) {
        employeeRepository.deleteById(id);
        return "Employee Deleted By Id..!="+id;

    }
}
