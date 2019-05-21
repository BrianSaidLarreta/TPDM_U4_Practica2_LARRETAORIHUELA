package com.example.tpdm_u4_practica2_larretaorihuela;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Mosca {

    Bitmap imagen;
    int x, y, x1,y1,toques;

    public Mosca(Lienzo este,int imagen, int posx, int posy,int toques){
        this.imagen = BitmapFactory.decodeResource(este.getResources(),imagen);
        x = posx;
        y = posy;
        this.toques = toques;
    }

    public void mover(int movx,int movy){
        x = movx ;
        y = movy ;

    }

    public void pintar(Canvas c, Paint p,String j){
        if(j.equals("jefe")) {
            x = (int)(Math.random()*300+10);
            y = (int)(Math.random()*600);
            c.drawBitmap(imagen, x, y, p);
        }
        else {
            c.drawBitmap(imagen, x, y, p);
        }
    }

    public boolean estaEnArea(int dedox, int dedoy) {
        int x2 = x + imagen.getWidth();
        int y2 = y + imagen.getHeight();
        if (dedox >= x && dedox <= x2 && dedoy >= y && dedoy <= y2) {
//            if(dedoy >=y && dedoy<=y2){
            return true;
//            }
        }
        return false;
    }
}
