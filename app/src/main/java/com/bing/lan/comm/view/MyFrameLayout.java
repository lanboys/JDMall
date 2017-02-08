package com.bing.lan.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.bing.lan.jdmall.R;

import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * 修改图片大小的工具类,无论是在布局中还是代码中都能使用
 */

public class MyFrameLayout extends FrameLayout {
    private float mRelative=444.0f/183;//宽度/高度的比值
    public static final int RELATIVE_WIDTH=0;//相对宽度,也就是说宽度是精确值
    public static final int RELATIVE_HEIGHT=1;//相对高度,也就是说高度是精确值
    public int mCurrentRelative=RELATIVE_WIDTH;

    //方便在代码中设置相对值
    public void setCurrentRelative(int currentRelative) {
        if (currentRelative>1||currentRelative<0){//代码只能是0 1
            return;
        }
        this.mCurrentRelative=currentRelative;
    }

    public void setRelative(float relative) {
        mRelative = relative;
    }

    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyFrameLayout);
         mRelative = a.getFloat(R.styleable.MyFrameLayout_reative, mRelative);
        mCurrentRelative=a.getInt(R.styleable.MyFrameLayout_mode_space,mCurrentRelative);
        a.recycle();
    }

    //如果是修改大小复写onMeasure

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**widthmode
         * 1 不确定(使用不多)
         * 2 精确模式  matchPartent 140dp
         * 3 不精确模式 warpContent
         *
         */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);


        int heightSize= MeasureSpec.getSize(heightMeasureSpec);
        int heightmode= MeasureSpec.getMode(heightMeasureSpec);
         //以宽度为确定值
        if (widthmode== MeasureSpec.EXACTLY&&mRelative!=0&&mCurrentRelative==RELATIVE_WIDTH){
            //求得父亲的宽度
            //求孩子的宽度
           int childWidth= widthSize-getPaddingLeft()-getPaddingRight();
            //求孩子的高度
            int childHeight= (int) (childWidth/mRelative+0.5f);
            int childWidthMeasureSpec= makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec= makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthMeasureSpec,childHeightMeasureSpec);
            //设置会父亲的宽高
             heightSize=childHeight+getPaddingTop()+getPaddingBottom();
            setMeasuredDimension(widthSize,heightSize);
          //以高度为确定值
        }else if(heightmode== MeasureSpec.EXACTLY&&mRelative!=0&&mCurrentRelative==RELATIVE_HEIGHT){
                //求孩子的宽度和高度
            int childHeight=heightSize-getPaddingBottom()-getPaddingTop();
            int childWidth= (int) (childHeight*mRelative+0.5f);
            int childWidthMeasureSpec= makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec= makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthMeasureSpec,childHeightMeasureSpec);
            //设置会父亲的宽高
            widthSize=childWidth+getPaddingRight()+getPaddingLeft();
            setMeasuredDimension(widthSize,heightSize);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }



    }
}
