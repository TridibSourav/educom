package com.example.tspc.educom;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
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

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RegActivity extends AppCompatActivity {
    EditText Ename,Eemail,Epass,EconPass,EintName;
    RadioGroup userCat;
    RadioButton teacher, student;
    String uName,uEmail,uPass,uConpass,uInst,uCat;
    Button b1;

    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
    }

    private void init(){
        Ename=findViewById(R.id.name);
        b1=findViewById(R.id.email_sign_in_button);
        Eemail=findViewById(R.id.email);
        Epass=findViewById(R.id.password);
        EconPass=findViewById(R.id.confirm_pass);
        EintName=findViewById(R.id.insName);
        userCat=findViewById(R.id.Rgrp);
        teacher=findViewById(R.id.teach);
        student=findViewById(R.id.stu);
    }

    public void RegisterDone(View view) {
        uName=Ename.getText().toString();
        uEmail=Eemail.getText().toString();
        uInst=EintName.getText().toString();
        uPass=Epass.getText().toString();
        uConpass=EconPass.getText().toString();
        userCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                uCat = rb.getText().toString();
                //Log.w("********",uCat);
            }
        });
        boolean status=validate(uName,uEmail,uPass,uConpass,uInst,uCat);
        if (status){
            user= new UserModel(uName,uEmail,uPass,uCat,uInst);
            networkRequest(user);
        }
    }

    private void networkRequest(UserModel user) {
        if (checkNetworkSettings()){
            if (checkInternetAvailibility()){
                ServerRequest(user);
            }else {
                AlertShow("No Internet connection");
            }
        }else {
            AlertShow("Turn on Internet connection");
        }


    }

    private void ServerRequest(UserModel user) {

    }

    private boolean checkNetworkSettings(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(RegActivity.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private boolean checkInternetAvailibility(){
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;

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
