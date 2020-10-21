package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

import java.util.Random;

public class SnakeGridObstacles implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 23;
    private boolean[][] isCovered;
    private Picture background;
    private String filePath;

    public SnakeGridObstacles() {

    }

    @Override
    public void initGrid() {
        int zeroToFour = (int) (Math.random() * 5);

        switch(zeroToFour) {
            case 0:
                filePath = "assets/Backgrounds/earth.png";
                break;
            case 1:
                filePath = "assets/Backgrounds/space.png";
                break;
            case 2:
                filePath = "assets/Backgrounds/space1.jpg";
                break;
            case 3:
                filePath = "assets/Backgrounds/lightspeed.jpg";
                break;
            case 4:
                filePath = "assets/Backgrounds/shady.jpg";
        }

        background = new Picture(PADDING, PADDING, filePath);
        background.draw();
        isCovered = new boolean[SnakeGridNormal.COLS][SnakeGridNormal.ROWS];
        Rectangle[] rectanglesVertical = new Rectangle[35];

        for (int i = 0; i < SnakeGridNormal.ROWS - 5; i++) {
            rectanglesVertical[i] = new Rectangle((SnakeGridNormal.COLS / 4 * 3) * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(SnakeGridNormal.COLS / 4) * 3][i] = true;
            rectanglesVertical[i] = new Rectangle((SnakeGridNormal.COLS / 4) * CELL_SIZE, ((SnakeGridNormal.ROWS - (SnakeGridNormal.ROWS - 5) + i) * CELL_SIZE), CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(SnakeGridNormal.COLS / 4)][(SnakeGridNormal.ROWS - (SnakeGridNormal.ROWS - 5)) + i] = true;
        }

        Rectangle[] rectanglesBox = new Rectangle[20];

        for (int i = 0; i < SnakeGridNormal.ROWS / 2; i++) {
            rectanglesBox[i] = new Rectangle((SnakeGridNormal.COLS / 6 * 2) * CELL_SIZE, (SnakeGridNormal.ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[(SnakeGridNormal.COLS / 6 * 2)][(SnakeGridNormal.ROWS / 4 + i)] = true;
            rectanglesBox[i] = new Rectangle((SnakeGridNormal.COLS / 6 * 4) * CELL_SIZE, (SnakeGridNormal.ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[SnakeGridNormal.COLS / 6 * 4][(SnakeGridNormal.ROWS / 4 + i)] = true;

        }
    }


    public boolean[][] getIsCovered() {
        return isCovered;
    }
}



