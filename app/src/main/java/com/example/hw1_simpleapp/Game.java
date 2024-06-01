package com.example.hw1_simpleapp;

import android.util.Log;
import android.util.Pair;

import java.util.Random;
import java.util.Arrays;
import java.util.Vector;

public class Game {
    private int mScore, mBest;
    int[][] mBoard;
    Random mRandom;

    Game() {
        mScore = 0;
        mBest = 0;
        mBoard = new int[4][4];
        mRandom = new Random();
        clearBoard();
    }

    public void clearBoard() {
        for (int[] row : mBoard) {
            Arrays.fill(row, 0);
        }
        mScore = 0;
    }

    public int getScore() {
        return mScore;
    }

    public int getBest() {
        return mBest;
    }

    public int getSquare(int i, int j) {
        return mBoard[i][j];
    }

    public int createRandomSquare() {
        Vector<Integer> empties = new Vector<>();
        for(int i = 0; i < 4; ++i)
            for(int j = 0; j < 4; ++j) if (mBoard[i][j] == 0)
                empties.add(i*4 + j);
        if (empties.isEmpty()) return -1;
        int index = empties.get(mRandom.nextInt(empties.size()));
        mBoard[index/4][index%4] = 2;
        Log.d("[Game]", "New position: " + index);
        return index;
    }
}
