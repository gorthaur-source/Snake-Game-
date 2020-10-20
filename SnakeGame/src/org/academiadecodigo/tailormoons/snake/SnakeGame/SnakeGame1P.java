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
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;

import java.util.Random;

public class SnakeGame1P implements SnakeGame, KeyHandler {


    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 20;


    //
    protected static int delay = 120;
    private Snake snake;
    private Consumable food;
    private int score;
    private Text scoreText = new Text(5, 2, "Score: " + score);
    private boolean[][] isCovered = new boolean[SnakeGridNormal.COLS][SnakeGridNormal.ROWS];


    public SnakeGame1P(SnakeGrid grid) {
        this.grid = grid;
        scoreText.setColor(Color.RED);
        scoreText.draw();

    }

    public void snakeInit() {
        snake = new Snake(1);
    }

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
        if (headX < 0 || headX >= SnakeGridNormal.COLS || headY < 0 || headY >= SnakeGridNormal.ROWS) {
            return;
        }
        if (isCovered[headX][headY]) {
            snake.setIsDead();
        }
    }

    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(SnakeGridNormal.COLS);
            y = new Random().nextInt(SnakeGridNormal.ROWS);
            food = new Consumable(x, y);

        } while (snake.snakeOnFood(food) || isCovered[x][y]);
        food.show();

    }

    public void init() {
        grid.initGrid();
        snakeInit();
        isCovered = grid.getIsCovered();
        createFood();
    }


    public void start() throws InterruptedException {

        while (!isGameOver()) {
                Thread.sleep((long) (delay/snake.getSpeed()));
                snake.move();
                checkCollision();
            if (snakeHasEaten(snake)) {
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

    public boolean snakeHasEaten(Snake snake) {
        SnakeParts head = snake.getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }

    public Snake getSnake() {
        return snake;
    }


    @Override
    public void pressed(KeyboardEvent e) {
        if(!snake.isDirectionChanged()) {
            snake.setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_LEFT: {
                    snake.changeDirection(Direction.LEFT);
                    break;
                }
                case KeyboardEvent.KEY_RIGHT: {
                    snake.changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_UP: {
                    snake.changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_DOWN: {
                    snake.changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
    }
}
