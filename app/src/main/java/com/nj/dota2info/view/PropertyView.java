package com.nj.dota2info.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2018-03-07.
 */

public class PropertyView extends View {
    private int zongfen;
    private int zongren;
    private Paint mPaint;

    public PropertyView(Context context) {
        this(context, null);
    }

    public PropertyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PropertyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ff0000"));
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#cccccc"));
        canvas.drawRect(getLeft(), getTop(),getRight()-100, getBottom(), mPaint);
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setTextSize(30);
        canvas.drawText("10", getRight() - 20, getHeight(), mPaint);
    }

    public void setZongfen(int zongfen) {
        this.zongfen = zongfen;
    }

    public void setZongren(int zongren) {
        this.zongren = zongren;
    }

}
