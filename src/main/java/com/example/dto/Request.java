package com.example.dto;

public class Request {

    String name;

    public Request(String name) {
        this.name = name;
    }

    public Request() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
