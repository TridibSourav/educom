package com.example.tspc.educom.Model;

public class Delete {
    String nam;
    String email;
    String pass;
    String inst;
    String categ;

    public Delete(String nam, String email, String pass, String inst, String categ) {
        this.nam = nam;
        this.email = email;
        this.pass = pass;
        this.inst = inst;
        this.categ = categ;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }
}

