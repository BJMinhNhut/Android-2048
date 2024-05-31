package com.example.hw1_simpleapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Game mGame;
    private static int MAX_COLOR = Color.rgb(159, 50, 0); // orange
    private static int START_COLOR = Color.argb(32, 238, 228, 218); // low
    Vector<TextView> mSquareList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initBoardDisplay();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int squareID = mGame.createRandomSquare();
                updateSquare(squareID);
            default:

        }
        return super.onTouchEvent(event);
    }

    void updateSquare(int id) {
        TextView square = mSquareList.get(id);
        int value = mGame.getSquare(id/4, id%4);
        int color = getColorByValue(value);

        square.setBackgroundColor(color);
        if (value > 0) {
            square.setText(String.valueOf(value));
        }
    }

    public void startNewGame(View view) {
        initBoardDisplay();
    }

    void initBoardDisplay() {
        mGame = new Game();
        mSquareList = new Vector<>();
        setTextById(R.id.score_box, mGame.getScore());
        setTextById(R.id.best_box, mGame.getBest());
        drawBoardSquares();
    }

    void setTextById(int id, int value) {
        TextView textView = findViewById(id);
        textView.setText(String.valueOf(value));
    }

    void drawBoardSquares() {
        GridLayout mainGrid = findViewById(R.id.board);
        mainGrid.removeAllViews();
        int margin = 10;

        for(int row = 0; row < 4; ++row) {
            for(int col = 0; col < 4; ++col) {
                TextView square = new TextView(this);
                square.setGravity(Gravity.CENTER);
                square.getAutoSizeMaxTextSize();
                square.setTypeface(square.getTypeface(), Typeface.BOLD);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = dpToPx(65);
                params.height = dpToPx(65);
                params.setMargins(margin, margin, margin, margin);

                mainGrid.addView(square, params);
                mSquareList.add(square);
//                System.out.println(row + " " + col + " " + color)
            }
        }

        for(int id = 0; id < 16; ++id)
            updateSquare(id);
    }

    int getColorByValue(int value) {
        if (value == 0) return START_COLOR;
        double ratio = (Math.min(Math.log(value + 1), 11.0) / 11.0);
        int r = 200;
        int g = (int)(200 * (1-ratio*0.7));
        int b = (int)(200 * (1-ratio));
        return Color.rgb(r, g, b);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

}