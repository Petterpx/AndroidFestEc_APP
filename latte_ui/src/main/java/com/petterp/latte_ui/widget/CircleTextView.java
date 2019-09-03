package com.petterp.latte_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author Petterp on 2019/6/19
 * Summary:圆形TextView
 * 邮箱：1509492795@qq.com
 */
public class CircleTextView extends AppCompatTextView {

    private  Paint PAINT;
    private  PaintFlagsDrawFilter FILTER;

    public CircleTextView(Context context) {
        super(context,null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        PAINT=new Paint();
        FILTER=new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        PAINT.setColor(Color.WHITE);
        PAINT.setAntiAlias(true);
    }
    public final void setCircleBackground(@ColorInt int color){
        PAINT.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width=getMeasuredWidth();
        final  int heigth=getMeasuredHeight();
        final int max=Math.max(width,heigth);
        setMeasuredDimension(max,max);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(FILTER);
        canvas.drawCircle(getWidth()/2,getHeight()/2,Math.max(getWidth(),getHeight())/2,PAINT);
        super.draw(canvas);
    }
}
