package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;

import java.util.Random;

public class SnakeGame {


    // grid stuff
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 80;
    private Consumable food;
    //
    protected static int delay = 20;
    private Snake snake;
    private int score;
    private Text scoreText = new Text(5, 2, "Score: " + score);


    SnakeGame() {
    }


    public void initGrid() {
        Rectangle gridRect = new Rectangle(PADDING, PADDING, COLS * CELL_SIZE, ROWS * CELL_SIZE);
        gridRect.setColor(Color.BLACK);
        gridRect.fill();
        scoreText.setColor(Color.RED);
        scoreText.draw();
    }

    private void snakeInit() {
        snake = new Snake();
        for (int i = 0; i < snake.getLength(); i++) {
            snake.getSnakeBody().add(new SnakeParts(SnakeGame.COLS / 2, SnakeGame.ROWS / 2 + i));
        }
    }

    public void init() {
        initGrid();
        snakeInit();
        createFood();

    }

    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(COLS);
            y = new Random().nextInt(ROWS);
            food = new Consumable(x, y);
        } while (snake.snakeOnFood(food));
        food.show();
    }


    public void start() throws InterruptedException {

        snake.keyboardHandling();

        while (!isGameOver()) {
            snake.move();
            Thread.sleep(delay);
            if (snakeHasEaten(food)) {
                score += 100;
                updateScore();
                snake.grow();
                food.hide();
                createFood();
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
