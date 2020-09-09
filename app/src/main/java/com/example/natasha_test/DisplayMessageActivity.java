package com.example.natasha_test;

import android.annotation.TargetApi;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.jar.Attributes;

public class DisplayMessageActivity extends AppCompatActivity {
    private SoundPool mSoundPool;
    private int vlad;
    private int mStreamID;
    private AssetManager mAssetManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }
        mAssetManager = getAssets();
        vlad = loadSound("vlad.ogg");


        // Получаем сообщение из объекта intent
        Intent intent = getIntent();
       // String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String name = intent.getStringExtra("Name");
        String sname = intent.getStringExtra("Sname");
        // Создаем текстовое поле
        TextView textView = new TextView(this);
        textView.setText("Здарова, " + name + " " + sname);
        textView.setTextSize(40);
        Button b = new Button(this);
        b.setText("Акции");
        Button b1 = new Button(this);
        b1.setText("Влад");
        //textView.setText(message);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.addView(textView);
        l.addView(b);
        l.addView(b1);
        ImageView img = new ImageView(DisplayMessageActivity.this);
        img.setImageResource(R.drawable.vlad_);
        l.addView(img, 0);
        // Устанавливаем текстовое поле в системе компоновки activity
        setContentView(l);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(DisplayMessageActivity.this, "Марина Сергеевна", Toast.LENGTH_LONG);
                LinearLayout ll = (LinearLayout) t.getView();
                ImageView img = new ImageView(DisplayMessageActivity.this);
                img.setImageResource(R.drawable.ms);
                ll.addView(img, 0);
                t.show();

                            }
        });
        img.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                if (eventAction == MotionEvent.ACTION_UP) {
                    // Отпускаем палец
                    if (mStreamID > 0)
                        mSoundPool.stop(mStreamID);
                }
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    // Нажимаем на кнопку
                    mStreamID = playSound(vlad);
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    mSoundPool.stop(mStreamID);
                }
                return true;
            }
        });


    }


    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }
    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }
}