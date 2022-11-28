package com.example.controller;

import com.example.dto.Request;
import com.example.entity.CustomResponse;
import com.example.entity.Employees;
import com.example.kafka.producer.KafkaProducer;
import com.example.service.EmployeeServiceImplementation;
import com.example.service.ListOperation;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 * Employee controller endpoints
 */

@Controller("/employee")
public class EmployeeController {
//    @Inject
//    Consumer consumer;

    @Inject
    EmployeeServiceImplementation employeeServiceImplementation;
//    @Inject
//    ProducerDemo producerDemo;
    @Inject
    ListOperation listOperation;
    @Inject
    KafkaProducer kafkaProducer;

    /***
     *
     * @param employees
     * @return object of Employee
     * @throws ExecutionException
     * @throws InterruptedException
     */

    @Post("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> AddEmployee(@Valid @Body Employees employees) throws ExecutionException, InterruptedException {
        return HttpResponse.ok(new CustomResponse(employeeServiceImplementation.addemployee(employees)));
    }


    /***
     *
     * @return List of employee
     */

    @Get("/show")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<Employees>> getEmployee() {
        List<Employees> act = employeeServiceImplementation.getAllEmployee();

        if (act.size() >= 0) {
            return HttpResponse.ok().body(act);
        }
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Get("/show/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Employees> getEmployeeById(@PathVariable int id){
        Employees act = employeeServiceImplementation.findById(id);

        if(act != null){
            return HttpResponse.ok().body(act);
        }
        else
            return HttpResponse.status(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param id employee_id
     * @return Employee deleted successfully
     */

    @Delete("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> deleteEmployeeById(@PathVariable int id){
        return HttpResponse.ok(new CustomResponse(employeeServiceImplementation.deleteById(id)));
    }

    /***
     *
     * @param employees object of employee
     * @param id employee_id
     * @return employee object
     */

    @Put("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> updateEmployeeById(@Valid @Body Employees employees,@PathVariable int id){
        return HttpResponse.ok(new CustomResponse(employeeServiceImplementation.updateEmployee(employees,id)));
    }
//    @Get("/consumer")
//    public void show() {
//        consumer.Consumer();
//    }

    @Get("/showlist")
    public List<String> showList() throws ExecutionException, InterruptedException {
        return listOperation.showlist();
    }
    @Post("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> insertion(@Body Request element) throws ExecutionException, InterruptedException {
        return listOperation.insert(element.getName());
    }
    @Post("/remove/{position}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> deletion(@PathVariable int position) {
        return listOperation.remove(position);
    }
    @Post("/increment/{incrementNumber}/{incrementIndex}")
    @Produces(MediaType.APPLICATION_JSON)
    public String increment(@PathVariable int incrementNumber, @PathVariable int incrementIndex) {
        return listOperation.increment(incrementNumber, incrementIndex);
    }
    @Get("/sort")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> sort() {
        return listOperation.sort();
    }

    @Post( "/send")
    public String produce(@Valid @Body Employees employees)
    {
        kafkaProducer.send("EMPLOYEE_TOPIC",employees);
        return "Successfully  Sent the object to EMPLOYEE_TOPIC";
    }
}
