package com.emretemur.muzeist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    private FirebaseAuth newAuth;
    private EditText userEmail,userPassword,userName;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userName = findViewById(R.id.regUsername);
        registerBtn = findViewById(R.id.registerBtn);

        newAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerBtn.setVisibility(View.INVISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String name = userName.getText().toString();

                if( email.isEmpty() || name.isEmpty() || password.isEmpty()) {

                    showMessage("Please fill the fields") ;
                    registerBtn.setVisibility(View.VISIBLE);
                }
                else {
                    CreateUserAccount(email,name,password);
                }
            }
        });
    }
    public  void gotoLogBtn(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    private void CreateUserAccount(String email, final String name, String password) {

        newAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMessage("Account created");
                        }
                        else
                        {
                            showMessage("Try Again!");
                        }
                    }
                });
    }
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about, menu);
        //int id = item.getItemId();

        return  true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.action_main){
            //  //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this, MainActivity.class  );
            startActivity(intent2);
        }
        else if(id== R.id.action_login){
            ///return true;  //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this,Login.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}