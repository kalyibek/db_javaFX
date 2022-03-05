package com.example.demofx.entities;

import java.util.Date;

public class Department {
    private String d_name;
    private Integer d_number;
    private Long mgr_ssn;
    private Date mgr_start_date;
    private String year;
    private String month;
    private String day;

    public Department(String dname,Integer dnumber, Long mgrSSn , Date mgrStartDate){
       this.d_name =dname;
       this.d_number =dnumber;
       this.mgr_ssn =mgrSSn;
       this.mgr_start_date =mgrStartDate;
        this.year = mgr_start_date.toString().substring(0,4);
        this.month = mgr_start_date.toString().substring(5,7);
        this.day = mgr_start_date.toString().substring(8);
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public Integer getD_number() {
        return d_number;
    }

    public void setD_number(Integer d_number) {
        this.d_number = d_number;
    }

    public Long getMgr_ssn() {
        return mgr_ssn;
    }

    public void setMgr_ssn(Long mgr_ssn) {
        this.mgr_ssn = mgr_ssn;
    }

    public Date getMgr_start_date() {
        return mgr_start_date;
    }

    public void setMgr_start_date(Date mgr_start_date) {
        this.mgr_start_date = mgr_start_date;
    }
    public String getYear(){return year;}

    public String getMonth(){return month;}

    public String getDay(){return day;}
}
