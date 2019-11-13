package com.example.adas.IdeationalPraxis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeView extends View {

    private Cell[][] cells;
    private static final int COLS = 5, ROWS = 10;
    private static final float WALL_THICKNESS = 6;
    private float cellSize, hMargin, vMargin;
    private Paint wallPaint;
    private Random random;

    public MazeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initialiseObjects();
        setWallPaint();
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

    private void setWallPaint() {
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(WALL_THICKNESS);
    }

    private void initialiseObjects() {
        wallPaint = new Paint();
        random = new Random();
    }

    private void generateMaze() {
        Stack<Cell> cellStack = new Stack<>();
        Cell currentCell, nextCell;

        cells = new Cell[COLS][ROWS];

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }

        currentCell = cells[0][0];
        currentCell.visited = true;

        do {
            nextCell = getNeighbour(currentCell);
            if (nextCell != null) {
                removeWall(currentCell, nextCell);
                cellStack.push(currentCell);
                currentCell = nextCell;
                currentCell.visited = true;
            } else {
                currentCell = cellStack.pop();
            }
        } while (!cellStack.empty());
    }

    private Cell getNeighbour(Cell cell) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        // left neighbour
        if (cell.col > 0) {
            if (!cells[cell.col - 1][cell.row].visited) {
                neighbours.add(cells[cell.col - 1][cell.row]);
            }
        }

        // right neighbour
        if (cell.col < COLS - 1) {
            if (!cells[cell.col + 1][cell.row].visited) {
                neighbours.add(cells[cell.col + 1][cell.row]);
            }
        }

        // top neighbour
        if (cell.row > 0) {
            if (!cells[cell.col][cell.row - 1].visited) {
                neighbours.add(cells[cell.col][cell.row - 1]);
            }
        }

        // bottom neighbour
        if (cell.row < ROWS - 1) {
            if (!cells[cell.col][cell.row + 1].visited) {
                neighbours.add(cells[cell.col][cell.row + 1]);
            }
        }

        if (neighbours.size() > 0) {
            int index = random.nextInt(neighbours.size());
            return neighbours.get(index);
        }

        return null;
    }

    private void removeWall(Cell c1, Cell c2) {
        if (c1.col == c2.col && c1.row == c2.row + 1) {
            c1.topWall = false;
            c2.bottomWall = false;
        }

        if (c1.col == c2.col && c1.row == c2.row - 1) {
            c1.bottomWall = false;
            c2.topWall = false;
        }

        if (c1.col == c2.col + 1 && c1.row == c2.row) {
            c1.leftWall = false;
            c2.rightWall = false;
        }

        if (c1.col == c2.col - 1 && c1.row == c2.row) {
            c1.rightWall = false;
            c2.leftWall = false;
        }
    }

    private class Cell {
        boolean
                topWall = true,
                leftWall = true,
                bottomWall = true,
                rightWall = true,
                visited = false;

        int col, row;

        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}
