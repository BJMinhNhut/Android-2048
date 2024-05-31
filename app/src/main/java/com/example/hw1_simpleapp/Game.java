package com.example.hw1_simpleapp;

import java.util.Random;

import java.util.Arrays;

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
        int i = mRandom.nextInt(4);
        int j = mRandom.nextInt(4);
        mBoard[i][j] = 2;
        return i*4 + j;
    }
}
