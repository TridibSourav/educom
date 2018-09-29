package com.example.tspc.educom.Model;

public class QuizList {

    String icon;
    String title;
    String list_no;

    public QuizList(String icon, String title, String list_no) {
        this.icon = icon;
        this.title = title;
        this.list_no = list_no;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getList_no() {
        return list_no;
    }

    public void setList_no(String list_no) {
        this.list_no = list_no;
    }
}
