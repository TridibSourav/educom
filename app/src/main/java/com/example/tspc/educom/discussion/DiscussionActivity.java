package com.example.tspc.educom.discussion;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tspc.educom.R;
import com.example.tspc.educom.discussion.Adapter.DiscussionAdapter;
import com.example.tspc.educom.discussion.discussionModel.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DiscussionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DiscussionAdapter adapter;
    List<QuestionModel>questionModelList;
    DatabaseReference myref;

    FirebaseAuth mAuth;


    EditText newQues;
    String name="";
    String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);


        recyclerView=findViewById(R.id.dicussionRecyler);
        newQues=findViewById(R.id.editComment);

        questionModelList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager liner= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(liner);
        recyclerView.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            FirebaseUser user= mAuth.getCurrentUser();
            getAuthorName(user.getUid());
        }


    }

    private void getdatafromserver(){

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myref=database.getReference().child("discussion");

        myref.addValueEventListener(new ValueEventListener() {
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

    private void viewUpdate(DataSnapshot childSnap) {

            String qID=childSnap.child("ques_id").getValue(String.class);
            String ques=childSnap.child("question").getValue(String.class);
            String author=childSnap.child("author").getValue(String.class);

            QuestionModel question= new QuestionModel(author,qID,ques);
            questionModelList.add(question);


        adapter= new DiscussionAdapter(this,questionModelList,name);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    public void PostNewQuestion(View view) {
        question=newQues.getText().toString();
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Posting new discussion topic");
        progressDialog.setMessage("Please wait..........");
        progressDialog.show();
                String string = UUID.randomUUID().toString().substring(0, 8);
                QuestionModel model = new QuestionModel(name, string, question);
                if (!question.equals("")) {
                    myref.child(string).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.cancel();
                                Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_LONG).show();
                                questionModelList.clear();
                                adapter.notifyDataSetChanged();
                                getdatafromserver();
                            } else {
                                progressDialog.cancel();
                                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Can't post empty topic", Toast.LENGTH_LONG).show();
                }


    }

    private void getAuthorName(String uid) {
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myref=database.getReference().child("users").child(uid);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    name=dataSnapshot.child("name").getValue(String.class);
                    getdatafromserver();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
