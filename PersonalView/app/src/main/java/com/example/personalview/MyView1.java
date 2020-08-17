package com.example.personalview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/***
 * 长宽wrap_content时一个蓝色的正方形
 */


public class MyView1 extends View {
    private Paint mPaint = new Paint();

    public MyView1(Context context) {
        super(context);
        mPaint.setColor(Color.BLUE);
    }

    public MyView1(Context context, AttributeSet attrs){
        super(context, attrs);
        mPaint.setColor(Color.BLUE);
    }

    public MyView1(Context context,AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getWidth()-paddingLeft-paddingRight;
        int height = getHeight()-paddingBottom-paddingTop;

        canvas.drawRect(0+paddingLeft, 0+paddingTop,
                width+paddingLeft, height+paddingTop, mPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST)
        { setMeasuredDimension(300, 300);}
        else if(widthMode == MeasureSpec.AT_MOST)
        {setMeasuredDimension(300, heightSize);}
        else if(heightMode == MeasureSpec.AT_MOST)
        {setMeasuredDimension(widthSize, 300);}
    }

}
