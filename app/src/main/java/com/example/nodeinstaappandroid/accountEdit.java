package com.example.nodeinstaappandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class accountEdit extends AppCompatActivity {

    private Button camera;
    private ImageView profilePicture;
    private Button galery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);

        profilePicture = findViewById(R.id.picture);
        galery = findViewById(R.id.galeria);

        camera = findViewById(R.id.aparat);
        camera.setOnClickListener((v) -> {
            checkPermission(Manifest.permission.CAMERA, 100);
        });

        galery.setOnClickListener((v) -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 101); // 100 - stała wartość, która później posłuży do identyfikacji tej akcji        });

        });

    }

    public void checkPermission(String permission, int requestCode) {
        // jeśli nie jest przyznane to zażądaj
        Activity accountEdit;
        if (ContextCompat.checkSelfPermission(accountEdit.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(accountEdit.this, new String[]{permission}, requestCode);

        } else {
            Toast.makeText(accountEdit.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//jeśli jest dostępny aparat
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 200); // 200 - stała wartość, która później posłuży do identyfikacji tej akcji
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    //nie ma uprawnien
                }
                break;
            case 101 :

                break;
        }

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
                Log.d("camera", b.getWidth() +" - "+b.getHeight());
                profilePicture.setImageBitmap(b);

            }
        }

        if(requestCode == 101) {
            Uri imgData = data.getData();
            InputStream stream = null;
            try {
                stream = getContentResolver().openInputStream(imgData);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap b = BitmapFactory.decodeStream(stream);
            StaticValues.picture = b;
            Log.d("galery", b.getWidth() +" - "+b.getHeight());
            profilePicture.setImageBitmap(b);
        }
    }

    public class StaticValues {

        public static Bitmap picture;

    }


}