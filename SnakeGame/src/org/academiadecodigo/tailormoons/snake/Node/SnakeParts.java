package org.academiadecodigo.tailormoons.snake.Node;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;

public class SnakeParts extends Node {

    private Rectangle figure;
    private Picture sprite;
    private Direction direction;
    private Direction previousDirection;
    private Direction nextDirection;


    public SnakeParts(int x, int y) {
        super(x, y);
        direction = Direction.UP;
        previousDirection= Direction.UP;
        figure = new Rectangle(x * SnakeGame.CELL_SIZE + SnakeGame.PADDING, y * SnakeGame.CELL_SIZE + SnakeGame.PADDING, SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE);
        figure.setColor(Color.GREEN);
        figure.fill();


    }

    public void updateSprite(String path) {
        sprite = new Picture(x, y, path);
        sprite.draw();
    }

    public void setColor(Color color){
        figure.setColor(color);
    }

    public Direction getDirection() {
        return direction;
    }

    public void moveInDirection() {

        switch (direction) {
            case LEFT:
                if (x == 0) {
                    x = SnakeGridNormal.COLS - 1;
                    figure.delete();
                    figure = new Rectangle(x * SnakeGame.CELL_SIZE + SnakeGame.PADDING, y * SnakeGame.CELL_SIZE + SnakeGame.PADDING, SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE);
                    figure.setColor(Color.GREEN);
                    figure.fill();
                    break;
                }
                x--;
                figure.translate(-SnakeGame.CELL_SIZE, 0);
                break;
            case DOWN:
                if (y == SnakeGridNormal.ROWS - 1) {
                    y = 0;
                    figure.delete();
                    figure = new Rectangle(x * SnakeGame.CELL_SIZE + SnakeGame.PADDING, y * SnakeGame.CELL_SIZE + SnakeGame.PADDING, SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE);
                    figure.setColor(Color.GREEN);
                    figure.fill();
                    break;
                }
                y++;
                figure.translate(0, SnakeGame.CELL_SIZE);
                break;
            case UP:
                if (y == 0) {
                    y = SnakeGridNormal.ROWS - 1;
                    figure.delete();
                    figure = new Rectangle(x * SnakeGame.CELL_SIZE + SnakeGame.PADDING, y * SnakeGame.CELL_SIZE + SnakeGame.PADDING, SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE);
                    figure.setColor(Color.GREEN);
                    figure.fill();
                    break;
                }
                y--;
                figure.translate(0, -SnakeGame.CELL_SIZE);
                break;
            case RIGHT:
                if (x == SnakeGridNormal.COLS - 1) {
                    x = 0;
                    figure.delete();
                    figure = new Rectangle(x * SnakeGame.CELL_SIZE + SnakeGame.PADDING, y * SnakeGame.CELL_SIZE + SnakeGame.PADDING, SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE);
                    figure.setColor(Color.GREEN);
                    figure.fill();
                    break;
                }
                x++;
                figure.translate(SnakeGame.CELL_SIZE, 0);
                break;
        }
    }


    public void setDirection(SnakeParts previous, SnakeParts next) {
        direction = previous.getPreviousDirection();
        nextDirection = next.getNextDirection();
    }



    public void copyDirection(SnakeParts part) {
        previousDirection = part.getPreviousDirection();
        direction = part.getDirection();
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setPreviousDirection(Direction direction) {
        previousDirection = direction;
    }

    public void setNextDirection(Direction direction) {
        nextDirection = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}