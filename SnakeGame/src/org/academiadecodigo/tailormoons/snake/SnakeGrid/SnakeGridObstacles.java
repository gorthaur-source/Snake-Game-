package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SnakeGridObstacles implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 23;
    private boolean[][] isCovered;
    private Picture background;
    private String filePath;
    private Picture flame;
    public SnakeGridObstacles() {

    }

    @Override
    public void initGrid() {

        int zeroToFive = (int) (Math.random() * 6);

        switch(zeroToFive) {
            case 0:
                filePath = "assets/Backgrounds/space.png";
                break;
            case 1:
                filePath = "assets/Backgrounds/space1.jpg";
                break;
            case 2:
                filePath = "assets/Backgrounds/lightspeed.jpg";
                break;
            case 3:
                filePath = "assets/Backgrounds/shady.jpg";
                break;
            case 4:
                filePath = "assets/Backgrounds/hole.jpg";
                break;
            case 5:
                filePath = "assets/Backgrounds/hole1.jpg";
                break;
        }

        background = new Picture(PADDING, PADDING, filePath);
        background.draw();
        isCovered = new boolean[SnakeGridNormal.COLS][SnakeGridNormal.ROWS];
        for (int i = 0; i < SnakeGridNormal.ROWS - 5; i++) {
            flame = new Picture(SnakeGridNormal.COLS / 6 * 5 * CELL_SIZE, i * CELL_SIZE, "assets/Flames/flame1.png");
            flame.draw();
            isCovered[(SnakeGridNormal.COLS / 6) * 5][i] = true;
            flame = new Picture (((SnakeGridNormal.COLS / 6)) * CELL_SIZE, ((SnakeGridNormal.ROWS - (SnakeGridNormal.ROWS - 5)) + i)*CELL_SIZE, "assets/Flames/flame1.png");
            flame.draw();
            isCovered[(SnakeGridNormal.COLS / 6)][(SnakeGridNormal.ROWS - (SnakeGridNormal.ROWS - 5)) + i] = true;
        }


        for (int i = 0; i < SnakeGridNormal.ROWS / 2; i++) {
            isCovered[(SnakeGridNormal.COLS / 6 * 2)][(SnakeGridNormal.ROWS / 4 + i)] = true;
            flame = new Picture ((SnakeGridNormal.COLS / 6 * 2)*CELL_SIZE, ((SnakeGridNormal.ROWS / 4 + i)*CELL_SIZE),  "assets/Flames/flame10.png");
            flame.draw();
            isCovered[SnakeGridNormal.COLS / 6 * 4][(SnakeGridNormal.ROWS / 4 + i)] = true;
            flame = new Picture((SnakeGridNormal.COLS / 6 * 4) * CELL_SIZE, (SnakeGridNormal.ROWS / 4 + i)*CELL_SIZE,  "assets/Flames/flame10.png");
            flame.draw();

        }
    }

    public boolean[][] getIsCovered() {
        return isCovered;
    }
}



