package com.jw.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @description:
 * @author: kangsx
 * @date: 2020/7/7
 * @version: 1.1.0
 */
public class RoundFrameLayout extends FrameLayout {
    private Path path;
    /**
     * 圆角半径
     */
    private float radius = 20f;

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public RoundFrameLayout(@NonNull Context context) {
        this(context,null);
    }

    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundFrameLayout);
            radius = typedArray.getDimension(R.styleable.RoundFrameLayout_radius, 20);
            typedArray.recycle();
        }
        path = new Path();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        path.reset();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), radius, radius, Path.Direction.CW);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.clipPath(path, Region.Op.REPLACE);
        super.dispatchDraw(canvas);
    }
}
