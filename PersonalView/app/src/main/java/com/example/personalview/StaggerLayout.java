package com.example.personalview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class StaggerLayout extends ViewGroup {
    public static final String TAG = "StaggerLayout";

    public StaggerLayout(Context context){
        super(context);
    }
    public StaggerLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public StaggerLayout(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }
    public StaggerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context,attrs,defStyleAttr,defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //最大高度宽度,最后提交
        int maxHeight = 0, maxWidth = 0;

        //当前行中最高的view的高度，当前行中已占用宽度
        int mLeftHeight = 0, mLeftWidth = 0;

        //子view数量
        final int count = getChildCount();

        //父控件最大宽度
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.d("layoutView", "screen wide: "+ widthSize);

        for(int i = 0; i < count; i++){
            final View child = getChildAt(i);
            if(child.getVisibility() == GONE) continue;
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            mLeftWidth += child.getMeasuredWidth();
          //  Log.d("layoutView", "item "+ (i+1) +":"+child.getMeasuredWidth());

            if(mLeftWidth > widthSize){
                //换行逻辑
                maxHeight += mLeftHeight;
                maxWidth = Math.max(mLeftWidth-child.getMeasuredWidth(), maxWidth);
                mLeftHeight = child.getMeasuredHeight();
                mLeftWidth = child.getMeasuredWidth();

            }else{
                mLeftHeight = Math.max(child.getMeasuredHeight(),mLeftHeight);
                maxWidth = Math.max(maxWidth,mLeftWidth);
            }
        }
        maxHeight += mLeftHeight;
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        setMeasuredDimension(
                resolveSizeAndState(maxWidth, widthMeasureSpec,0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("layoutView", "onLayout: ");
        final int count = getChildCount();

        final int childLeft = getPaddingLeft();
       // Log.d("layoutView", "PaddingLeft "+childLeft);
        final int childTop = getPaddingTop();
       // Log.d("layoutView", "PaddingTop "+childTop);
        final int maxWidth = r-l-getPaddingRight();
      //  Log.d("layoutView", "maxWidth "+maxWidth);

        int maxHeight = 0;

        int curLeft = childLeft;
        int curTop = childTop;

        int childWidth,childHeight;
        for(int i = 0; i<count; i++){
            View child = getChildAt(i);
            if(child.getVisibility() == GONE) continue;

            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();
            if(curLeft + childWidth > maxWidth){
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = childHeight;
            }
            child.layout(curLeft,curTop,curLeft+childWidth,curTop+childHeight);
            curLeft += childWidth;
            maxHeight = Math.max(maxHeight, childHeight);
        }
    }
}
