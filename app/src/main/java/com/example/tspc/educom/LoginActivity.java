package com.example.tspc.educom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button regBtn, logBtn;
    FirebaseAuth loginAuth;
    EditText Remail, Rpasswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginAuth = FirebaseAuth.getInstance();
        logBtn = findViewById(R.id.email_sign_in_button);
        Remail = findViewById(R.id.emul);
        Rpasswrd = findViewById(R.id.paswrd);
        regBtn = findViewById(R.id.regbtn);
        regBtn.setOnClickListener(this);


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = Remail.getText().toString();
                String passwordd = Rpasswrd.getText().toString();

                if (emailText.isEmpty() || passwordd.isEmpty()) {

                    Remail.setError("Fill Id");
                    Rpasswrd.setError("Fill valid Pass");
                    Toast.makeText(LoginActivity.this, "Please enter all of informatin", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailText, passwordd);
                }
            }
        });


    }


    private void loginUser(final String emailText, final String passwordd) {
        loginAuth.signInWithEmailAndPassword(emailText, passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Log In SuccesFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                    intent.putExtra("email", Remail.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Not Success ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //alada
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegActivity.class);
        startActivity(intent);

    }
}
