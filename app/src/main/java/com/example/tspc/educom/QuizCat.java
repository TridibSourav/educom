package com.example.tspc.educom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tspc.educom.quiz.QuizSetList;

public class QuizCat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_cat);
    }



    public void ShowQuizList(View v) {
        Intent intent = new Intent(QuizCat.this,QuizSetList.class);
        intent.putExtra("cat","general_knowledge");
        startActivity(intent);

    }

    public void quiz(View view) {
        Intent intent = new Intent(QuizCat.this,QuizSetList.class);
        intent.putExtra("cat","web");
        startActivity(intent);
    }
}
