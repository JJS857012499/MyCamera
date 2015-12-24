package com.zengfull.mycamera.camera.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * 相机的井字型背景
 * Created by asus on 2015/12/23.
 */
public class CameraGrid extends View {

    private Paint mPaint;
    private boolean isShowGrid = true;

    public CameraGrid(Context context) {
        this(context,null);
    }

    public CameraGrid(Context context, AttributeSet attrs) {
        super(context,attrs);
//        this(context,attrs,0);
        init();
    }

    public CameraGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        this(context,attrs,defStyleAttr,0);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CameraGrid(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(120);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int with = this.getWidth();
        int height = this.getHeight();
        if(isShowGrid){
            canvas.drawLine(0,height/3,with,height/3,mPaint);
            canvas.drawLine(0,height*2/3,with,height*2/3,mPaint);
            canvas.drawLine(with/3,0,with/3,height,mPaint);
            canvas.drawLine(with*2/3,0,with*2/3,height,mPaint);
        }
    }

    public void setShowGrid(boolean showGrid) {
        isShowGrid = showGrid;
        invalidate();
    }
}
