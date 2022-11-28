package com.example.service;

import com.aerospike.client.Record;
import com.aerospike.client.*;
import com.aerospike.client.cdt.ListSortFlags;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.InfoPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.QueryPolicy;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Class of ListOperation
 */
@Singleton
public class ListOperation {
//    @Inject
//    ProducerDemo producerDemo;


    AerospikeClient client = new AerospikeClient("localhost", 3000);
    Integer theKey = 0;
    String listSet = "listset1";
    String listNamespace = "test";

    Key key = new Key(listNamespace, listSet, theKey);
    ClientPolicy clientPolicy = new ClientPolicy();
    InfoPolicy infoPolicy = new InfoPolicy();

    String listStrBinName = "liststrbin";
    String listIntBinName = "listintbin";
    Bin pk = new Bin("pk", 0);

    /**
     *
     * @param element String type of element
     * @return list of element
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> insert(String element) throws ExecutionException, InterruptedException {
        String key1 = "id_";

        int firstposition = 0;

        Record origRecord = client.get(null, key);
        System.out.println("Before – " + origRecord.getValue(listStrBinName));

        client.operate(client.writePolicyDefault, key,
                com.aerospike.client.cdt.ListOperation.insert(listStrBinName, firstposition, Value.get(element))
        );
//        ProducerDemo.createProducer(String.valueOf(KafkaTopic.EMPLOYEE_TOPIC), element, key1);
        Record finalRecord = client.get(null, key);
        return (List<String>) finalRecord.getValue(listStrBinName);
    }

    /**
     *
     * @param incrementNumber number which we want to add in existing number
     * @param incrementIndex index number of that element which we want to increment
     * @return String of integer elemet
     */
    public String increment(int incrementNumber, int incrementIndex) {
        Key key = new Key(listNamespace, listSet, theKey);
        Record origRecord = client.get(null, key);
        System.out.println("Before – " + origRecord.getValue(listIntBinName));

        client.operate(client.writePolicyDefault, key,
                com.aerospike.client.cdt.ListOperation.increment(listIntBinName, incrementIndex, Value.get(incrementNumber))
        );

        Record finalRecord = client.get(null, key);
        System.out.println(" After – " + finalRecord.getValue(listIntBinName));
        return finalRecord.getValue(listIntBinName).toString();

    }

    /**
     *
     * @param Number number which we want to append
     * @return String of integer elemet
     */
    public String append(int Number) {
        Key key = new Key(listNamespace, listSet, theKey);
        Record origRecord = client.get(null, key);
        System.out.println("Before – " + origRecord.getValue(listIntBinName));

        client.operate(client.writePolicyDefault, key,
                com.aerospike.client.cdt.ListOperation.append(listIntBinName, Value.get(Number))
        );

        Record finalRecord = client.get(null, key);
        System.out.println(" After – " + finalRecord.getValue(listIntBinName));
        return finalRecord.getValue(listIntBinName).toString();

    }

    /**
     *
     * @param i index number of String list element
     * @return list of string
     */
    public List<String> remove(int i) {
        client.operate(client.writePolicyDefault, key,
                com.aerospike.client.cdt.ListOperation.remove(listStrBinName, i)
        );


        Record finalRecord = client.get(null, key);
        System.out.println(" After – " + finalRecord.getValue(listStrBinName));
        return (List<String>) finalRecord.getValue(listStrBinName);

    }

    /**
     *
     * @return list of string
     */
    public List<String> sort() {
        List<String> liststr = new ArrayList<>();
        try {
            client.operate(client.writePolicyDefault, key, com.aerospike.client.cdt.ListOperation.sort(listStrBinName, ListSortFlags.DEFAULT));

            Record frecord = client.get(null, key);
            liststr = (List<String>) frecord.getValue(listStrBinName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            liststr = null;
        }
        return liststr;
    }

    /**
     *
     * @return list of String
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> showlist() throws ExecutionException, InterruptedException {
        Record record = client.get(null, key);

        String value = record.getValue(listStrBinName).toString();
        String key = "id";//+Integer.toString(0);
//        producerDemo.createProducer(String.valueOf(KafkaTopic.EMPLOYEE_TOPIC), value, key);


        return (List<String>) record.getValue(listStrBinName);
    }

    /**
     *
     * @return fecthed record by key
     */
    public Record getRecords() {
        List<String> records = new ArrayList<>();
        Policy policy = new Policy();
        policy.sendKey = true;
        QueryPolicy queryPolicy = new QueryPolicy();
        Statement statement = new Statement();
        statement.setNamespace("test");
        statement.setSetName("listset1");
        statement.setFilter(Filter.equal("pk", 0));
        RecordSet record = client.query(queryPolicy, statement);
        System.out.println(record.getRecord());
        return record.getRecord();
    }


}