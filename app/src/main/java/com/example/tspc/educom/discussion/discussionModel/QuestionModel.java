package com.example.tspc.educom.discussion.discussionModel;

public class QuestionModel {
    String author;
    String ques_id;
    String question;

    public QuestionModel(String author, String ques_id, String question) {
        this.author = author;
        this.ques_id = ques_id;
        this.question = question;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQues_id() {
        return ques_id;
    }

    public void setQues_id(String ques_id) {
        this.ques_id = ques_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
