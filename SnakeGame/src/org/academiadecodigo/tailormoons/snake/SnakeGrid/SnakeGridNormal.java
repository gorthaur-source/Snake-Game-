package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

import java.util.Random;

public class SnakeGridNormal implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    private static int ROWS;
    private static int COLS;
    private Snake[] snake = new Snake[2];
    private Consumable food;

    public SnakeGridNormal() {
    }


    @Override
    public void initGrid(int rows, int columns) {
        ROWS = rows;
        COLS = columns;
        Rectangle gridRect = new Rectangle(PADDING, PADDING, COLS * CELL_SIZE, ROWS * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();

    }

    @Override
    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(COLS);
            y = new Random().nextInt(ROWS);
            food = new Consumable(x, y);
        } while (snake[0].snakeOnFood(food));
        food.show();
    }

    @Override
    public Consumable getFood() {
        return food;
    }

    public void checkCollision(){

        int headX=snake[0].getHead().getX();
        int headY=snake[0].getHead().getY();

        for(int i=1;i<snake[0].getLength(); i++){
            if (snake[0].getSnakeBody().get(i).getY() == headY && snake[0].getSnakeBody().get(i).getX() == headX) {
                snake[0].setIsDead();
                break;
            }
        }
    }

    @Override
    public void setSnake(Snake[] snakes) {
        snake[0] = snakes[0];
        if(snake[1] != null) {
            snake[1] = snakes[1];
        }
    }

}
