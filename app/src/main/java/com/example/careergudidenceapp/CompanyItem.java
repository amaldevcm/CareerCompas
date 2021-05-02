package com.example.careergudidenceapp;

public class CompanyItem {
    String companyName, salary;

    public CompanyItem(String companyName, String salary) {
        this.companyName = companyName;
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
