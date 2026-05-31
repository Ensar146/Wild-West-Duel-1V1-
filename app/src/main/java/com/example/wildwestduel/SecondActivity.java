package com.example.wildwestduel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    Runnable runnable;
    Handler handler;

    Button btnp1, btnp2;

    TextView sonuc, sayac, sonuc2;

    int sayi = 0;

    int surem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        handler = new Handler(Looper.getMainLooper());

        sonuc = findViewById(R.id.son);
        sayac = findViewById(R.id.say);
        btnp1 = findViewById(R.id.buttonp1);
        btnp2 = findViewById(R.id.butonp2);
        sonuc2 = findViewById(R.id.son2);

        btnp1.setVisibility(View.INVISIBLE);
        btnp2.setVisibility(View.INVISIBLE);


        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;


        Random sure = new Random();
        surem = sure.nextInt(12) + 2;


        sayi = 0;
        runnable = new Runnable() {
            @Override
            public void run() {
                sayi++;
                sayac.setText(String.valueOf(sayi));

                if (sayi < surem) {
                    handler.postDelayed(this, 1000);
                }


                if (sayi == surem) {
                    sayac.setText("ATEŞLE");

                    Random randomPos = new Random();


                    int padding = 550;


                    int btnW = btnp1.getWidth();
                    int btnH = btnp1.getHeight();
                    int maxX = screenWidth - btnW;
                    int maxY = screenHeight - btnH;

                    int randomX1 = randomPos.nextInt(Math.max(1, maxX / 2));
                    int randomY1 = randomPos.nextInt(Math.max(1, maxY));

                    int randomX2 = maxX / 2 + randomPos.nextInt(Math.max(1, maxX / 2));
                    int randomY2 = randomPos.nextInt(Math.max(1, maxY));

                    btnp1.setX(randomX1);
                    btnp1.setY(randomY1);
                    btnp1.setVisibility(View.VISIBLE);

                    btnp2.setX(randomX2);
                    btnp2.setY(randomY2);
                    btnp2.setVisibility(View.VISIBLE);

                    handler.removeCallbacks(this);
                }

            }
        };

        handler.post(runnable);


        btnp1.setOnClickListener(view -> {
            sonuc.setText("Oyuncu 1 Kazandı!");
            handler.removeCallbacks(runnable);
            btnp2.setVisibility(View.GONE);
        });

        btnp2.setOnClickListener(view -> {
            sonuc2.setText("Oyuncu 2 kazandı!");
            handler.removeCallbacks(runnable);
            btnp1.setVisibility(View.GONE);
        });


    }
}

