package com.developers.danceguru.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.developers.danceguru.Utils.Data;

/**
 * Created by Amanjeet Singh on 02-Jul-17.
 */

public class Human extends View {

    private Paint paint, paint1,paint2,paint3,paint4;

    public Human(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint1 = new Paint();
        paint2=new Paint();
        paint3=new Paint();
        paint4=new Paint();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        paint1.setColor(Color.BLACK);
        paint1.setStrokeWidth(5);
        paint1.setStyle(Paint.Style.STROKE);

        paint2.setColor(Color.BLACK);
        paint2.setStrokeWidth(5);
        paint2.setStyle(Paint.Style.STROKE);

        paint3.setColor(Color.BLACK);
        paint3.setStrokeWidth(5);
        paint3.setStyle(Paint.Style.STROKE);

        paint4.setColor(Color.BLACK);
        paint4.setStrokeWidth(5);
        paint4.setStyle(Paint.Style.STROKE);

        post(runnable);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 50, paint);
        canvas.drawLine(getWidth() / 2, getHeight() / 2 + 50, getWidth() / 2, getHeight(), paint);
        canvas.drawLine(getWidth() / 2, getHeight() / 2 + 90, getWidth() / 2 + 100, getHeight() / 2 + 90, paint1);
        canvas.drawLine(getWidth() / 2, getHeight() / 2 + 90, getWidth() / 2 - 100, getHeight() / 2 + 90, paint2);
        canvas.drawLine(getWidth()/2,getHeight(),getWidth()/2+100,getHeight(),paint3);
        canvas.drawLine(getWidth()/2,getHeight(),getWidth()/2-100,getHeight(),paint4);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (Data.getColorRight() == (Color.RED)) {
                paint1.setColor(Color.RED);
            }
            if(Data.getColorLeft()==Color.RED){
                paint2.setColor(Color.RED);
            }
            if(Data.getColorRightLeg()==Color.RED){
                paint3.setColor(Color.RED);
            }
            if(Data.getColorLeftLeg()==Color.RED){
                paint4.setColor(Color.RED);
            }


            invalidate();
            postDelayed(this, 200);
        }
    };

}
