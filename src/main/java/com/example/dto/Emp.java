package com.example.dto;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.luaj.vm2.ast.Str;

import java.io.Serializable;
import java.util.Map;

public class Emp implements Serializable, Serializer, Deserializer {

    Integer id ;
    String empname;

    public Emp(Integer id, String empname) {
        this.id = id;
        this.empname = empname;
    }

    public Emp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
    public void configure(Map configs, boolean isKey) {

    }

    public byte[] serialize(String s, Object o) {
        return new byte[0];
    }

    public byte[] serialize(String topic, Headers headers, Object data) {
        return new byte[0];
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return null;
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    public void close() {

    }
}
