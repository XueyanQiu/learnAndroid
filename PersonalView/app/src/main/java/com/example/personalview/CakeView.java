package com.example.personalview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CakeView extends View {

    private List<CakeBean> beanList;
    private RectF cakeRectF = new RectF();
    private RectF legendRectF;
    private Paint mPaint;
    private int cakeWidth,cakeHeight;
    private float rotateDegree = 0.0f; //圆弧起始角度
    private float sumValue = 0;
    private float diameter;
    private float textY;
    private float legendHeight = 40, legendWidth = 80;
    private float mMarginInterval = 40;
    private Context mContext;



    public CakeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }
    public CakeView(Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
        mContext = context;
        init();
    }
    public CakeView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init(){
        beanList = new ArrayList<>();

        setData(beanList);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode){
            case MeasureSpec.EXACTLY:
                cakeWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                cakeWidth = 800;
                break;
            default:
                break;
        }
        switch (heightMode){
            case MeasureSpec.EXACTLY:
                cakeHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                cakeHeight = 400;
                break;
            default:
                break;
        }

        Log.d("CakeView", "onMeasure: ");
        setMeasuredDimension(cakeWidth,cakeHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        diameter = Math.min(cakeHeight, cakeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Log.d("cakeview", "onDraw: ");
        textY = 0f;
        cakeRectF.set(0,0,diameter,diameter);
        canvas.translate((cakeWidth-diameter)/8,(cakeHeight-diameter)/2);
        if(beanList.size()>0 && Float.compare(sumValue,0.0f)!=0)
            for(int i = 0; i < beanList.size(); i++){
                CakeBean bean = beanList.get(i);
                mPaint.setColor(bean.color);
                canvas.drawArc(cakeRectF, rotateDegree, bean.degree, true, mPaint);
                rotateDegree += bean.degree;
                drawRectAndText(canvas,bean);
            }
    }

    private void drawRectAndText(Canvas canvas, CakeBean bean){
        legendRectF = new RectF();
        float left = diameter + mMarginInterval;
        float right = diameter + mMarginInterval + legendWidth;
        float bottom = textY + legendHeight;
        legendRectF.set(left, textY,right,bottom);
        canvas.drawRect(legendRectF, mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        canvas.drawText(
                bean.name + "(" + new DecimalFormat(".00").format(bean.value/sumValue*100) + "%)",
                right + 10, textY + 30, mPaint);
        textY += legendHeight;
    }

    void setData(List<CakeBean> beans){
        Log.d("cakeview", "setData: ");
        if(beans == null || beans.size()<=0) return;
        for(int i=0; i<beans.size(); i++){
            CakeBean bean = beans.get(i);
            sumValue+= bean.value;
        }
        for (int i = 0; i < beans.size(); i++) {
            CakeBean bean = beans.get(i);
            bean.degree = bean.value / sumValue * 360;
            beanList.add(bean);
        }
        invalidate();
    }

//    public void setStartDegree(float startDegree) {
//        this.rotateDegree = startDegree;
//        invalidate();
//    }
}
