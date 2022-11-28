package com.example.controller;


import com.aerospike.client.Record;
import com.example.service.ListOperation;
import com.example.service.ListService;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * List controller endpoints
 */
@Controller("/list")
public class ListOperationsController {

    @Inject
    ListOperation listOperation;
    @Inject
    ListService listService;


//    @Inject
//    Consumer consumer1;

    /***
     *
     * @param element String type of element
     * @return List of element
     * @throws ExecutionException
     * @throws InterruptedException
     */



    /**
     *
     * @return sorted list element
     */

    @Get("/list/sort")
    public List<String> sort() {
        return listOperation.sort();
    }

    /**
     *
     * @return List of element
     * @throws ExecutionException
     * @throws InterruptedException
     */

    @Get("/showlist")
    public List<String> showList() throws ExecutionException, InterruptedException {
        return listOperation.showlist();
    }

    /**
     *
     * @param position index number of string which we have to deleted
     * @return List of element
     */

    @Post("/list/remove/{position}")
    public List<String> deletion(@PathVariable int position) {
        return listOperation.remove(position);
    }

    /**
     *
     * @param incrementNumber increment number which we want to add on that number
     * @param incrementIndex index of that number
     * @return String of integer element
     */
    @Post("/increment/{incrementNumber}/{incrementIndex}")
    public String increment(@PathVariable int incrementNumber, @PathVariable int incrementIndex) {
        return listOperation.increment(incrementNumber, incrementIndex);
    }

    /**
     *
     * @return records by key
     */

    @Get("/records")
    public Record getRecord() {
        return listService.getRecordByKey();

    }

    /**
     *
     * @param appendNumber integer number which we want to append
     * @return
     */
    @Post("/list/append/{appendNumber}")
    public String append(@PathVariable int appendNumber) {
        return listOperation.append(appendNumber);
    }

    /**
     * Show the console of consumer
     */
//    @Get("/consumer")
//    public void show() {
//        consumer1.Consumer();
//    }

}