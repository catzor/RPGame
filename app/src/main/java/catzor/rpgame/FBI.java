package catzor.rpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


/**
 * Created by steurjor on 10-3-2016.
 */
public class FBI {
    private Bitmap image;
    // Fixed position for FBI sprite (for now)
    private int x=10, y=400;
    public static Rect source, destination;
    public int width = 64, height = 64;
    public int speed = 5, count = 0;
    public int sourceX, sourceY = 64;

    public FBI (Bitmap res) {
        image = res;
        // Create two rectangles, one source in the sprite and one destination on the screen
        source = new Rect(sourceX, sourceY, sourceX+width, sourceY+height);
        destination = new Rect(x, y, x+width, y+height);
    }

    public void update(){
        // Count to 5 to update FBI sprite
        count = count + 1;
        if (count == speed) {
            // If end of spritesheet is reached, set X = 0
            if (sourceX == 512) {
                sourceX = 0;
            } else {
                // Go to next sprite
                sourceX = sourceX + 64;
            }
            // Reset counter
            count = 0;
        }
        setRect(source, sourceX, sourceY, sourceX+width, sourceY+height);
    }

    // Draw sprite
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, getRect(source), getRect(destination), null);
    }

    // Set Sprite coordinates
    public void setRect(Rect rect, int x, int y, int width, int height) {
        rect.set(x, y, width, height);
    }

    // Get up2date sprite
    public Rect getRect( Rect rect) {
        return rect;
    }



}
