package com.example.tspc.educom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tspc.educom.discussion.Adapter.AnswerAdapter;
import com.example.tspc.educom.discussion.Adapter.DiscussionAdapter;
import com.example.tspc.educom.discussion.discussionModel.AnswerModel;
import com.example.tspc.educom.discussion.discussionModel.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String q_id = "";
    String author = "";
    TextView ques;
    AnswerAdapter adapter;
    DatabaseReference myref;
    EditText commentReply;

    List<AnswerModel> ansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans);

        ansList = new ArrayList<>();

        Intent intent = getIntent();
        q_id = intent.getStringExtra("questionId");
        author = intent.getStringExtra("author");



        recyclerView = findViewById(R.id.reply);
        ques = findViewById(R.id.disqusQues);
        commentReply = findViewById(R.id.editComment);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager liner = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(liner);
        recyclerView.setAdapter(adapter);
        getdatafromserver(q_id);
    }

    private void getdatafromserver(String q_id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref = database.getReference().child("discussion").child(q_id);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    String qu=dataSnapshot.child("question").getValue(String.class);
                    ques.setText(qu);
                    if (dataSnapshot.hasChild("comment")){
                        DataSnapshot commentSnapshot=dataSnapshot.child("comment");
                        for (DataSnapshot childSnapshot : commentSnapshot.getChildren()) {
                            viewUpdate(childSnapshot);
                        }
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void viewUpdate(DataSnapshot childSnap) {

        String author = childSnap.child("name").getValue(String.class);
        String comment = childSnap.child("ans").getValue(String.class);

        AnswerModel ans = new AnswerModel(author, comment);
        ansList.add(ans);


        adapter = new AnswerAdapter(this, ansList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    public void PostNewComment(View view) {
        String newComment = commentReply.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Posting new reply");
        progressDialog.setMessage("Please wait..........");
        progressDialog.show();
        AnswerModel model = new AnswerModel(author, newComment);
        String string = UUID.randomUUID().toString().substring(0, 8);
        if (!newComment.equals("")) {
            myref.child("comment").child(string).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_LONG).show();
                        ansList.clear();
                        //adapter.notifyDataSetChanged();
                        getdatafromserver(q_id);
                        commentReply.setText("");
                    } else {
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            Toast.makeText(getApplicationContext(), "Can't post empty reply", Toast.LENGTH_LONG).show();
        }
    }
}
