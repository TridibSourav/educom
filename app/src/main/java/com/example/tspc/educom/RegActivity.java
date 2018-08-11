package com.example.tspc.educom;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tspc.educom.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutionException;

public class RegActivity extends AppCompatActivity {
    EditText Ename,Eemail,Epass,EconPass,EintName;
    RadioGroup userCat;
    RadioButton teacher, student;
    String uName,uEmail,uPass,uConpass,uInst,uCat;
    Button b1;

    UserModel user;
    Boolean stat;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void init(){
        Ename=findViewById(R.id.name);
        b1=findViewById(R.id.email_sign_in_button);
        Eemail=findViewById(R.id.email);
        Epass=findViewById(R.id.password);
        EconPass=findViewById(R.id.confirm_pass);
        EintName=findViewById(R.id.insName);
        userCat=(RadioGroup) findViewById(R.id.Rgrp);
        //teacher=findViewById(R.id.teach);
        //student=findViewById(R.id.stu);
    }

    public void RegisterDone(View view) {
        uName=Ename.getText().toString();
        uEmail=Eemail.getText().toString();
        uInst=EintName.getText().toString();
        uPass=Epass.getText().toString();
        uConpass=EconPass.getText().toString();
        uCat="Teacher";

       /* userCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                uCat = rb.getText().toString();
                Log.w("********",uCat);
            }
        });*/





        boolean status=validate(uName,uEmail,uPass,uConpass,uInst,uCat);
        //boolean status=true;
        if (status){
            user= new UserModel(uName,uEmail,uPass,uCat,uInst);
            //networkRequest(user);
            ServerRequest(user);
        }
    }

    private void networkRequest(UserModel user) {
        try {
            stat=new com.masud.dev.examresultsbd.utils.CheckInternet(this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (stat){
                ServerRequest(user);
        }else {
            AlertShow("No Internet connection");
        }


    }

    private void ServerRequest(final UserModel mUser) {
        mAuth.createUserWithEmailAndPassword(mUser.getEmail(), mUser.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            try {
                                String uid=user.getUid();
                                databaseUpdate(mUser,uid);
                            }catch (Exception e){
                                e.printStackTrace();
                                Log.w("*****",e.toString());
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void databaseUpdate(UserModel mUser,String userId) {

        myRef.child(userId).setValue(user);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String uid = user.getUid();
            Log.w("**********",uid);
        }

    }


    private void AlertShow(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(RegActivity.this).create();
        alertDialog.setTitle("Error!");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    private boolean validate(String uName, String uEmail, String uPass, String uConpass, String uInst, String uCat) {
        boolean stat=false;
        if (uName.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            stat=true;
        }
        if (uEmail.equals("")||!uEmail.contains("@")){
            Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            stat=true;
        }

        if (uPass.length()<6||!uPass.equals(uConpass)||uConpass.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter password correctly",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            stat=true;
        }

        if (uInst.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter Institution name",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            stat=true;
        }

        if (uCat.equals("")){
            Toast.makeText(getApplicationContext(),"Please Select Category",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            stat=true;
        }
       return stat;
    }
}
