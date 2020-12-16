package com.example.todolistapp;

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

public class CreateAccountActivity extends AppCompatActivity {

    EditText name , email , password ;
    Button creat ;
    FirebaseAuth fAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initialization();
        creatListener();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext() , ListsActivity.class));
            finish();


        }
    }

    private void initialization() {
        name = findViewById(R.id.create_name);
        email = findViewById(R.id.create_email);
        password = findViewById(R.id.create_password);
        creat = findViewById(R.id.creat_btn);
        fAuth = FirebaseAuth.getInstance();
    }

    private void creatListener() {
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();

                //to check all edittext is fill

                if (TextUtils.isEmpty(username)){
                    name.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(useremail)){
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(userpassword)){
                    email.setError("Password is required");
                    return;
                }

                //register user in firebase

                fAuth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CreateAccountActivity.this, "user created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext() , ListsActivity.class));

                        }else {
                            Toast.makeText(CreateAccountActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });




            }
        });
    }


    public void Login(View view) {
        startActivity(new Intent(getApplicationContext() , LoginActivity.class));
    }
}