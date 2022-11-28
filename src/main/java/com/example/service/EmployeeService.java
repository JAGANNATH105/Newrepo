package com.example.service;

import com.example.entity.Employees;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface EmployeeService {
    public String addemployee(Employees employees) throws ExecutionException, InterruptedException;


    public List<Employees> getAllEmployee();

    public Employees findById(int id);

    public String updateEmployee(Employees employees, int id);
    public String deleteById(int id);
}
