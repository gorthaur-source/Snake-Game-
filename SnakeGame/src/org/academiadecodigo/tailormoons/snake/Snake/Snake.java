package org.academiadecodigo.tailormoons.snake.Snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;

import java.util.LinkedList;

public class Snake {

    private final LinkedList<SnakeParts> snakeBody;
    private int length = 15;
    private boolean isDead = false;
    private boolean growing;
    private boolean directionChanged;
    private double speed = 2;


    public Snake(int typeOfSnake) {
        snakeBody = new LinkedList<>();
        switch (typeOfSnake){
            case 1:
                for(int i=0;i<length;i++){
                    snakeBody.add(new SnakeParts(SnakeGridNormal.COLS/2,SnakeGridNormal.ROWS/2+i));
                }
                break;
            case 2:
                for(int i=0;i<length;i++){
                    snakeBody.add(new SnakeParts((SnakeGridNormal.COLS/6),SnakeGridNormal.ROWS/2+i) );
                }
                break;
            case 3:
                for(int i=0;i<length;i++){
                    snakeBody.add(new SnakeParts(((SnakeGridNormal.COLS/6)*5),SnakeGridNormal.ROWS/2+i));
                }
                break;
        }
    }


    public void grow() {
        growing = true;
    }

    public void move() {

        directionChanged = false;

            if (growing) {

                SnakeParts temp = snakeBody.get(length - 2);

                snakeBody.add(length - 1, new SnakeParts(temp.getX(), temp.getY()));
                length++;

                snakeBody.get(length - 2).copyDirection(temp);
                snakeBody.get(length - 2).setColor(Color.RED);

                for (int i = 0; i < length - 2; i++) {
                    snakeBody.get(i).moveInDirection();

                }

                snakeBody.get(0).setPreviousDirection(snakeBody.get(0).getDirection());

                for (int i = 1; i < length - 2; i++) {
                    snakeBody.get(i).setPreviousDirection(snakeBody.get(i).getDirection());
                    snakeBody.get(i).setDirection(snakeBody.get(i - 1), snakeBody.get(i + 1));
                }

                growing = false;
                return;
            }

            for (int i = 0; i < length; i++) {
                snakeBody.get(i).setPreviousDirection(snakeBody.get(i).getDirection());
                snakeBody.get(i).moveInDirection();

                if (snakeBody.get(i) == getHead()) {
                    continue;
                }
                try {
                    snakeBody.get(i).setDirection(snakeBody.get(i - 1), snakeBody.get(i + 1));
                } catch (IndexOutOfBoundsException e) {
                    snakeBody.get(i).setDirection(snakeBody.get(i - 1), getHead());
                }

            }

    }

    public void updateSprites(SnakeParts bodyPart) {

        //head cases
        if(bodyPart == snakeBody.get(0)) {
            if (bodyPart.getPreviousDirection() == Direction.LEFT) {
                bodyPart.updateSprite("assets/snakeHeadLeft");
            } else if (bodyPart.getPreviousDirection() == Direction.RIGHT) {
                bodyPart.updateSprite("assets/snakeHeadRight");
            } else if (bodyPart.getPreviousDirection() == Direction.UP) {
                bodyPart.updateSprite("assets/snakeHeadUp");
            } else if (bodyPart.getPreviousDirection() == Direction.DOWN) {
                bodyPart.updateSprite("assets/snakeHeadDown");
            }
        }
        //tail cases
        else if (bodyPart == snakeBody.get(length-1)) {
            if (bodyPart.getNextDirection() == Direction.LEFT) {
                bodyPart.updateSprite("assets/snakeTailLeft");
            } else if (bodyPart.getNextDirection() == Direction.RIGHT) {
                bodyPart.updateSprite("assets/snakeTailRight");
            } else if (bodyPart.getNextDirection() == Direction.UP) {
                bodyPart.updateSprite("assets/snakeTailUp");
            } else if (bodyPart.getNextDirection() == Direction.DOWN) {
                bodyPart.updateSprite("assets/snakeTailDown");
            }
        }
        //body cases: might be possible to shrink this
        else {
            if (bodyPart.getPreviousDirection() == Direction.LEFT) {
                if (bodyPart.getNextDirection() == Direction.LEFT) {
                    bodyPart.updateSprite("assets/snakeBodyHorizontal");
                } else if (bodyPart.getNextDirection() == Direction.DOWN) {
                    bodyPart.updateSprite("assets/snakeBodyLeftDown");
                } else if (bodyPart.getNextDirection() == Direction.UP) {
                    bodyPart.updateSprite("assets/snakeBodyLeftUp");
                }
            }else if (bodyPart.getPreviousDirection() == Direction.RIGHT) {
                if (bodyPart.getNextDirection() == Direction.RIGHT) {
                    bodyPart.updateSprite("assets/snakeBodyHorizontal");
                } else if (bodyPart.getNextDirection() == Direction.DOWN) {
                    bodyPart.updateSprite("assets/snakeBodyRightDown");
                } else if (bodyPart.getNextDirection() == Direction.UP) {
                    bodyPart.updateSprite("assets/snakeBodyRightUp");
                }
            } else if (bodyPart.getPreviousDirection() == Direction.DOWN) {
                if (bodyPart.getNextDirection() == Direction.DOWN) {
                    bodyPart.updateSprite("assets/snakeBodyVertical");
                } else if (bodyPart.getNextDirection() == Direction.LEFT) {
                    bodyPart.updateSprite("assets/snakeBodyRightUp");
                } else if (bodyPart.getNextDirection() == Direction.RIGHT) {
                    bodyPart.updateSprite("assets/snakeBodyLeftUp");
                }
            } else {
                if (bodyPart.getNextDirection() == Direction.UP) {
                    bodyPart.updateSprite("assets/snakeBodyVertical");
                } else if (bodyPart.getNextDirection() == Direction.LEFT) {
                    bodyPart.updateSprite("assets/snakeBodyRightDown");
                } else if (bodyPart.getNextDirection() == Direction.RIGHT) {
                    bodyPart.updateSprite("assets/snakeBodyLeftDown");

                }
            }
        }
    }

    public boolean snakeOnFood(Consumable food) {

        for (int i = 0; i < length; i++) {
            if (snakeBody.get(i).getX() == food.getX() && snakeBody.get(i).getY() == food.getY()) {
                return true;
            }
        }
        return false;
    }


    public boolean isDead() {
        return isDead;
    }


    public void changeDirection(Direction newDirection) {
        if (!(getHead().getDirection().isOpposite(newDirection))) {
            getHead().setDirection(newDirection);
        }
    }


    public void setIsDead() {
        isDead = true;
    }

    public SnakeParts getHead() {
        return snakeBody.getFirst();
    }

    public void setDirectionChanged(boolean value) {
        directionChanged = value;
    }

    public boolean isDirectionChanged() {
        return directionChanged;
    }

    public LinkedList<SnakeParts> getSnakeBody() {
        return snakeBody;
    }

    public double getSpeed() {
        return speed;
    }

    public int getLength() {
        return length;
    }
}
