package com.example.adas.IdeationalPraxis;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.adas.Helper.Helpers;
import com.example.adas.Model.Result;
import com.example.adas.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class MazeView extends View {

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Cell[][] cells;
    private Cell player, exit;
    private static final int COLS = 5, ROWS = 5;
    private static final float WALL_THICKNESS = 6;
    private float cellSize, hMargin, vMargin;
    private Paint wallPaint, playerPaint, exitPaint;
    private Random random;

    private static final int MAX_ROUNDS = 2;
    private int curRounds;

    private double curScore = 0;
    private int totalScore = 0;

    private CountDownTimer cTimer = null;
    private long timeRemaining = 0;
    private TextView mTimerTextView;

    public MazeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initialiseObjects();
        setPaint();
        generateMaze();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.LTGRAY);

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

        float rectMargin = cellSize/15;

        canvas.drawRect(
                player.col * cellSize + rectMargin,
                player.row * cellSize + rectMargin,
                (player.col + 1) * cellSize - rectMargin,
                (player.row + 1) * cellSize - rectMargin,
                playerPaint
        );

        canvas.drawRect(
                exit.col * cellSize + rectMargin,
                exit.row * cellSize + rectMargin,
                (exit.col + 1) * cellSize - rectMargin,
                (exit.row + 1) * cellSize - rectMargin,
                exitPaint
        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) return true;

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();

            float playerCenterX = hMargin + (player.col + 0.5f) * cellSize;
            float playerCenterY = vMargin + (player.row + 0.5f) * cellSize;

            // difference in coordinates between player touch and player rectangle
            float dx = x - playerCenterX;
            float dy = y - playerCenterY;

            float absDx = Math.abs(dx);
            float absDy = Math.abs(dy);

            if (absDx > cellSize || absDy > cellSize) {
                if (absDx > absDy) {
                    if (dx > 0)
                        movePlayer(Direction.RIGHT);
                    else
                        movePlayer(Direction.LEFT);
                } else {
                    if (dy > 0)
                        movePlayer(Direction.DOWN);
                    else
                        movePlayer(Direction.UP);
                }
            }
        }

        return super.onTouchEvent(event);
    }

    private void setPaint() {
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(WALL_THICKNESS);

        playerPaint.setColor(Color.BLUE);
        exitPaint.setColor(Color.RED);
    }

    private void initialiseObjects() {
        wallPaint = new Paint();
        playerPaint = new Paint();
        exitPaint = new Paint();
        random = new Random();
        curRounds = 0;
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

        player = cells[0][0];
        exit = cells[COLS - 1][ROWS - 1];

        currentCell = cells[0][0];
        currentCell.visited = true;

        startTimer();

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

    private void movePlayer(Direction direction) {
        switch (direction) {
            case UP:
                if (!player.topWall)
                    player = cells[player.col][player.row - 1];
                break;
            case DOWN:
                if (!player.bottomWall)
                    player = cells[player.col][player.row + 1];
                break;
            case LEFT:
                if (!player.leftWall)
                    player = cells[player.col - 1][player.row];
                break;
            case RIGHT:
                if (!player.rightWall)
                    player = cells[player.col + 1][player.row];
                break;
        }

        checkExit();
        invalidate();
    }

    private void checkExit() {
        if (player == exit)
        {
            if (curRounds < MAX_ROUNDS) {
                generateMaze();
                endRound();
            } else {
                endRound();
                completeMaze();
            }
        }
    }

    public void completeMaze() {
        ((IdeationalPraxisActivity) getContext()).setScore(totalScore);
        Log.i("IP Info", "Total Score: " + ((IdeationalPraxisActivity) getContext()).getScore());

        ((IdeationalPraxisActivity) getContext()).sendIntent();
    }

    private void endRound() {
        if (curRounds < MAX_ROUNDS)
        {
            curRounds++;
            calculateScore();
        } else {
            cancelTimer();
        }
    }

    private void calculateScore() {
        double roundScore = (timeRemaining / 3) - 5;
        curScore = curScore + roundScore;

        totalScore = (int) curScore / curRounds;
    }

    private void startTimer() {
        cancelTimer();

        cTimer = new CountDownTimer(30000 + 1000, 1000) {
            @Override
            public void onTick(long milUntilFinish) {
                timeRemaining = milUntilFinish / 1000;
                updateTimerText(mTimerTextView, timeRemaining);
            }

            @Override
            public void onFinish() {
                generateMaze();
                curRounds++;
            }
        };

        cTimer.start();
    }

    private void cancelTimer() {
        if(cTimer != null) {
            cTimer.cancel();
        }
    }

    private void updateTimerText(TextView textView, long seconds) {
        textView.setText("Time Remaining: " + seconds);
    }

    public void getTextView(TextView textView) {
        mTimerTextView = textView;
    }

    public int passScore() {
        return totalScore;
    }

    private class Cell {
        boolean
                topWall = true,
                leftWall = true,
                bottomWall = true,
                rightWall = true,
                visited = false;

        int col, row;

        private Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}
