package com.example.repository;

import com.example.Configuration.aeroMapperConfig;
import com.example.entity.Employees;
import jakarta.inject.Inject;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Inject
    aeroMapperConfig mapper;


    @Override
    public String addemployee(Employees employees) {
        mapper.getMapper().save(employees);
        return "Employee saved successfully..!=";
    }

    @Override
    public List<Employees> getAllEmployee() {
        return mapper.getMapper().scan(Employees.class);
    }

    @Override
    public Employees findById(int id) {
        return mapper.getMapper().read(Employees.class, id);

    }

    @Override
    public String updateEmployee(Employees employees, int id) {
        Employees a=mapper.getMapper().read(Employees.class,id);
        a.setaName(employees.getaName());
        a.setaEmail(employees.getaEmail());
        a.setaDob(employees.getaDob());
        a.setaSal(employees.getaSal());
        mapper.getMapper().save(a);
        return "Employee Updated..!="+a.getId();
    }

    @Override
    public String deleteById(int id) {
        mapper.getMapper().delete(Employees.class,id);

        return "Employee Deleted By Id..!="+id;
    }
}
