package com.example.tpdm_u4_practica2_larretaorihuela;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {
    Thread hilo,tiempo;
    Mosca mosca,jefe;
    int x = (int)(Math.random()*300+10),y = (int)(Math.random()*600),toquesmosca = 30, toquesJefe=5, fin = 0, contador = 60, perdiste = 0;


    public Lienzo(Context context){
        super(context);
        mosca = new Mosca(this,R.drawable.mosca,x,y,toquesmosca);
        jefe = new Mosca(this,R.drawable.jefe,10000,10000,toquesJefe);
        hilo = new Thread() {

            public void run() {
                while (true) {
                    if(fin == 1)
                        return;
                    if(perdiste == 1){
                        return;
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        };
        hilo.start();
        tiempo = new Thread() {

            public void run() {
                while (true) {
                            contador--;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        };
        tiempo.start();
    }

    protected void onDraw (Canvas c){
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setTextSize(20f);

        if(contador <= 0 && (jefe.toques >= 1 || mosca.toques >=1)) {
            p.setTextSize(40f);
            c.drawText("Perdiste :c", 200, 200, p);
            perdiste = 1;
            return;
        }else if(fin==1 || (jefe.toques <= 0 && mosca.toques <=0)){
            c.drawText("Felicidades Has Ganado :3", 60, 150, p);
            fin = 1;
            return;
        }
        c.drawText("Tiempo:" + contador, 0, 50, p);



        if(mosca.toques>=1){
            //c.drawText("mosca"+ mosca.toques, 60, 120, p);
            mosca.pintar(c,p,"normal");
        }else {
            //c.drawText("jefe"+ jefe.toques, 60, 120, p);
            jefe.pintar(c, p, "jefe");
        }

    }

    public boolean onTouchEvent(MotionEvent me){
        //el evento ontouchevent permite detectar los toques
        //de uno o mas dedos que se hacen en el area de dibujo
        /*me.getAction() = accion: presiono,soltar,mover
                       pos x pos y*/

        int accion = me.getAction();
        int posx = (int) me.getX();
        int posy = (int) me.getY();

        switch (accion){
            case MotionEvent.ACTION_DOWN:
                //presiono
                if(mosca.estaEnArea(posx,posy) && mosca.toques>0){
                    mosca.toques--;
                    mosca.mover((int)(Math.random()*(getWidth()-mosca.imagen.getWidth())),(int)(Math.random()*(getHeight()-mosca.imagen.getHeight())));
                }
                if(jefe.estaEnArea(posx,posy) && jefe.toques>0){
                    jefe.toques--;
                    jefe.mover((int)(Math.random()*(getWidth()-jefe.imagen.getWidth())),(int)(Math.random()*(getHeight()-jefe.imagen.getHeight())));
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                break;

        }

        invalidate();
        return true;
    }

}
