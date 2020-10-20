package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

import java.util.Random;

public class SnakeGridObstacles implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 60;
    private boolean[][] isCovered;


    public SnakeGridObstacles() {

    }

    @Override
    public void initGrid() {
        Rectangle gridRect = new Rectangle(PADDING, PADDING, COLS * CELL_SIZE, ROWS * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();
        isCovered = new boolean[COLS][ROWS];
        Rectangle[] rectanglesVertical = new Rectangle[35];

        for (int i = 0; i < ROWS - 5; i++) {
            rectanglesVertical[i] = new Rectangle((COLS / 4 * 3) * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(COLS / 4) * 3][i] = true;
            rectanglesVertical[i] = new Rectangle((COLS / 4) * CELL_SIZE, ((ROWS - (ROWS - 5) + i) * CELL_SIZE), CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(COLS / 4)][(ROWS - (ROWS - 5)) + i] = true;
        }

        Rectangle[] rectanglesBox = new Rectangle[20];

        for (int i = 0; i < ROWS / 2; i++) {
            rectanglesBox[i] = new Rectangle((COLS / 8 * 3) * CELL_SIZE, (ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[COLS / 8 * 3][(ROWS / 4 + i)] = true;
            rectanglesBox[i] = new Rectangle((COLS / 8 * 5) * CELL_SIZE, (ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[COLS / 8 * 5][(ROWS / 4 + i)] = true;

        }
    }


    public boolean[][] getIsCovered() {
        return isCovered;
    }
}



