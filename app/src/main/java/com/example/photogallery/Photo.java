package com.example.photogallery;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.util.ArrayList;

public class Photo {

    // Получение списка адресов фото
    public static ArrayList<String> getImageList(Context context) {

        // Создание пустого списка
        ArrayList<String> list_image = new ArrayList<>();
        // Получение доступа к бд андройда
        ContentResolver contentResolver = context.getContentResolver();
        // Создаем запрос
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DATE_ADDED);
        // Начинаем заполнять список
        if (cursor.moveToLast()) {
            do {
                list_image.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            } while (cursor.moveToPrevious());
            cursor.close();
        }
        return list_image;
    }

    // Получение списка имен фото (аналогичен верхнему)
    public static ArrayList<String> getImageName(Context context) {

        ArrayList<String> list_name = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DATE_ADDED);
        if (cursor.moveToLast()) {
            do {
                list_name.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
            } while (cursor.moveToPrevious());
            cursor.close();
        }
        return list_name;
    }
}
