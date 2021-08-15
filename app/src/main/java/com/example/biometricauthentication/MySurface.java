package com.example.biometricauthentication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;

public class MySurface extends SurfaceView {
    private boolean playing=true;
    public MySurface(Context context) {
        super(context);
        new Abis().start(); }
    private class Abis extends Thread{
        int counter=0;
        @Override
        public void run() {
            long last_update_time=0;
            long delay=100;
            int img_Id[]={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5};
            while (true){
                if (playing){
                    long current_time=System.currentTimeMillis();
                    if (current_time>last_update_time-delay){
                        if (counter>=4){
                            counter=0; }
                        draw(img_Id[counter]);
                        last_update_time=current_time;counter++; } } } }
        private void draw(int id){
            SurfaceHolder holder=getHolder();
            Canvas canvas=holder.lockCanvas();
            if (canvas!=null){
                canvas.drawColor(Color.WHITE);Paint paint=new Paint();
                Bitmap bitmap= BitmapFactory.decodeResource(getContext().getResources(),id);
                canvas.drawBitmap(bitmap,100,100,paint);
                holder.unlockCanvasAndPost(canvas); } }}}
