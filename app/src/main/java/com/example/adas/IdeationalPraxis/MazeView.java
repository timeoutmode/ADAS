package com.example.adas.IdeationalPraxis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MazeView extends View {

    private Cell[][] cells;
    private static final int COLS = 5, ROWS = 10;
    private static final float WALL_THICKNESS = 6;
    private float cellSize, hMargin, vMargin;
    private Paint wallPaint;

    public MazeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(WALL_THICKNESS);

        generateMaze();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        if (width/height < COLS/ROWS)
            cellSize = width/(COLS+1);
        else
            cellSize = height/(ROWS+1);

        hMargin = (width-COLS*cellSize)/2;
        vMargin = (height-ROWS*cellSize)/2;

        canvas.translate(hMargin, vMargin);

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                if (cells[x][y].topWall) {
                    canvas.drawLine(
                            x * cellSize,
                            y * cellSize,
                            (x + 1) * cellSize,
                            y * cellSize,
                            wallPaint
                    );
                }

                if (cells[x][y].leftWall) {
                    canvas.drawLine(
                            x * cellSize,
                            y * cellSize,
                            x * cellSize,
                            (y + 1) * cellSize,
                            wallPaint
                    );
                }

                if (cells[x][y].bottomWall) {
                    canvas.drawLine(
                            x * cellSize,
                            (y + 1) * cellSize,
                            (x + 1) * cellSize,
                            (y + 1) * cellSize,
                            wallPaint
                    );
                }

                if (cells[x][y].rightWall) {
                    canvas.drawLine(
                            (x + 1) * cellSize,
                            y * cellSize,
                            (x + 1) * cellSize,
                            (y + 1) * cellSize,
                            wallPaint
                    );
                }
            }
        }
    }

    private void initialiseVariables() {
        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(WALL_THICKNESS);
    }

    private void generateMaze() {
        cells = new Cell[COLS][ROWS];

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    private class Cell {
        boolean
                topWall = true,
                leftWall = true,
                bottomWall = true,
                rightWall = true;

        int col, row;

        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}