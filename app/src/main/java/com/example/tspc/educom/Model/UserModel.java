package com.example.tspc.educom.Model;

public class UserModel {
    String name;
    String email;
    String password;
    String category;
    String institute;

    public UserModel(String name, String email, String password, String category, String institute) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.category = category;
        this.institute = institute;
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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
