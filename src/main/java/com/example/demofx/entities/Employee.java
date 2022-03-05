package com.example.demofx.entities;

import java.util.Date;


public class Employee {
    private String first_name;
    private String last_name;
    private Integer Ssn;
    private Date birth_day;
    private String address;
    private String sex;
    private Integer salary;
    private Long super_ssn;
    private Integer d_number;
    private String year;
    private String month;
    private String day;

    public Employee(String fname, String lname, Integer ssn, Date bdate, String address,
                    String sex, Integer salary, Long superSsn, Integer dnumber) {
        this.first_name = fname;
        this.last_name = lname;
        this.Ssn = ssn;
        this.birth_day = bdate;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.super_ssn = superSsn;
        this.d_number = dnumber;
        this.year = birth_day.toString().substring(0,4);
        this.month = birth_day.toString().substring(5,7);
        this.day = birth_day.toString().substring(8);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getSsn() {
        return Ssn;
    }

    public void setSsn(Integer ssn) {
        Ssn = ssn;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public String getYear(){return year;}

    public String getMonth(){return month;}

    public String getDay(){return day;}


    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getSuper_ssn() {
        return super_ssn;
    }

    public void setSuper_ssn(Long super_ssn) {
        this.super_ssn = super_ssn;
    }

    public Integer getD_number() {
        return d_number;
    }

    public void setD_number(Integer d_number) {
        this.d_number = d_number;
    }
}
