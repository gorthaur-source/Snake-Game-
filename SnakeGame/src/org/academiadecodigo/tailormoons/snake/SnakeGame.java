package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;

import java.util.Random;

public class SnakeGame {


    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 80;
    //
    protected static int delay = 60;
    private Snake snake;
    private int score;
    private Text scoreText = new Text(5, 2, "Score: " + score);


    SnakeGame(SnakeGrid grid) {
        this.grid = grid;
    }

    private void snakeInit() {
        snake = new Snake(grid);
        for (int i = 0; i < snake.getLength(); i++) {
            snake.getSnakeBody().add(new SnakeParts(SnakeGame.COLS / 2, SnakeGame.ROWS / 2 + i));
        }
    }

    public void init() {
        grid.initGrid();
        snakeInit();
        grid.setSnake(snake);
        grid.createFood();

    }


    public void start() throws InterruptedException {

        snake.keyboardHandling();

        while (!isGameOver()) {
            Thread.sleep(delay);
            snake.move();
            if (snakeHasEaten(grid.getFood())) {
                score += 100;
                updateScore();
                snake.grow();
                grid.getFood().hide();
                grid.createFood();
            }
        }

    }

    public boolean isGameOver() {
        if (snake.isDead()) {
            return true;
        }
        return false;
    }

    public void updateScore() {
        scoreText.delete();
        scoreText = new Text(5, 2, "Score: " + score);
        scoreText.setColor(Color.RED);
        scoreText.draw();

    }

    public boolean snakeHasEaten(Consumable food) {
        SnakeParts head = snake.getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }


}
