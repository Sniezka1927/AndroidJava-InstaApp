package com.example.nodeinstaappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class accountView<adapter> extends AppCompatActivity {

    private GridView gridView;
    private Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        gridView = findViewById(R.id.photoGrid);
        editProfile = findViewById(R.id.edytujprofil);

        editProfile.setOnClickListener((v) -> {
            Log.d("View", "accountEdit");
            Intent intent = new Intent(accountView.this,accountEdit.class);
            startActivity(intent);
        });


        String[] array = new String[]{"a","b","c","a","b","c","a","b","c","a","b","c"};


        ArrayAdapter adapter = new ArrayAdapter<>(
                accountView.this,       // tzw Context
                R.layout.gridcell,     // nazwa pliku xml naszej komórki na liście
                R.id.textcell,                // id pola txt w komórce
                array );                 // tablica przechowująca testowe dane


        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener((a,v,i,l)-> {
            Log.d("TAG","numer klikanego wiersza w GridView = " + i);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}