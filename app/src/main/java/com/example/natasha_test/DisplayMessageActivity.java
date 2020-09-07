package com.example.natasha_test;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //textView.setText(message);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.addView(textView);
        l.addView(b);

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
    }
}