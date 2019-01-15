package com.emretemur.muzeist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("About");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        TextView textView9 = (TextView)findViewById(R.id.textView9);
        textView9.setText("In this application, the information of the museums and " +
                "historical places in the city will be shown to the tourists in this country " +
                "which has many tourist visitors. In this application, users will " +
                "be able to access the names of museums and historical places, images, location, open hours and detailed " +
                "information about these places.");
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
}
