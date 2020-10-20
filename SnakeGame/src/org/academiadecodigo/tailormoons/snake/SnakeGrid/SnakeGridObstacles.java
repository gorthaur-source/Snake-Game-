package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

import java.util.Random;

public class SnakeGridObstacles implements SnakeGrid {

    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static int ROWS;
    public static int COLS;
    private boolean[][] isCovered;
    private Snake[] snake = new Snake[2];
    private Consumable food;

    public SnakeGridObstacles() {

    }


    @Override
    public void initGrid(int rows, int columns) {
        ROWS = rows;
        COLS = columns;
        Rectangle gridRect = new Rectangle(PADDING, PADDING, columns * CELL_SIZE, rows * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();
        isCovered = new boolean[COLS][ROWS];
        Rectangle[] rectanglesVertical = new Rectangle[35];

        for (int i = 0; i < ROWS - 5; i++) {
            rectanglesVertical[i] = new Rectangle((COLS / 4 * 3) * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(COLS / 4) * 3][i] = true;
            rectanglesVertical[i] = new Rectangle((COLS / 4) * CELL_SIZE, ((ROWS - (ROWS - 5) + i) * CELL_SIZE), CELL_SIZE, CELL_SIZE);
            rectanglesVertical[i].setColor(Color.GRAY);
            rectanglesVertical[i].fill();
            isCovered[(COLS / 4)][(ROWS - (ROWS - 5)) + i] = true;
        }

        Rectangle[] rectanglesBox = new Rectangle[20];

        for (int i = 0; i < ROWS / 2; i++) {
            rectanglesBox[i] = new Rectangle((COLS / 8 * 3) * CELL_SIZE, (ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[COLS / 8 * 3][(ROWS / 4 + i)] = true;
            rectanglesBox[i] = new Rectangle((COLS / 8 * 5) * CELL_SIZE, (ROWS / 4 + i) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectanglesBox[i].setColor(Color.GRAY);
            rectanglesBox[i].fill();
            isCovered[COLS / 8 * 5][(ROWS / 4 + i)] = true;

        }
    }

    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(COLS);
            y = new Random().nextInt(ROWS);
            food = new Consumable(x, y);
        } while (snake[0].snakeOnFood(food) || snake[1].snakeOnFood(food) || isCovered[x][y]);
        food.show();
    }

    @Override
    public Consumable getFood() {
        return food;
    }


    public void checkCollisionSnake(Snake snake) {

        //  int headX
    }

    @Override
    public void checkCollision() {
        int headX = snake[0].getHead().getX();
        int headY = snake[0].getHead().getY();

        for (int i = 1; i < snake[0].getLength(); i++) {
            if (snake[0].getSnakeBody().get(i).getY() == headY && snake[0].getSnakeBody().get(i).getX() == headX) {
                snake[0].setIsDead();
                break;
            }
        }

        switch (snake[0].getHead().getDirection()) {
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
            snake[0].setIsDead();
        }

        headX = snake[1].getHead().getX();
        headY = snake[1].getHead().getY();

        for (int i = 1; i < snake[1].getLength(); i++) {
            if (snake[1].getSnakeBody().get(i).getY() == headY && snake[1].getSnakeBody().get(i).getX() == headX) {
                System.out.println("hello");
                snake[1].setIsDead();
                break;
            }
        }

        switch (snake[1].getHead().getDirection()) {
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
            snake[1].setIsDead();
        }
    }

    @Override
    public void setSnake(Snake[] snakes) {
        snake[0] = snakes[0];
        snake[1] = snakes[1];
    }

}

