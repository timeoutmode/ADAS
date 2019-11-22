package com.example.adas.Constructional_Praxis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintView extends View {

    private Path drawpath;
    private boolean erase = false;
    private Canvas drawCanvas;
    private Paint drawaPaint, canvasPaint;
    private  int paintColor = 0XFF660000;
    private Bitmap canvasBitmap;
    private Paint paintLine;
    private float brushSize, lastBushSize;
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    public void setErase(boolean isErase){
        erase = isErase;

        if (erase) drawaPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawaPaint.setXfermode(null);



    }

    public void clear(){
        drawpath.reset();
        canvasBitmap.eraseColor(Color.WHITE);
        invalidate();

    }


    public void setBrushSize(float newSize){
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize,getResources().getDisplayMetrics());
        brushSize = pixelAmount;
        drawaPaint.setStrokeWidth(brushSize);

    }

    public void setLastBrushSize(float lastSize){
        lastBushSize = lastSize;
    }

    public float getBrushSize(){
        return lastBushSize;
}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawpath, drawaPaint);

        //  canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, 78 , paintLine);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawpath.moveTo(touchX, touchY);
                break;
            case  MotionEvent.ACTION_MOVE:
                drawpath.lineTo(touchX, touchY);

                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawpath, drawaPaint);
                drawpath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }


    public void setupDrawing(){
        drawpath = new Path();
        drawaPaint = new Paint();
        drawaPaint.setAntiAlias(true);
        drawaPaint.setStrokeWidth(5);
        drawaPaint.setStyle(Paint.Style.STROKE);
        drawaPaint.setStrokeJoin(Paint.Join.ROUND);
        drawaPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        brushSize = 10;
        lastBushSize = brushSize;
        drawaPaint.setStrokeWidth(brushSize);

    }

}
