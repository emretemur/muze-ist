package com.emretemur.muzeist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
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
    public  void goReg(View view){
        Intent intent2 = new Intent(this, Register.class  );
        startActivity(intent2);
    }
}
