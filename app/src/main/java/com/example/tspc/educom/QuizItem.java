package com.example.tspc.educom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tspc.educom.Model.QuizModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizItem extends AppCompatActivity{

    String quiz_list_no="";

    DatabaseReference myRef;

    List<QuizModel> quizList;

    TextView Ques,Opt1,Opt2,Opt3,Opt4,pointT;
    String cat="";

    int count=0;
    int point=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item);

        Ques=findViewById(R.id.ques);
        Opt1=findViewById(R.id.opt1);
        Opt2=findViewById(R.id.opt2);
        Opt3=findViewById(R.id.opt3);
        Opt4=findViewById(R.id.opt4);
        pointT=findViewById(R.id.pointTxt);



        quizList= new ArrayList<>();

        Intent intent=getIntent();

        quiz_list_no=intent.getStringExtra("quiz_set_no");
        cat=intent.getStringExtra("cat");

        getDataFromServer(quiz_list_no,cat);

    }

    private void getDataFromServer(String quiz_list_no, String sCat) {
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef=database.getReference().getRef().child("quiz").child(sCat).child(quiz_list_no);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot!=null){
                        viewUpdate(dataSnapshot);


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void viewUpdate(DataSnapshot childSnapshot) {
        Log.w("****",childSnapshot.toString());

        for (int i=0; i<childSnapshot.getChildrenCount();i++){
            try{
                String q="ques"+i;
                if (childSnapshot.hasChild(q)) {
                    DataSnapshot child = childSnapshot.child(q);
                    String ques = child.child("ques").getValue(String.class);
                    String ans = child.child("ans").getValue(String.class);
                    String opt1 = child.child("opt1").getValue(String.class);
                    String opt2 = child.child("opt2").getValue(String.class);
                    String opt3 = child.child("opt3").getValue(String.class);
                    String opt4 = child.child("opt4").getValue(String.class);
                    QuizModel quiz = new QuizModel(ques, opt1, opt2, opt3, opt4, ans);
                    quizList.add(quiz);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            setQuiz();






    }

    private void setQuiz() {
        if (count<quizList.size()){
            QuizModel quiz= quizList.get(count);
            Ques.setText(quiz.getQues());
            Opt1.setText(quiz.getOpt1());
            Opt2.setText(quiz.getOpt2());
            Opt3.setText(quiz.getOpt3());
            Opt4.setText(quiz.getOpt4());
        }else if (count==quizList.size()){
            Intent intent = new Intent(QuizItem.this, ResultActivity.class);
            intent.putExtra("result",String.valueOf(point));
            startActivity(intent);
        }

    }

    public void optionClick(View view) {
        String selected="";
        int id= view.getId();
        switch(id){
            case R.id.opt1:
                selected=Opt1.getText().toString();
                count++;
                checkAns(selected);
                break;
            case R.id.opt2:
                selected=Opt2.getText().toString();
                count++;
                checkAns(selected);
                break;
            case R.id.opt3:
                selected=Opt3.getText().toString();
                count++;
                checkAns(selected);
                break;
            case R.id.opt4:
                selected=Opt4.getText().toString();
                count++;
                checkAns(selected);
                break;
                default:
                    break;
        }

    }
    private void checkAns(String s){
        QuizModel quiz=quizList.get(count-1);
        if (s.trim().toLowerCase().equals(quiz.getAns().toLowerCase().trim())){
            point=point+2;
            pointT.setText(String.valueOf(point));
        }else {
            point=point-1;
            pointT.setText(String.valueOf(point));
        }
        setQuiz();
    }

}
