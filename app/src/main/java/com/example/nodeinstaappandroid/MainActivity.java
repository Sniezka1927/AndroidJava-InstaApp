package com.example.nodeinstaappandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private LinearLayout accountView;
    private LinearLayout searchView;
    private LinearLayout createView;
    private LinearLayout collectionsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        accountView = findViewById(R.id.account);
        accountView.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this,accountView.class);
            startActivity(intent);
            Log.d("xxx", "acc");
        });

        searchView = findViewById(R.id.search);
        searchView.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this,searchView.class);
            startActivity(intent);

            Log.d("xxx", "searcd");

        });

        createView = findViewById(R.id.create);
        createView.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this,createView.class);
            startActivity(intent);

            Log.d("xxx", "create");

        });

        collectionsView = findViewById(R.id.collections);
        collectionsView.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this,collectionsView.class);
            startActivity(intent);

            Log.d("xxx", "collection");

        });
    };


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                // tutaj są dostępne dane zdjęcia z aparatu, można je logować
                Log.d("camera", "mam zdjecie");
                Bundle extras = data.getExtras();
                Bitmap b = (Bitmap) extras.get("data");
                Log.d("xxx", b.getWidth() +" - "+b.getHeight());
            }
        }
    }
}

