package com.example.authentkicationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    TextView txt,loginlink;
    EditText fname,email,pass;
    Button  reg;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt=findViewById(R.id.abc);
        fname=(EditText)findViewById(R.id.fullname);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);

        reg=(Button)findViewById(R.id.register);

        loginlink=(TextView)findViewById(R.id.loginlink);
        firebaseAuth= FirebaseAuth.getInstance();

      /*
       //if user wants to stay login(savestate)
       if (firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
*/
        reg.setOnClickListener(new View.OnClickListener() {
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
                firebaseAuth.createUserWithEmailAndPassword(femail,fpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(RegisterActivity.this,"User successfully created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {

                            Toast.makeText(RegisterActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}
