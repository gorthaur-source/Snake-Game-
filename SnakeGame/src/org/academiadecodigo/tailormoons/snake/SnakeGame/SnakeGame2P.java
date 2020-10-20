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

import java.util.Random;

public class SnakeGame2P implements SnakeGame, KeyHandler {

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
    private Snake playerOne;
    private Snake playerTwo;
    private Consumable food;
    private boolean[][] isCovered = new boolean[COLS][ROWS];

    public SnakeGame2P(SnakeGrid grid) {
        this.grid = grid;

    }

    public void init() {
        grid.initGrid();
        isCovered = grid.getIsCovered();
        scoreTextOne.setColor(Color.RED);
        scoreTextTwo.setColor(Color.RED);
        scoreTextOne.draw();
        scoreTextTwo.draw();
        createFood();

    }

    public void start() throws InterruptedException {

        while (!isGameOver()) {

            Thread.sleep((long) (delay/playerOne.getSpeed()));
            playerOne.move();
            playerOne.move();
            if (snakeHasEaten(playerOne)) {
                scoreOne += 100;
                updateScore();
                playerOne.grow();
                food.hide();
                createFood();
            }
            else if (snakeHasEaten(playerTwo)) {
                scoreTwo += 100;
                updateScoreTwo();
                playerTwo.grow();
                food.hide();
                createFood();
            }
        }
    }

    public boolean isGameOver() {
        return playerOne.isDead() || playerTwo.isDead();
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
        if(!playerOne.isDirectionChanged()) {
            playerOne.setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_LEFT: {
                    playerOne.changeDirection(Direction.LEFT);
                    break;
                }
                case KeyboardEvent.KEY_RIGHT: {
                    playerOne.changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_UP: {
                    playerOne.changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_DOWN: {
                    playerOne.changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
        if(!playerTwo.isDirectionChanged()) {
            playerTwo.setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_A: {
                    playerOne.changeDirection(Direction.LEFT);
                    break;
                }
                case KeyboardEvent.KEY_D: {
                    playerTwo.changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_W: {
                    playerTwo.changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_S: {
                    playerTwo.changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
    }
    public void checkCollisionSnake(Snake snake) {
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
    }

    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(COLS);
            y = new Random().nextInt(ROWS);
            food = new Consumable(x, y);
        } while (playerOne.snakeOnFood(food) || playerTwo.snakeOnFood(food) || isCovered[x][y]);
        food.show();
    }
    public void checkCollision(){
        checkCollisionSnake(playerOne);
        checkCollisionSnake(playerTwo);
    }
    public boolean snakeHasEaten(Snake snake) {
        SnakeParts head = playerOne.getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }




}
