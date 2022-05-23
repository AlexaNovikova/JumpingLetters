package ru.sibguti.alexanovikova.jumpingletters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


class Panel extends View {

    private Paint paint;
    private final List<String> lettersList = Arrays.asList("Н", "О", "В", "И", "К", "О", "В", "А");
    private int currentLetter = 0;
    private ArrayList<Letter> letters;

    public Panel(Context context) {// Конструктор
        super(context);
        letters = new ArrayList<>();
        paint = new Paint();
        paint.setColor(Color.rgb(100, 255, 10));
        paint.setTextSize(120);
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Letter l: letters){
            l.draw(canvas, paint);
        }
        invalidate();
    }

    public void createNewLetter(float x, float y) {
        String l = lettersList.get(currentLetter);
        currentLetter++;
        if (currentLetter > lettersList.size() - 1) {
                currentLetter = 0;
        }
        Letter letter;

        letter = new Letter(this);
        letter.setLetter(l);
        letter.setPosX(x);
        letter.setPosY(y);
        Thread thread = new Thread(letter);
        thread.start();
        letters.add(letter);
    }

    public void removeLetter(Letter letter){
        letters.remove(letter);
    }
}
