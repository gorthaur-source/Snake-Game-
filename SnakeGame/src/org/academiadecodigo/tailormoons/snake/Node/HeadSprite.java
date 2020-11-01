package org.academiadecodigo.tailormoons.snake.Node;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class HeadSprite implements Sprite {

    private static Picture headUp;
    private static Picture headDown;
    private static Picture headRight;
    private static Picture headLeft;
    private Picture selectedPicture;

    public HeadSprite(double x, double y) {
        headUp = new Picture(x, y, "assets/SnakeParts/head_up.png");
        headDown = new Picture(x, y, "assets/SnakeParts/head_down.png");
        headRight = new Picture(x, y, "assets/SnakeParts/head_right.png");
        headLeft = new Picture(x, y, "assets/SnakeParts/head_left.png");
        selectedPicture = headUp;
        selectedPicture.draw();
    }


    @Override
    public void translate(double x, double y) {
        selectedPicture.translate(x, y);
    }

    @Override
    public void setSprite(TypeOfSprite typeOfSprite) {
        int x = selectedPicture.getX();
        int y = selectedPicture.getY();

        int x1 = 0;
        int y1 = 0;
        selectedPicture.delete();

        switch (typeOfSprite) {
            case RIGHT:
                x1 = headRight.getX();
                y1 = headRight.getY();
                selectedPicture = headRight;
                break;
            case LEFT:
                x1 = headLeft.getX();
                y1 = headLeft.getY();
                selectedPicture = headLeft;
                break;
            case DOWN:
                x1 = headDown.getX();
                y1 = headDown.getY();
                selectedPicture = headDown;
                break;
            case UP:
                x1 = headUp.getX();
                y1 = headUp.getY();
                selectedPicture = headUp;
                break;
        }

        selectedPicture.translate(x-x1,y-y1);
        selectedPicture.draw();


    }


}
