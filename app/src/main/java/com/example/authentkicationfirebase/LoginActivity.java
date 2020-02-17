package com.example.authentkicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    Button login;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.emaill);
        pass=(EditText)findViewById(R.id.passw);

        login=(Button)findViewById(R.id.login);
        firebaseAuth= FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String femail= email.getText().toString().trim();
                String fpass=pass.getText().toString().trim();


                if (TextUtils.isEmpty(femail)){
                    email.setError("Please enter email");
                    return;
                }
                if (TextUtils.isEmpty(fpass)){
                    pass.setError("Please enter password");
                    return;
                }
                if (fpass.length()<7)
                {
                    pass.setError("Please enter password contain more than 7 character");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(femail,fpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(LoginActivity.this,"Login Succesfull...",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {

                            Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });

            }
        });


    }
}
