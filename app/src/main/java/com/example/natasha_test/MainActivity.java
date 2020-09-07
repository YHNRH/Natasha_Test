package com.example.natasha_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout name = new LinearLayout(this);
        name.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout sname = new LinearLayout(this);
        sname.setOrientation(LinearLayout.HORIZONTAL);
        final TextView n = new TextView(this);
        final TextView sn = new TextView(this);
        final EditText n1 = new EditText(this);
        final EditText sn1 = new EditText(this);
        Button b = new Button(this);
        n.setText("Имя: ");
        sn.setText("Фамилия: ");
        n1.setHint("Введи имя если не лох...");
        sn1.setHint("Введи фамилию если не лох...");
        name.addView(n);
        name.addView(n1);
        sname.addView(sn);
        sname.addView(sn1);
        main.addView(name);
        main.addView(sname);
        b.setText("Передать");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                String name = n1.getText().toString();
                String sname = sn1.getText().toString();
                intent.putExtra("Name", name);
                intent.putExtra("Sname", sname);
                startActivity(intent);
            }
        });
        main.addView(b);
        setContentView(main);
    }

    public void sendMessage(View view) {

    }
}