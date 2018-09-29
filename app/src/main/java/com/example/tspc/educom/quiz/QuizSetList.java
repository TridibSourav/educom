package com.example.tspc.educom.quiz;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;

import com.example.tspc.educom.Model.QuizList;
import com.example.tspc.educom.R;
import com.example.tspc.educom.Utils.GridSpacingItemDecoration;
import com.example.tspc.educom.adapters.QuizSetListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizSetList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference myRef;

    List<QuizList> quizLists;

    String cat="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_set_list);

        Intent intent= getIntent();
        cat=intent.getStringExtra("cat");
        quizLists= new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        GridLayoutManager mlayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getQuizListFromServer();
    }

    private void getQuizListFromServer() {

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef=database.getReference().getRef().child("quiz").child("general_knowledge");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    for (DataSnapshot childSnapshot:dataSnapshot.getChildren()){
                        viewUpdate(childSnapshot);
                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    private void viewUpdate(DataSnapshot dataSnapshot) {
        String title= dataSnapshot.child("title").getValue(String.class);
        String icon= dataSnapshot.child("icon").getValue(String.class);
        String list_no= dataSnapshot.getKey();


        QuizList quizList= new QuizList(icon,title,list_no);
        quizLists.add(quizList);

        QuizSetListAdapter adapter =new QuizSetListAdapter(this,quizLists,cat);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
