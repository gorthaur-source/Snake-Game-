package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;

import java.util.Objects;
import java.util.Random;

public class SnakeGridNormal implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 23;
    public static final int ROWS = 40;
    public static final int COLS = 60;
    private boolean[][] isCovered = new boolean[COLS][ROWS];
    private final String[] BACKGROUNDS = {
            "assets/Backgrounds/space.png",
            "assets/Backgrounds/space1.jpg",
            "assets/Backgrounds/lightspeed.jpg",
            "assets/Backgrounds/shady.jpg",
            "assets/Backgrounds/hole.jpg",
            "assets/Backgrounds/hole1.jpg"
    };

    public SnakeGridNormal() {
    }


    @Override
    public void initGrid() {
        int randomBackground = (int) (Math.random() * BACKGROUNDS.length);

        Picture background = new Picture(PADDING, PADDING, BACKGROUNDS[randomBackground]);
        background.draw();

    }

    public boolean[][] getIsCovered() {
        return isCovered;
    }



}

