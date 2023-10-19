package com.example.employeesreports.models;

import java.sql.Date;

public class DeptManager {

    private String dept_no;
    private int ep_no;
    private Date from_date;
    private Date to_date;

    public DeptManager() {
    }

    public DeptManager(String dept_no, int ep_no, Date from_date, Date to_date) {
        this.dept_no = dept_no;
        this.ep_no = ep_no;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public int getEp_no() {
        return ep_no;
    }

    public void setEp_no(int ep_no) {
        this.ep_no = ep_no;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
}
