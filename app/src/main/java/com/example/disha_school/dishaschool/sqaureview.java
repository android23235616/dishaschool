package com.example.disha_school.dishaschool;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class sqaureview  extends ImageView {

    public sqaureview(Context context) {
        super(context);
    }

    public sqaureview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public sqaureview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}