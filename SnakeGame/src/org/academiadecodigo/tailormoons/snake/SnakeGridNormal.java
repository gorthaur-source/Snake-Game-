package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;

import java.util.Random;

public class SnakeGridNormal implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 80;
    private Snake snake;
    private Consumable food;

    public SnakeGridNormal() {
    }


    @Override
    public void initGrid() {

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
        } while (snake.snakeOnFood(food));
        food.show();
    }

    @Override
    public Consumable getFood() {
        return food;
    }

    public void checkCollision(){

        int headX=snake.getHead().getX();
        int headY=snake.getHead().getY();

        for(int i=1;i<snake.getLength(); i++){
            if (snake.getSnakeBody().get(i).getY() == headY && snake.getSnakeBody().get(i).getX() == headX) {
                snake.setIsDead();
                break;
            }
        }
    }

    @Override
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

}
