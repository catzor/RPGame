package catzor.rpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by steurjor on 29-2-2016.
 */
public class Background {
    private MainThread thread;
    private Bitmap image;
    private int x, y;

    public Background (Bitmap res) {
        image = res;
    }

    public void update(){

    }

    public void draw(Canvas canvas, String FPS, int positionX, int positionY, float scaleFactorX, float scaleFactorY) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(70);
        paint.setAntiAlias(true);
        final int savedState = canvas.save();
        canvas.scale(scaleFactorX,scaleFactorY);
        canvas.drawBitmap(image, x, y, null);
        canvas.drawText(FPS + " FPS", 10 , 70, paint);
        canvas.drawText("Kitkat " + Kitkat.kitkat, 10 , 150, paint);
        //canvas.drawText(positionX + ", " + positionY, 10 , 150, paint);
        canvas.restoreToCount(savedState);

    }


}
