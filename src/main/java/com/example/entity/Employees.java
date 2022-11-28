package com.example.entity;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class of employee
 */
@AerospikeRecord(namespace = "test",set = "Emp")
public class Employees {
    @AerospikeKey
    @AerospikeBin
    private int id;
    @AerospikeBin
    @NotBlank(message = "name should not be blank")
    @Length(min = 3,max = 20,message = "enter coorect name")
    private String aName;
    @AerospikeBin
    @Email(message = "Email should be valid")
    private String aEmail;
    @AerospikeBin
    @Min( 100 )
    private  int aSal;
    @AerospikeBin
    @NotNull(message = "please enter date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String aDob;



    public Employees( int id, String aName, String aEmail, int aSal, String aDob) {
        this.id = id;
        this.aName = aName;
        this.aEmail = aEmail;
        this.aSal = aSal;
        this.aDob = aDob;

    }

    public Employees(String aName, String aEmail, int aSal, String aDob) {
        this.aName = aName;
        this.aEmail = aEmail;
        this.aSal = aSal;
        this.aDob = aDob;

    }

    public Employees() {
    }
    public int getId() {
        return id;
    }

    public void setId( int id) {
         this.id = id;
    }

    public String getaName() {
        return aName;
    }

    public void setaName( String aName) {
        this.aName = aName;
    }

    public String getaEmail() {
        return aEmail;
    }

    public void setaEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public int getaSal() {
        return aSal;
    }

    public void setaSal(int aSal) {
        this.aSal = aSal;
    }

    public String getaDob() {
        return aDob;
    }

    public void setaDob(String aDob) {
        this.aDob = aDob;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", aName='" + aName + '\'' +
                ", aEmail='" + aEmail + '\'' +
                ", aSal=" + aSal +
                ", aDob='" + aDob + '\'' +
                '}';
    }
}
