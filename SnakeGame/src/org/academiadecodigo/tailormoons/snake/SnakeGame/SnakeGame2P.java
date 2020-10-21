package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;

import javax.sound.sampled.Clip;
import java.util.Random;

public class SnakeGame2P implements SnakeGame, KeyHandler {

    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 23;
    //
    protected static int delay = 120;
    private int scoreOne;
    private int scoreTwo;
    private Text scoreTextOne = new Text(5, 2, "Player one score: " + scoreOne);
    private Text scoreTextTwo = new Text((SnakeGridNormal.COLS - 8) * CELL_SIZE, 2, "Player two score: " + scoreTwo);
    private Snake playerOne;
    private Snake playerTwo;
    private Snake winner;
    private Consumable food;
    private boolean[][] isCovered = new boolean[SnakeGridNormal.COLS][SnakeGridNormal.ROWS];
    private Sound music;
    private String filePathMusic;

    public SnakeGame2P(SnakeGrid grid) {
        this.grid = grid;
        int randomMusic = (int) (Math.random() * 4);
        switch (randomMusic) {
            case 0:
                filePathMusic = "/assets/Sounds/Music/1.wav";
                break;
            case 1:
                filePathMusic = "/assets/Sounds/Music/2.wav";
                break;
            case 2:
                filePathMusic = "/assets/Sounds/Music/3.wav";
                break;
            case 3:
                filePathMusic = "/assets/Sounds/Music/4.wav";
                break;
        }

        System.out.println(filePathMusic);
        music = new Sound(filePathMusic);
        music.play(true);


    }

    public void init() {
        grid.initGrid();
        isCovered = grid.getIsCovered();
        snakeInit();
        scoreTextOne.setColor(Color.RED);
        scoreTextTwo.setColor(Color.RED);
        scoreTextOne.draw();
        scoreTextTwo.draw();
        createFood();

    }

    public void snakeInit() {
        playerOne = new Snake(2);
        playerTwo = new Snake(3);
    }

    public void start() throws InterruptedException {

        while (!isGameOver()) {

            Thread.sleep((long) (delay / playerOne.getSpeed()));
            playerOne.move();
            playerTwo.move();
            checkCollision();
            if (snakeHasEaten(playerOne)) {
                scoreOne += 100;
                updateScore();
                playerOne.grow();
                food.hide();
                createFood();
            }if (snakeHasEaten(playerTwo)) {
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
        scoreTextTwo = new Text((SnakeGridNormal.COLS - 8) * CELL_SIZE, 2, "Player two score: " + scoreTwo);
        scoreTextTwo.setColor(Color.RED);
        scoreTextTwo.draw();

    }

    @Override
    public void pressed(KeyboardEvent e) {
        if (!playerOne.isDirectionChanged()) {
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
        if (!playerTwo.isDirectionChanged()) {
            playerTwo.setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_A: {
                    playerTwo.changeDirection(Direction.LEFT);
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


    public void checkCollisionsBetweenSnakes() {

        for (int i = 1; i < playerOne.getLength(); i++) {
            if (playerTwo.getHead().getX() == playerOne.getSnakeBody().get(i).getX() && playerTwo.getHead().getY() == playerOne.getSnakeBody().get(i).getY()) {
                playerTwo.setIsDead();
            }
        }
        for (int i = 1; i < playerTwo.getLength(); i++) {
            if (playerOne.getHead().getX() == playerTwo.getSnakeBody().get(i).getX() && playerOne.getHead().getY() == playerTwo.getSnakeBody().get(i).getY()) {
                playerTwo.setIsDead();
            }
        }
        if (playerOne.getHead().getX() == playerTwo.getHead().getX() && playerOne.getHead().getY() == playerTwo.getHead().getY()) {
            playerTwo.setIsDead();
            playerOne.setIsDead();
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
        } while (playerOne.snakeOnFood(food) || playerTwo.snakeOnFood(food) || isCovered[x][y]);
        food.show();
    }

    public void checkCollision() {
        checkCollisionSnake(playerOne);
        checkCollisionSnake(playerTwo);
        checkCollisionsBetweenSnakes();
    }

    public boolean snakeHasEaten(Snake snake) {
        SnakeParts head = snake.getSnakeBody().getFirst();
        return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
    }


}
