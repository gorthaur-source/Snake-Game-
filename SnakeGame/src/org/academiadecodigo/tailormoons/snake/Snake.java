package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.Node;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;

import java.util.LinkedList;

public class Snake implements KeyboardHandler {

    private LinkedList<SnakeParts> snakeBody;
    private int length = 15;
    private boolean isDead = false;
    private boolean growing;
    private boolean directionChanged;
    // Keyboard
    KeyboardListener snakeListener = new KeyboardListener(this);
    Keyboard keyboard = new Keyboard(snakeListener);

    public Snake() {
        snakeBody = new LinkedList<>();
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

            checkCollision();
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
            }catch (IndexOutOfBoundsException e){
                snakeBody.get(i).setDirection(snakeBody.get(i - 1), getHead());
            }

        }
        checkCollision();
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


    public void checkCollision(){

        int headX=getHead().getX();
        int headY=getHead().getY();

        for(int i=1;i<length; i++){
            if (getSnakeBody().get(i).getY() == headY && getSnakeBody().get(i).getX() == headX) {
                isDead = true;
                break;
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


    public void keyboardHandling() {


        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);

        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_UP);

        keyboard.addEventListener(right);

        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_DOWN);

        keyboard.addEventListener(down);
    }


    public void changeDirection(Direction newDirection) {
        if (!(getHead().getDirection().isOpposite(newDirection))) {
            getHead().setDirection(newDirection);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if (isDirectionChanged()) {
            setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_LEFT: {
                    changeDirection(Direction.LEFT);
                }
                case KeyboardEvent.KEY_RIGHT: {
                    changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_UP: {
                    changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_DOWN: {
                    changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }

    public SnakeParts getTail() {
        return snakeBody.getLast();
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

    public int getLength() {
        return length;
    }
}
