package com.example.photogallery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity {

    // Константа для получения из другого экрана адресс фотки
    public static final String EXTRA_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    // Константа для получения из другого экрана имени фотки
    public static final String EXTRA_NAME = "SpacePhotoActivity.SPACE_NAME";

    // Место для отобраения фотографии
    private ImageView mImageView;
    // Место для отобраения имени
    private TextView mTextName;

    // При создании окна
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Получаем место для фотки
        mImageView = (ImageView) findViewById(R.id.image2);
        // Получаем место для имени
        mTextName = (TextView) findViewById(R.id.textName);

        // Получаем из Gallery адрес фотки
        String photo = getIntent().getStringExtra(EXTRA_PHOTO);
        // Получаем из Gallery имя фотки
        String name = getIntent().getStringExtra(EXTRA_NAME);


        // Добавление картинки на экран
        Glide.with(this)
                .load(photo)
                .asBitmap()
                .error(R.drawable.ic_cloud_off_red)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);


        // Добавление на экран имени
        mTextName.setText("Название: "+name);
    }



}
