package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;

public class SnakeGame2P implements SnakeGame, KeyHandler {

    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static int ROWS = 20;
    public static int COLS = 40;
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



    public void start() throws InterruptedException {

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

    @Override
    public void pressed(KeyboardEvent e) {
        if(!snake[0].isDirectionChanged()) {
            snake[0].setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_LEFT: {
                    snake[0].changeDirection(Direction.LEFT);
                    break;
                }
                case KeyboardEvent.KEY_RIGHT: {
                    snake[0].changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_UP: {
                    snake[0].changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_DOWN: {
                    snake[0].changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
        if(!snake[1].isDirectionChanged()) {
            snake[1].setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_A: {
                    snake[1].changeDirection(Direction.LEFT);
                    break;
                }
                case KeyboardEvent.KEY_D: {
                    snake[1].changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_W: {
                    snake[1].changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_S: {
                    snake[1].changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
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
