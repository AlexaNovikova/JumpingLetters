package ru.sibguti.alexanovikova.jumpingletters;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Letter implements Runnable{

    private String letter;
    private float posX;
    private float posY;
    private float dx = 1f;
    private float dy = 4f;
    private int counter;
    private float maxY;
    private Panel panel;
    private boolean isDestroyed;
    private long currentTime;
    private long deltaTime;
    private final long speed = 6l;

    public Letter(Panel panel) {
        this.panel = panel;
        isDestroyed = false;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
        maxY = posY;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    private void move() {
        while (!isDestroyed&&maxY<panel.getHeight()) {
            deltaTime =  System.currentTimeMillis()-currentTime;
            if (deltaTime > speed) {
                currentTime = System.currentTimeMillis();
                posX += dx;
                posY += dy;
                if (posX > panel.getWidth()) {
                    isDestroyed = true;
                    panel.removeLetter(this);
                    currentTime=System.currentTimeMillis();
                }
                if (posY >= panel.getHeight()) {
                    dy = -4f;
                    maxY += ((panel.getHeight()-maxY)/2);
                    if (maxY >= panel.getHeight()) {
                        isDestroyed = true;
                        panel.removeLetter(this);
                    }
                }
                if (posY < maxY) {
                    dy = 4f;
                }
            }
            }

        }

//    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public String getLetter() {
        return letter;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void flushDestroy() {
        isDestroyed = false;
    }

    public void draw(Canvas canvas, Paint paint) {
      //  move();
        canvas.drawText(letter, posX, posY, paint);
    }


    @Override
    public void run() {
        currentTime=System.currentTimeMillis();
        move();
    }
}
