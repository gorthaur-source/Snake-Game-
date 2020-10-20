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
    private boolean[][] isCovered;


    public SnakeGridObstacles() {

    }

    @Override
    public void initGrid() {
        Rectangle gridRect = new Rectangle(PADDING, PADDING, SnakeGridNormal.COLS * CELL_SIZE, SnakeGridNormal.ROWS * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();
        isCovered = new boolean[SnakeGridNormal.COLS][SnakeGridNormal.ROWS];
        Rectangle[] rectanglesVertical = new Rectangle[35];

        for (int i = 0; i < SnakeGridNormal.ROWS - 5; i++) {
            rectanglesVertical[i] = new Rectangle(((long)SnakeGridNormal.COLS / 4 * 3) * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(SnakeGridNormal.COLS / 4) * 3][i] = true;
            rectanglesVertical[i] = new Rectangle(((long)SnakeGridNormal.COLS / 4) * CELL_SIZE, ((SnakeGridNormal.ROWS - (SnakeGridNormal.ROWS - 5) + i) * CELL_SIZE), CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(SnakeGridNormal.COLS / 4)][(SnakeGridNormal.ROWS - (SnakeGridNormal.ROWS - 5)) + i] = true;
        }

        Rectangle[] rectanglesBox = new Rectangle[20];

        for (int i = 0; i < SnakeGridNormal.ROWS / 2; i++) {
            rectanglesBox[i] = new Rectangle(((long)SnakeGridNormal.COLS / 8 * 3) * CELL_SIZE, ((long)SnakeGridNormal.ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[SnakeGridNormal.COLS / 8 * 3][(SnakeGridNormal.ROWS / 4 + i)] = true;
            rectanglesBox[i] = new Rectangle(((long)SnakeGridNormal.COLS / 8 * 5) * CELL_SIZE, ((long)SnakeGridNormal.ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[SnakeGridNormal.COLS / 8 * 5][(SnakeGridNormal.ROWS / 4 + i)] = true;

        }
    }


    public boolean[][] getIsCovered() {
        return isCovered;
    }
}



