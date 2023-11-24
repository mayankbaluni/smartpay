package com.smart.pay.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
//import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;


import androidx.appcompat.widget.AppCompatImageView;

import com.smart.pay.R;

public class RoundedImageView extends AppCompatImageView {
    private float mTopLeft = 0;
    private float mTopRight = 0;
    private float mBottomRight = 0;
    private float mBottomLeft = 0;
    private RoundRectShape mRoundRectShape;
    private final Paint paint = new Paint();
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    public RoundedImageView(Context context) {
        super(context);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        getAttributes(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        getAttributes(context, attrs);
    }

    private void getAttributes(Context context, AttributeSet attrs) {

        final TypedArray typedArrayAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.RoundedImageView);

        mTopLeft = typedArrayAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_topLeftRadius, 0);
        mTopRight = typedArrayAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_topRightRadius, 0);
        mBottomLeft = typedArrayAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_bottomLeftRadius, 0);
        mBottomRight = typedArrayAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_bottomRightRadius, 0);

        mRoundRectShape = new RoundRectShape(new float[]{
                mTopLeft, mTopLeft,
                mTopRight, mTopRight,
                mBottomRight, mBottomRight,
                mBottomLeft, mBottomLeft
        }, null, null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (((BitmapDrawable) getDrawable()) != null) {
            mBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }

        paint.setAntiAlias(true);
        paint.setShader(mBitmapShader);

        mRoundRectShape.resize(getWidth(), getHeight());
        mRoundRectShape.draw(canvas, paint);
    }

    public float getTopLeftRadius() {
        return mTopLeft;
    }

    public void setTopLeftRadius(float radius) {
        this.mTopLeft = radius;
    }

    public float getTopRightRadius() {
        return mTopRight;
    }

    public void setTopRightRadius(float radius) {
        this.mTopRight = radius;
    }

    public float getBottomRightRadius() {
        return mBottomRight;
    }

    public void setBottomRightRadius(float radius) {
        this.mBottomRight = radius;
    }

    public float getBottomLeftRadius() {
        return mBottomLeft;
    }

    public void setBottomLeftRadius(float radius) {
        this.mBottomLeft = radius;
    }
}
