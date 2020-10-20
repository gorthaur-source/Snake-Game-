package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyboardListener;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyboardListenerSnake;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyboardListenerSnakeTwo;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;

public class SnakeGame2P implements SnakeGame {

    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 40;
    public static final int COLS = 60;
    //
    protected static int delay = 120;
    private int scoreOne;
    private int scoreTwo;
    private Text scoreTextOne = new Text(5, 2, "Player one score: " + scoreOne);
    private Text scoreTextTwo = new Text((COLS-8) * CELL_SIZE, 2, "Player two score: "+ scoreTwo);
    private Snake[] snake = new Snake[2];

    public SnakeGame2P(SnakeGrid grid) {
        this.grid = grid;
    }

    public void init() {
        grid.initGrid(ROWS, COLS);
        snakeInit();
        grid.setSnake(snake);
        grid.createFood();
        scoreTextOne.setColor(Color.RED);
        scoreTextTwo.setColor(Color.RED);
        scoreTextOne.draw();
        scoreTextTwo.draw();



    }

    public void keyboardSnakeInit() {


        KeyboardListener snakeTwoListener = new KeyboardListenerSnakeTwo(snake[0]);
        snake[0].setKeyboardListener(snakeTwoListener);
        snake[0].setKeyboard(new Keyboard((KeyboardHandler) snakeTwoListener));
        snake[0].getSnakeListener().keyboardHandling();


        KeyboardListener snakeOneListener = new KeyboardListenerSnake(snake[1]);
        snake[1].setKeyboardListener(snakeOneListener);
        snake[1].setKeyboard(new Keyboard((KeyboardHandler) snakeOneListener));
        snake[1].getSnakeListener().keyboardHandling();



    }


    public void start() throws InterruptedException {

        keyboardSnakeInit();

        while (!isGameOver()) {

            Thread.sleep((long) (delay/snake[0].getSpeed()));
            snake[0].move();
            snake[1].move();
            if (snakeHasEaten(grid.getFood())) {
                scoreOne += 100;
                updateScore();
                snake[0].grow();
                grid.getFood().hide();
                grid.createFood();
            }
            else if (snakeTwoHasEaten(grid.getFood())) {
                scoreTwo += 100;
                updateScoreTwo();
                snake[1].grow();
                grid.getFood().hide();
                grid.createFood();
            }
        }
    }
    public void snakeInit() {

        for(int i = 0; i < snake.length; i++) {
            snake[i] = new Snake(grid);
        }

        for (int i = 0; i < snake[0].getLength(); i++) {
            snake[0].getSnakeBody().add(new SnakeParts(COLS / 8, ROWS / 2 + i));
            snake[1].getSnakeBody().add(new SnakeParts((COLS/8 * 7), ROWS/2 + i));
        }
    }

    public boolean isGameOver() {
        for (Snake value : snake) {
            return value.isDead();
        }
        return false;
    }

    public void updateScore() {
        scoreTextOne.delete();
        scoreTextOne = new Text(5, 2, "Score: " + scoreOne);
        scoreTextOne.setColor(Color.RED);
        scoreTextOne.draw();

    }

    public void updateScoreTwo() {
        scoreTextTwo.delete();
        scoreTextTwo = new Text(5, 2, "Score: " + scoreTwo);
        scoreTextTwo.setColor(Color.RED);
        scoreTextTwo.draw();

    }

    public boolean snakeHasEaten(Consumable food) {
        SnakeParts head = snake[0].getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }

    @Override
    public Snake getSnake() {
        return null;
    }

    @Override
    public SnakeGrid getGrid() {
        return grid;
    }

    public boolean snakeTwoHasEaten(Consumable food) {
        SnakeParts head = snake[1].getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }


}
