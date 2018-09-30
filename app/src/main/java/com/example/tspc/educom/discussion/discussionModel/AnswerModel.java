package com.example.tspc.educom.discussion.discussionModel;

public class AnswerModel {
    String name;
    String ans;

    public AnswerModel(String name, String ans) {
        this.name = name;
        this.ans = ans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
