package com.emretemur.muzeist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MDetailActivity extends AppCompatActivity {
    TextView nMuzeNameTxt, nDetailTxt, nKoordTxt, nAddressTxt,nVisitorTxt,nTelnoTxt;
    ImageView nMuzeImage;
    String desc; String koord; String telno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdetail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Museum Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        nMuzeNameTxt = findViewById(R.id.titleTv);
        nDetailTxt = findViewById(R.id.descriptionTv);
        nAddressTxt = findViewById(R.id.addressTv);
        nKoordTxt = findViewById(R.id.koordinatTxt);
        nVisitorTxt = findViewById(R.id.visitorTv);
        nTelnoTxt = findViewById(R.id.telnoTv);
        nMuzeImage = findViewById(R.id.imageView);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        String title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("description");
        koord = getIntent().getStringExtra("koordinat");
        String address = getIntent().getStringExtra("address");
        String visitor = getIntent().getStringExtra("visitor");
         telno = getIntent().getStringExtra("telno");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        nMuzeNameTxt.setText(title);
        nDetailTxt.setText(desc);
        nAddressTxt.setText(address);
        nVisitorTxt.setText(visitor);
        nTelnoTxt.setText(telno); //  public  // koordinat icin mDetailTv.setText(desc);
        nMuzeImage.setImageBitmap(bmp);

     /*   TextView mapBtn = findViewById(R.id.addressTxtBtn);
        String yazi = "go to address";
        SpannableString ss = new SpannableString(yazi);
        ClickableSpan clickableSpan1 =  new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri location = Uri.parse(koord);
                // Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ss.setSpan(clickableSpan1, 0,13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mapBtn.setText(ss);
        mapBtn.setMovementMethod(LinkMovementMethod.getInstance());
    }       */
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void buAdres(View view){
        Uri location = Uri.parse(koord);
       // Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }
    public void buCall (View view){
        Uri number = Uri.parse("tel:"+ telno);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

}
