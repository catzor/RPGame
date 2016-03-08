package catzor.rpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by steurjor on 7-3-2016.
 */
public class Kitkat {
    private Bitmap image;
    public static final int WIDTH = 43;
    public static final int HEIGHT = 84;
    public int randomX = 380, randomY = 200;
    public static Rect bounds;
    public static int kitkat = 0;

    public Kitkat (Bitmap res) {
        image = res;
        bounds = new Rect(randomX, randomY, randomX+WIDTH, randomY+HEIGHT);
    }

    public void update(){
        bounds.set(randomX, randomY, randomX+WIDTH, randomY+HEIGHT);
        isKitkatEaten();

    }

    public void draw(Canvas canvas, float scaleFactorX, float scaleFactorY) {
        final int savedState = canvas.save();
        canvas.scale(scaleFactorX,scaleFactorY);
        canvas.drawBitmap(image, getRandomX(), getRandomY(), null);
        canvas.restoreToCount(savedState);
    }

    public void isKitkatEaten(){
        if (intersect()) {
            calcualteNewKitkat();
            kitkat = kitkat + 1;
        }
    }

    public void calcualteNewKitkat(){
        randomX = 0 + (int) (Math.random() * ((GamePanel.WIDTH - WIDTH) + 1));
        randomY = 0 + (int) (Math.random() * ((GamePanel.HEIGHT - HEIGHT) + 1));
    }

    public int getRandomX(){
        return randomX;
    }

    public int getRandomY(){
            return randomY;
    }

    public boolean intersect() {
        if (bounds.intersects(Character.x-Character.centerCharacterX, Character.y-Character.centerCharacterY, Character.x-Character.centerCharacterX+WIDTH, Character.y-Character.centerCharacterY+HEIGHT)) {
            return true;
        }
        return false;
    }

}
