package org.academiadecodigo.tailormoons.snake.Sprites;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BodySprite implements Sprites {
    private Picture bodyHorizontal;
    private Picture bodyVertical;
    private Picture bodyCornerLeftToUp;
    private Picture bodyCornerUptoLeft;
    private Picture bodyCornerRightToUp;
    private Picture bodyCornerUptoRight;
    private Picture selectedPicture;

    public BodySprite(double x, double y) {
        bodyCornerLeftToUp = new Picture(x, y, "assets/SnakeParts/body_corner4.png");
        bodyCornerRightToUp = new Picture(x, y, "assets/SnakeParts/body_corner2.png");
        bodyCornerUptoLeft = new Picture(x, y, "assets/SnakeParts/body_corner3.png");
        bodyCornerUptoRight = new Picture(x, y, "assets/SnakeParts/body_corner1.png");
        bodyHorizontal = new Picture(x, y, "assets/SnakeParts/body_horizontal.png");
        bodyVertical = new Picture(x, y, "assets/SnakeParts/body_vertical.png");
        selectedPicture = bodyVertical;
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

        int x1=0;
        int y1=0;

        selectedPicture.delete();

        switch (typeOfSprite) {
            case UP:
                x1 = bodyVertical.getX();
                y1 = bodyVertical.getY();
                selectedPicture = bodyVertical;
                break;
            case DOWN:
                x1 = bodyVertical.getX();
                y1 = bodyVertical.getY();
                selectedPicture = bodyVertical;
                break;
            case LEFT:
                x1 = bodyHorizontal.getX();
                y1 = bodyHorizontal.getY();
                selectedPicture = bodyHorizontal;
                break;
            case RIGHT:
                x1 = bodyHorizontal.getX();
                y1 = bodyHorizontal.getY();
                selectedPicture = bodyHorizontal;
                break;
            case CONERUPRIGHT:
                x1 = bodyCornerUptoRight.getX();
                y1 = bodyCornerUptoRight.getY();
                selectedPicture = bodyCornerUptoRight;
                break;
            case CORNERLEFTUP:
                x1 = bodyCornerLeftToUp.getX();
                y1 = bodyCornerLeftToUp.getY();
                selectedPicture = bodyCornerLeftToUp;
                break;
            case CORNERUPLEFT:
                x1 = bodyCornerUptoLeft.getX();
                y1 = bodyCornerUptoLeft.getY();
                selectedPicture = bodyCornerUptoLeft;
                break;
            case CORNERRIGHTUP:
                x1 = bodyCornerRightToUp.getX();
                y1 = bodyCornerRightToUp.getY();
                selectedPicture = bodyCornerRightToUp;
                break;
        }

        selectedPicture.translate(x-x1,y-y1);
        selectedPicture.draw();

    }

    public TypeOfSprite getTypeOfSprite(){

        if (selectedPicture == bodyHorizontal){
            return TypeOfSprite.LEFT;
        }
        if (selectedPicture == bodyVertical){
            return TypeOfSprite.UP;
        }

        if (selectedPicture == bodyCornerLeftToUp){
            return  TypeOfSprite.CORNERLEFTUP;
        }

        if (selectedPicture == bodyCornerUptoRight){
            return TypeOfSprite.CONERUPRIGHT;
        }

        if (selectedPicture==bodyCornerRightToUp){
            return TypeOfSprite.CORNERRIGHTUP;
        }

        if(selectedPicture==bodyCornerUptoLeft){
            return TypeOfSprite.CORNERUPLEFT;
        }
        return null;
    }
}
