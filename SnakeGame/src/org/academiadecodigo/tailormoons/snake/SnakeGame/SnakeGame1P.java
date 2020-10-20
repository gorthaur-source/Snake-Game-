package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;

import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;

import java.util.Random;

public class SnakeGame1P implements SnakeGame, KeyHandler {


    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;
    public static final int ROWS = 20;
    public static final int COLS = 40;

    //
    protected static int delay = 120;
    private Snake[] snake = new Snake[1];
    private Consumable food;
    private int score;
    private Text scoreText = new Text(5, 2, "Score: " + score);



    public SnakeGame1P(SnakeGrid grid) {
        this.grid = grid;
        scoreText.setColor(Color.RED);
        scoreText.draw();

    }



    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(COLS);
            y = new Random().nextInt(ROWS);
            food = new Consumable(x, y);
        } while (snake[0].snakeOnFood(food));
        food.show();
    }

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

    public void snakeInit() {
        snake[0] = new Snake(grid);
        for (int i = 0; i < snake[0].getLength(); i++) {
            snake[0].getSnakeBody().add(new SnakeParts(SnakeGame1P.COLS / 2, SnakeGame1P.ROWS / 2 + i));
        }
    }

    public void init() {
        grid.initGrid(ROWS, COLS);
        snakeInit();
        grid.setSnake(snake);
        grid.createFood();
    }


    public void start() throws InterruptedException {

        while (!isGameOver()) {
                Thread.sleep((long) (delay/snake[0].getSpeed()));
                snake[0].move();
            if (snakeHasEaten(grid.getFood())) {
                score += 100;
                updateScore();
                snake[0].grow();
                grid.getFood().hide();
                grid.createFood();
            }
        }

    }

    public boolean isGameOver() {
        if (snake[0].isDead()) {
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
        SnakeParts head = snake[0].getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }

    public Snake getSnake() {
        return snake[0];
    }

    public SnakeGrid getGrid() {
        return grid;
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
    }
}
