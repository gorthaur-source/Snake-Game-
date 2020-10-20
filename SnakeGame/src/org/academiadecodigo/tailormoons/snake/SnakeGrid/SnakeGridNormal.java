package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

import java.util.Objects;
import java.util.Random;

public class SnakeGridNormal implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 60;
    private boolean[][] isCovered = new boolean[COLS][ROWS];

    public SnakeGridNormal() {
    }


    @Override
    public void initGrid() {
        Rectangle gridRect = new Rectangle(PADDING, PADDING, COLS * CELL_SIZE, ROWS * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();

    }

    public boolean[][] getIsCovered() {
        return isCovered;
    }



}

