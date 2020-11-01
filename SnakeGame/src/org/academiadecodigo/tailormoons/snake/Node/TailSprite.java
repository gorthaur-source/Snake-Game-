package org.academiadecodigo.tailormoons.snake.Node;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class TailSprite implements Sprite {

    private Picture tailUp;
    private Picture tailDown;
    private Picture tailRight;
    private Picture tailLeft;
    private Picture selectedPicure;

    public TailSprite(double x, double y) {
        tailUp = new Picture(x, y, "assets/SnakeParts/tail_up.png");
        tailDown = new Picture(x, y, "assets/SnakeParts/tail_down.png");
        tailRight = new Picture(x, y, "assets/SnakeParts/tail_right.png");
        tailLeft = new Picture(x, y, "assets/SnakeParts/tail_left.png");
        selectedPicure = tailUp;
        selectedPicure.draw();
    }


    @Override
    public void translate(double x, double y) {
        selectedPicure.translate(x, y);
    }

    @Override
    public void setSprite(TypeOfSprite typeOfSprite) {

        int x = selectedPicure.getX();
        int y = selectedPicure.getY();
        int x1=0;
        int y1=0;
        selectedPicure.delete();


        switch (typeOfSprite) {
            case RIGHT:
                x1 = tailRight.getX();
                y1 = tailRight.getY();
                selectedPicure = tailRight;
                break;
            case LEFT:
                x1 = tailLeft.getX();
                y1 = tailLeft.getY();
                selectedPicure = tailLeft;
                break;
            case DOWN:
                x1 = tailDown.getX();
                y1 = tailDown.getY();
                selectedPicure = tailDown;
                break;
            case UP:
                x1 = tailUp.getX();
                y1 = tailUp.getY();
                selectedPicure = tailUp;
                break;
        }

        selectedPicure.translate(x-x1,y-y1);
        selectedPicure.draw();

    }
}
