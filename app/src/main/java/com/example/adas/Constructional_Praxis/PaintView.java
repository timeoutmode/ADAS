package com.example.adas.Constructional_Praxis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

import androidx.annotation.Nullable;

public class PaintView extends View {
    public  static final float TOUCH_TOLERANCE = 10;

    private Path drawpath;
    private boolean erase = false;
    private Canvas drawCanvas,bitmapCanvas;
    private Paint drawaPaint, canvasPaint, paintScreen, painLine;
    private int paintColor = 0XFF660000;
    private Bitmap canvasBitmap;
    private Bitmap bitmap;
    private Paint paintLine;
    private float brushSize, lastBushSize;
    private HashMap<Integer, Path> pathMap;
    private  HashMap<Integer, Point> previousPointMap;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        setupDrawing();
    }

    void  setupDrawing(){
        paintScreen = new Paint();

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(23);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeJoin(Paint.Join.ROUND);
        paintLine.setStrokeCap(Paint.Cap.ROUND);

        pathMap = new HashMap<>();
        previousPointMap = new HashMap<>();


    }




    public void clear() {
        pathMap.clear();
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        invalidate();


    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, paintScreen);

        for (Integer key: pathMap.keySet()){
            canvas.drawPath(pathMap.get(key), paintLine);
        }

    }

//
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action  = event.getActionMasked();
        int actionIndex  = event.getActionIndex();

        if (action == MotionEvent.ACTION_DOWN ||
            action == MotionEvent.ACTION_POINTER_DOWN){
           
            touchStarted(event.getX(actionIndex), 
                    event.getY(actionIndex), 
                    event.getPointerId(actionIndex));
        }else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP){
            touchEnded(event.getPointerId(actionIndex));
        }else {
            touchMoved(event);
        }

//

        invalidate();
        return true;
    }

    private void touchMoved(MotionEvent event) {
        for (int i = 0; i < event.getPointerCount(); i++){
            int pointerId = event.getPointerId(i);
            int pointerIndex =  event.findPointerIndex(pointerId);

            if (pathMap.containsKey(pointerId)){
                float newx = event.getX(pointerIndex);
                float newY = event.getY(pointerIndex);

                Path path = pathMap.get(pointerId);
                Point point = previousPointMap.get(pointerId);

                //Calculate how far the user moved from the last update
                float deltaX = Math.abs(newx - point.x);
                float deltaY = Math.abs(newY - point.y);

                if (deltaX >= TOUCH_TOLERANCE ||
                    deltaY >= TOUCH_TOLERANCE){

                    // Move the path to a new location
                    path.quadTo(point.x, point.y,
                            (newx + point.x)/2,
                            (newY + point.y)/2);

                    //Store the new coordinates
                    point.x =(int)newx;
                    point.y = (int)newY;

                }

             }
        }
    }

    private void touchEnded(int pointerId) {
        Path path = pathMap.get(pointerId);
        bitmapCanvas.drawPath(path, paintLine); // draw to bitmap canvas
        path.reset();

    }



    private void touchStarted(float x, float y, int pointerId) {
        Path path;
        Point point;

        if (pathMap.containsKey(pointerId)){
            path = pathMap.get(pointerId);
            point = previousPointMap.get(pointerId);

        }else {
            path = new Path();
            pathMap.put(pointerId, path);
            point = new Point();
            previousPointMap.put(pointerId, point);
        }

        // move to the coordinates of the touch
        path.moveTo(x, y);
        point.x = (int)x;
        point.y = (int)y;


    }







}
