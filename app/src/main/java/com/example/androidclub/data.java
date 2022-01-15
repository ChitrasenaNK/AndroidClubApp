package com.example.androidclub;

public class data {
    String id,name,email,phone,branch;
    public data()
    {

    }
    public data(String id ,String name, String email, String phone, String branch) {
        this.id=id;
        this.name=name;
        this.email = email;
        this.phone=phone;
        this.branch=branch;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email =email;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

}
