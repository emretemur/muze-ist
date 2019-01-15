package com.emretemur.muzeist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView registerBtn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn  = (TextView) findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }
    private void userLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {  //
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                        }
                    }
                });
    }
        @Override
        public void onClick(View view) {
            if(view == loginBtn){
                userLogin();
            }
            if(view == registerBtn){
                finish();
                startActivity(new Intent(this, Register.class));
            }

        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        //int id = item.getItemId();

        return  true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.action_main){
            ///return true;  //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this, MainActivity.class  );
            startActivity(intent2);
        }
        else if(id== R.id.action_register){
            ///return true;  //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this,Register.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
