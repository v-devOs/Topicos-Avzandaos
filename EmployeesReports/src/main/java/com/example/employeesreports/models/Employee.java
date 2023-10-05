package com.example.employeesreports.models;

import java.sql.Date;

public class Employee {
    private int empNo;
    private String firstName, lastName;
    private char gender;
    private Date hireDate;

    Employee(){}

    Employee( int empNo, String firstName, String lastName, char gender, Date hireDate ){
        this.empNo = empNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }


    // Methods Get
    public int getEmpNo(){ return empNo; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public char getGender(){ return gender; }
    public Date getHireDate(){ return hireDate; }

    // Methods set
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
