package catzor.rpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by steurjor on 2-3-2016.
 */
public class Character {
    private MainThread thread;
    private Bitmap image;
    public static final int WIDTH = 106;
    public static final int HEIGHT = 102;
    //final float scaleFactorX = 0, scaleFactorY = 0;
    public static int x = 400, y = 400; // Start position character
    boolean walking = false;
    boolean doXPath = false;
    int walkingSpeed = 9;
    int newX, newY;
    int diffX, diffY;
    int speedX, speedY;
    public static int centerCharacterX = 53;
    public static int centerCharacterY = 51;
    int angle;
    public static Rect bounds;

    public Character (Bitmap res) {
        image = res;
        bounds = new Rect(x-centerCharacterX, y-centerCharacterY, x+WIDTH, y+HEIGHT);
    }

    public void update(int positionX, int positionY){
        if ((newX != positionX)&(newY != positionY)) {
            walking = true;
            diffX = positionX - x;
            diffY = positionY - y;
            double dX = Math.signum(diffX);
            double dY = Math.signum(diffY);
            int iX = (int) dX;
            int iY = (int) dY;
            // Take route in 2 sec / 60 fps
            speedX = iX * walkingSpeed;
            speedY = iY * walkingSpeed;
            if (Math.abs(diffX) < Math.abs(diffY)) {
                doXPath = true;
            }
        }
        newX = positionX;
        newY = positionY;

        // Reached destination
        if ((positionX == x)&(positionY == y)) {
            walking = false;
        }

        // Start walking to destination
        if (walking) {
            // Check if X rout is shorter than Y route
            diffX = positionX - x;
            diffY = positionY - y;
            if (doXPath){
                setAngle(doXPath, positionX, positionY);
                if (diffX != 0) {
                    if (Math.abs(diffX) < Math.abs(speedX)) {
                        x = positionX;
                    } else {
                        x = x + speedX;
                    }
                } else {
                    doXPath = false;
                }
            }
            if (!doXPath) {
                setAngle(doXPath, positionX, positionY);
                if (diffY != 0) {
                    if (Math.abs(diffY) < Math.abs(speedY)) {
                        y = positionY;
                    } else {
                        y = y + speedY;
                    }
                } else {
                    doXPath = true;
                }
            }
        }

        bounds.set(x-centerCharacterX, y-centerCharacterY, x-centerCharacterX+WIDTH, y-centerCharacterY+HEIGHT);
    }

    public void draw(Canvas canvas, int positionX, int positionY, float scaleFactorX, float scaleFactorY) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(70);
        paint.setAntiAlias(true);

        Matrix matrix = new Matrix();
        matrix.postRotate(getAngle(), centerCharacterX,centerCharacterY);
        matrix.postTranslate((x-centerCharacterX), (y-centerCharacterY));
        // Draw X line
        //canvas.drawLine(x, y, x, positionY, paint);
        // Draw y line
        //canvas.drawLine(x, positionY, positionX, positionY, paint);
        final int savedState = canvas.save();
        canvas.scale(scaleFactorX,scaleFactorY);
        //canvas.drawText(getAngle()+ " deg", 10 , 230, paint);
        canvas.drawBitmap(image, matrix, null);
        canvas.restoreToCount(savedState);
    }


    public void setAngle(boolean doXPath, int positionX, int positionY) {
        if (doXPath) { // If start walking via X
            if (x < positionX) {
                angle = 90;
            } else {
                angle = 270;
            }
        }
        if (!doXPath) { // If start walking via Y
            if (y < positionY) {
                angle = 180;
            } else {
                angle = 0;
            }
        }
    }

    public int getAngle(){
        return angle;
    }


}
