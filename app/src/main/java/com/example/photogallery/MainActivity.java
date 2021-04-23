package com.example.photogallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Переход к классу Gallery
        Intent intent = new Intent(MainActivity.this, Gallery.class);
        startActivity(intent);
    }
}
