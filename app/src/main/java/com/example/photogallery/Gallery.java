package com.example.photogallery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    // Код запроса разрешения (просто нужно при запросе разрешения)
    private static final int REQUST_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Запрашиваем разрешеие у пользователя на доступ к памяти телефона
        requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUST_CODE);

        // Создаем класс, ответственный за вид списка (2 колонки)
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        // Создаем список
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        // Говорим, чтобы он не изменял размеры
        recyclerView.setHasFixedSize(true);
        // Добавляем то, как он будет выглядеть
        recyclerView.setLayoutManager(layoutManager);

        // Создаем класс, который будет наполнять список
        Gallery.ImageGalleryAdapter adapter = new Gallery.ImageGalleryAdapter(this);
        // Добавляем данные к списку
        recyclerView.setAdapter(adapter);

    }

    // Класс который наполняет список
    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

        // При создании
        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Сохраняем контекст
            Context context = parent.getContext();
            // Получаем сборщик страницы
            LayoutInflater inflater = LayoutInflater.from(context);

            // Получаем место для фото
            View photoView = inflater.inflate(R.layout.item_photo, parent, false);
            // Создаем для него хранилище
            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            // Возвращаем хранилище
            return viewHolder;
        }

        // При отображении картинки
        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {
            // Получаем из списка фотографий ту, что нужно именно в эту позицию
            String photo = photos.get(position);
            // Получаем место, где будет отображаться данное фото
            ImageView imageView = holder.mPhotoImageView;

            // Загружаем данное фото в нужное место
            Glide.with(mContext)
                    .load(photo)
                    .placeholder(R.drawable.ic_cloud_off_red)
                    .error(R.drawable.picture)
                    .into(imageView);


        }

        // Необходимый размер списка
        @Override
        public int getItemCount() {
            return (photos.size());
        }

        // Класс для хранение инфы о фотке
        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            // Место где оно хранится
            public ImageView mPhotoImageView;

            // Конструкор
            public MyViewHolder(View itemView) {

                super(itemView);
                // Получаем место для фотки
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                // добавляем нажатие на фотку
                itemView.setOnClickListener(this);
            }

            // при нажатии на фото
            @Override
            public void onClick(View view) {

                // Получаем номер картинки
                int position = getAdapterPosition();
                // Если эта позиция правильная, то
                if(position != RecyclerView.NO_POSITION) {

                    // Получаем адресс фото
                    String photo = photos.get(position);
                    // Получаем имя фото
                    String name = names.get(position);

                    // Создаем переход в новое окно
                    Intent intent = new Intent(mContext, PhotoActivity.class);
                    // Передаем туда адресс фото
                    intent.putExtra(PhotoActivity.EXTRA_PHOTO, photo);
                    // Передаем имя
                    intent.putExtra(PhotoActivity.EXTRA_NAME, name);
                    // Запскаем окно
                    startActivity(intent);
                }
            }
        }

        // Список адресов фоток
        private ArrayList<String> photos;
        // Список имен фото
        private ArrayList<String> names;
        // Информация об экране, в котором находимся
        private Context mContext;

        // Конструктор
        public ImageGalleryAdapter(Context context) {
            // Получение контекста
            mContext = context;
            // Получение адресов
            photos = Photo.getImageList(context);
            // Получение имен
            names = Photo.getImageName(context);
        }
    }
}
