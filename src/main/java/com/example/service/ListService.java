package com.example.service;

import com.aerospike.client.Record;
import com.aerospike.client.*;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListSortFlags;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.IndexType;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
@Singleton
public class ListService {
    AerospikeClient client = new AerospikeClient("localhost", 3000);
    ClientPolicy clientPolicy = new ClientPolicy();


    public String addList() {
        String message = "";
        try {
            List<String> listStr = new ArrayList<String>();
            listStr.add("Grapes");
            listStr.add("Lychee");
            listStr.add("Cherry");
            listStr.add("Apple");

            Key key = new Key("test", "listset", 1);

            Bin bin1 = new Bin("index", 1);
            Bin bin2 = new Bin("list", listStr);

            WritePolicy policy = new WritePolicy();
            policy.sendKey = true;

            client.createIndex(new Policy(), "test", "listset", "index_1",
                    "index", IndexType.NUMERIC);
            client.put(policy, key, bin1, bin2);
//            client.createIndex(policy, "test", "listset1", "s_index", String.valueOf(key), IndexType.NUMERIC);
            message = "List added successfully";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return "List added successfully";
    }

    public List<String> retrieve(int key) {
        Key newKey = new Key("test", "listset", key);
        Record record = client.get(null, newKey);
        return (List<String>) record.getValue("list");
    }

    public Record getRecordByKey() {
        try {
            List<String> records = new ArrayList<>();

            Policy policy = new Policy();
            policy.sendKey = true;

            Statement statement = new Statement();
            statement.setNamespace("test");
            statement.setSetName("listset");

            statement.setFilter(Filter.range("index", 0, 10));

            RecordSet record = client.query(null, statement);


            if (record.next()) {
                System.out.println(record.getKey());
                System.out.println(record.getRecord());
                return record.getRecord();
            } else {
                return record.getRecord();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> order(int key) {
        List<String> list = new ArrayList<>();
        try {
            Key newKey = new Key("test", "listset", key);

            client.operate(client.writePolicyDefault, newKey,               //sorting logic
                    ListOperation.sort("list", ListSortFlags.DEFAULT)
            );
            Record finalRecord = client.get(null, newKey);

            list = (List<String>) finalRecord.getValue("list");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            list = null;
        }
        return list;
    }

    public String insertString(String fruit, int key1) {
        String result = "";
        try {
            Key newKey = new Key("test", "listset", key1);

            Record origRecord = client.get(null, newKey);

            client.operate(client.writePolicyDefault, newKey,
                    ListOperation.insert("list", -1, Value.get(fruit))
            );
            result = "Inserted successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = "Insertion failed";
        }
        return result;
    }
}

