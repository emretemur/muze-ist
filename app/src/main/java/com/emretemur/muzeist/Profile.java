package com.emretemur.muzeist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView profileEmail;
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Account");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        profileEmail = (TextView) findViewById(R.id.profileEmail);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);

        profileEmail.setText("Welcome "+user.getEmail());

        logoutBtn.setOnClickListener(this);
    }@Override
    public void onClick(View view) {
        if(view == logoutBtn){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
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
        else if(id== R.id.action_login){
            ///return true;  //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this,Login.class);
            startActivity(intent2);
        }

        return super.onOptionsItemSelected(item);
    }
    public void goM (View view){

        Intent go = new Intent(this, MainActivity.class);
        startActivity(go);
    }
}
