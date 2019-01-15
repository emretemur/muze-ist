package com.emretemur.muzeist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
private TextView Mes;
private ImageView Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar myActionBar = getSupportActionBar();

        myActionBar.hide();

        Mes= (TextView)findViewById(R.id.MessageTxt);
        Logo = (ImageView)findViewById(R.id.LogoI);
        Animation newAnimation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        Mes.startAnimation(newAnimation);
        Logo.startAnimation(newAnimation);
        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){
            public  void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                e.printStackTrace();
                }finally {
                startActivity(intent); finish();
                }
            }
          };
          timer.start();
    }
}
