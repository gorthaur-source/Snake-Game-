package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

import java.util.Objects;
import java.util.Random;

public class SnakeGridNormal implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 23;
    public static final int ROWS = 40;
    public static final int COLS = 60;
    private boolean[][] isCovered = new boolean[COLS][ROWS];
    private Picture background;
    private String filePath;


    public SnakeGridNormal() {
    }


    @Override
    public void initGrid() {

        int zeroToFour = (int) (Math.random() * 5);

        switch(zeroToFour) {
            case 0:
                filePath = "assets/Backgrounds/earth.jpg";
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

    }

    public boolean[][] getIsCovered() {
        return isCovered;
    }



}

