package com.emretemur.muzeist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView newRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference newDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Museums");
        newRecyclerView = findViewById(R.id.recyclerView);
        newRecyclerView.setHasFixedSize(true);

        newRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        newDbRef = mFirebaseDatabase.getReference("Data");
    }
    private void firebaseSearch(String searchText){
        Query firebaseSearchQuery = newDbRef.orderByChild("title").startAt(searchText.toUpperCase()).endAt(searchText.toLowerCase() + "\uf8ff");
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(
                Model.class,
                R.layout.row,
                ViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage(),model.getKoordinat(),model.getAddress(),model.getVisitor(),model.getTelno());
            }
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ViewHolder viewHolder =super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView nMuzeNameTxt = view.findViewById(R.id.muzeNameTxt);
                        TextView nDetailTxt = view.findViewById(R.id.muzeDetailTxt);
                        TextView nKoordTxt = view.findViewById(R.id.koordinatTxt);
                        TextView nAddressTxt = view.findViewById(R.id.addressTxt);
                        TextView nTelnoTxt = view.findViewById(R.id.telnoTxt);
                        TextView nVisitTxt = view.findViewById(R.id.visitorTxt);

                        ImageView nMuzeImage = view.findViewById(R.id.muzeImage);
                        String nMuzeName = nMuzeNameTxt.getText().toString();
                        String nDetail = nDetailTxt.getText().toString();
                        String nKord = nKoordTxt.getText().toString();
                        String nAddress = nAddressTxt.getText().toString();
                        String nTel = nTelnoTxt.getText().toString();

                        String nVisit = nVisitTxt.getText().toString();

                        Drawable mDrawable = nMuzeImage.getDrawable();
                        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                        Intent intent = new Intent(view.getContext(),MDetailActivity.class);
                        ByteArrayOutputStream newStream = new ByteArrayOutputStream();
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, newStream);
                        byte[] bytes = newStream.toByteArray();
                        intent.putExtra("image", bytes);
                        intent.putExtra("title", nMuzeName);
                        intent.putExtra("koordinat", nKord);
                        intent.putExtra("address", nAddress);
                        intent.putExtra("visitor", nVisit);
                        intent.putExtra("telno", nTel);
                        intent.putExtra("description", nDetail);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                return viewHolder;
            }
        };
        newRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        newDbRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage(),model.getKoordinat(),model.getAddress(),model.getVisitor(),model.getTelno());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder =super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView nMuzeNameTxt = view.findViewById(R.id.muzeNameTxt);
                                TextView nDetailTxt = view.findViewById(R.id.muzeDetailTxt);
                                TextView nKoordTxt = view.findViewById(R.id.koordinatTxt);
                                TextView nAddressTxt = view.findViewById(R.id.addressTxt);
                                TextView nVisitTxt = view.findViewById(R.id.visitorTxt);
                                TextView nTelnoTxt = view.findViewById(R.id.telnoTxt);
                                ImageView nMuzeImage = view.findViewById(R.id.muzeImage);
                                String nMuzeName = nMuzeNameTxt.getText().toString();
                                String nDetail = nDetailTxt.getText().toString();
                                String nAddress = nAddressTxt.getText().toString();
                                String nKord = nKoordTxt.getText().toString();
                                String nVisit = nVisitTxt.getText().toString();
                                String nTel = nTelnoTxt.getText().toString();
                                Drawable mDrawable = nMuzeImage.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
//
                                Intent intent = new Intent(view.getContext(),MDetailActivity.class);
                                ByteArrayOutputStream newStream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, newStream);
                                byte[] bytes = newStream.toByteArray();
                                intent.putExtra("image", bytes); //put bitmap image as array of bytes
                                intent.putExtra("title", nMuzeName); // put title
                                intent.putExtra("koordinat", nKord);
                                intent.putExtra("address", nAddress);
                                intent.putExtra("visitor", nVisit);
                                intent.putExtra("telno", nTel);
                                intent.putExtra("description", nDetail); //put description
                                startActivity(intent); //start activity
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };
        newRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.action_about){
            ///return true;  //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this,About.class);
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