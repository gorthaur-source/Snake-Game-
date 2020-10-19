package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;

import java.util.Random;

public class SnakeGridObstacles implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 80;
    private boolean[][] isCovered = new boolean[COLS][ROWS];
    private Snake snake;
    private Consumable food;

    public SnakeGridObstacles() {
        this.snake = snake;
    }



    @Override
    public void initGrid() {

        Rectangle gridRect = new Rectangle(PADDING, PADDING, COLS * CELL_SIZE, ROWS * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();

        Rectangle[] rectanglesVertical = new Rectangle[35];

        for(int i = 0; i < 35; i++) {
            rectanglesVertical[i] = new Rectangle(60*CELL_SIZE,i * CELL_SIZE , CELL_SIZE,  CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[60][i] = true;
            rectanglesVertical[i] = new Rectangle(20*CELL_SIZE, ((ROWS - 35) + i) * CELL_SIZE, CELL_SIZE,  CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[20][(ROWS-35)+i] = true;
        }

        Rectangle[] rectanglesBox = new Rectangle[20];

        for(int i = 0; i < 20; i++) {
            rectanglesBox[i] = new Rectangle(30*CELL_SIZE, (10 + i) * CELL_SIZE, CELL_SIZE,  CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[30][10+i] = true;
            rectanglesBox[i] = new Rectangle(50*CELL_SIZE, (10 + i) * CELL_SIZE, CELL_SIZE,  CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[50][10+i] = true;

            }
        }

    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(COLS);
            y = new Random().nextInt(ROWS);
            food = new Consumable(x, y);
        } while (snake.snakeOnFood(food) || isCovered[x][y]);
        food.show();
    }

    @Override
    public Consumable getFood() {
        return food;
    }

    @Override
    public void checkCollision() {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();

        for (int i = 1; i < snake.getLength(); i++) {
            if (snake.getSnakeBody().get(i).getY() == headY && snake.getSnakeBody().get(i).getX() == headX) {
                snake.setIsDead();
                break;
            }
        }

        switch (snake.getHead().getDirection()) {
            case UP:
                headY--;
                break;
            case RIGHT:
                headX++;
                break;
            case DOWN:
                headY++;
                break;
            case LEFT:
                headX--;
                break;
        }
        if (headX < 0 || headX >= COLS || headY < 0 || headY >= ROWS) {
            return;
        }
            if (isCovered[headX][headY]) {
                snake.setIsDead();
            }

    }
    @Override
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

}
