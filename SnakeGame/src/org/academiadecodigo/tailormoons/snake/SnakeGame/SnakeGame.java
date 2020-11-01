package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.tailormoons.snake.BasicTimer;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;

import org.academiadecodigo.tailormoons.snake.Menu.GameOver;
import org.academiadecodigo.tailormoons.snake.Menu.StartMenu;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Node.SnakeParts;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;

import java.util.Random;

public class SnakeGame implements KeyHandler {


    // grid stuff
    private SnakeGrid grid;
    public static final int PADDING = 0;
    public static final int CELL_SIZE = 23;


    //
    private BasicTimer delays = new BasicTimer(30);
    protected static int delay = 60;
    private Consumable food;
    private boolean[][] isCovered = new boolean[SnakeGridNormal.COLS][SnakeGridNormal.ROWS];
    //  private Sound eat = new Sound("/assets/Sounds/eat.wav");
    //  private Sound die = new Sound("/assets/Sounds/die.wav");
    private Sound music;
    private int playerNumber;
    private Snake[] snake;
    private Text[] scoreText;
    private int[] score;
    private Snake winner;
    private boolean gameOver = false;
    private static final String[] MUSICS = {
            "/assets/Sounds/Music/1.wav",
            "/assets/Sounds/Music/2.wav",
            "/assets/Sounds/Music/3.wav",
            "/assets/Sounds/Music/4.wav"
    };

    public SnakeGame(SnakeGrid grid) {
        this.grid = grid;

        int randomMusic = (int) (Math.random() * MUSICS.length);

        music = new Sound(MUSICS[randomMusic]);
        music.play(true);

    }

    public void snakeInit() {
        snake = new Snake[playerNumber];
        if (playerNumber == 1) {
            snake[0] = new Snake(1);
        } else if (playerNumber == 2) {
            snake[0] = new Snake(2);
            snake[1] = new Snake(3);

        }
    }

    public void checkCollision() {
        for (int i = 0; i < playerNumber; i++) {
            int headX = snake[i].getHead().getX();
            int headY = snake[i].getHead().getY();

            for (int j = 1; j < snake[i].getLength(); j++) {
                if (snake[i].getSnakeBody().get(j).getY() == headY && snake[i].getSnakeBody().get(j).getX() == headX) {
                    snake[i].setIsDead();
                    break;
                }
            }
            switch (snake[i].getHead().getDirection()) {
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
                snake[i].setIsDead();
            }
        }
    }

    public void createFood() {
        int x, y;
        do {
            x = new Random().nextInt(SnakeGridNormal.COLS);
            y = new Random().nextInt(SnakeGridNormal.ROWS);
            food = new Consumable(x, y);

        } while (snakeOnFoodOrCovered(food, x, y));
        food.show();

    }

    public boolean snakeOnFoodOrCovered(Consumable food, int x, int y) {

        for (int j = 0; j < playerNumber; j++) {
            for (int i = 0; i < snake[j].getLength(); i++) {
                if (snake[j].getSnakeBody().get(i).getX() == food.getX() && snake[j].getSnakeBody().get(i).getY() == food.getY() || isCovered[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void init() {
        grid.initGrid();
        snakeInit();
        score = new int[playerNumber];
        scoreText = new Text[playerNumber];
        scoreBoardCreation();
        isCovered = grid.getIsCovered();
        createFood();
        music.play(true);
    }


    public void start() {

        if(playerNumber == 2) delays.changeFPS(40);

        while (!gameOver) {
            for (int i = 0; i < playerNumber; i++) {
                delays.sync();
               checkCollision();
               checkCollisionsBetweenSnakes();
               isGameOver();
                snake[i].move();
                if (snakeHasEaten(snake[i])) {
                    score[i] += 100;
                    updateScore(i);
                    snake[i].grow();
                    food.hide();
                    createFood();
                }
            }
        }
        }



    public boolean isGameOver() {
        for (int i = 0; i < playerNumber; i++) {
            if (snake[i].isDead()) {
                gameOver = true;//   die.play(true);
                return true;
            }
        }
        return false;

    }

    public void checkCollisionsBetweenSnakes() {

        if (playerNumber == 1) return;


        for (int j = 0; j < playerNumber - 1; j++) {
            for (int i = 1; i < snake[j + 1].getLength(); i++) {
                if (snake[j].getHead().getX() == snake[j + 1].getHead().getX() && snake[j].getHead().getY() == snake[j + 1].getHead().getY()) {
                    snake[j].setIsDead();
                    snake[j + 1].setIsDead();
                    System.out.println(snake[j + 1].getSnakeBody().get(i).getX());
                    break;
                } else if ((snake[j].getHead().getX() == snake[j + 1].getSnakeBody().get(i).getX() && snake[j].getHead().getY() == snake[j + 1].getSnakeBody().get(i).getY())) {
                    snake[j].setIsDead();
                    winner = snake[j+1];
                    break;
                }
            }
            for (int i = 1; i < snake[j].getLength(); i++) {
                if (snake[j + 1].getHead().getX() == snake[j].getSnakeBody().get(i).getX() && snake[j + 1].getHead().getY() == snake[j].getSnakeBody().get(i).getY()) {
                    snake[j + 1].setIsDead();
                    winner = snake[j];
                    break;
                }
            }
        }
    }


        public void scoreBoardCreation () {

            for (int i = 0; i < score.length; i++) {
                score[i] = 0;
            }

            if (playerNumber == 1) {
                scoreText[0] = new Text(5, 2, "Score: " + score[0]);
                scoreText[0].setColor(Color.RED);
                scoreText[0].draw();
            }
            else if (playerNumber == 2) {
                scoreText[0] = new Text(5, 2, "Score: " + score[0]);
                scoreText[0].setColor(Color.RED);
                scoreText[0].draw();
                scoreText[1] = new Text((SnakeGridNormal.COLS - 8) * CELL_SIZE, 2, "Player two score: " + score[1]);
                scoreText[1].setColor(Color.RED);
                scoreText[1].draw();

            }
        }

        public void updateScore ( int index){
            scoreText[index].delete();
            if (index == 0) {
                scoreText[index] = new Text(5, 2, "Score: " + score[0]);

            } else if (index == 1) {
                scoreText[index] = new Text((SnakeGridNormal.COLS - 8) * CELL_SIZE, 2, "Player two score: " + score[1]);

            }

            scoreText[index].setColor(Color.RED);
            scoreText[index].draw();

        }

        public boolean snakeHasEaten (Snake snake){
            SnakeParts head = snake.getSnakeBody().getFirst();
            return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
        }

        @Override
        public void pressed (KeyboardEvent e){

            if (!snake[0].isDirectionChanged()) {
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
             if (playerNumber == 2) {
                if (!snake[1].isDirectionChanged()) {
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
        }

    public void deactivateMusic() {
        music.close();
    }

        public void setPlayerNumber ( int numberOfPlayers){
            playerNumber = numberOfPlayers;
        }
    }
