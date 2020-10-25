package org.academiadecodigo.tailormoons.snake.Menu;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame1P;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridObstacles;


public class StartMenu implements KeyHandler {


    private Picture snakeLogo = new Picture(375, 150, "assets/Menu/snake1.png");
    private Picture startButtonSelected = new Picture(540, 500, "assets/Menu/PLAY Selected-300x100.png");
    private Picture startButton = new Picture(540, 500, "assets/Menu/PLAY unselected-300x100.png");
    private Picture exitButtonSelected = new Picture(540, 600, "assets/Menu/EXIT Selected-300x100.png");
    private Picture exitButton = new Picture(540, 600, "assets/Menu/EXIT unselected-300x100.png");
    private Picture scoresButtonSelected = new Picture(540, 700, "assets/Menu/SCORES selected-300x100.png");
    private Picture scoresButton = new Picture(540, 700, "assets/Menu/SCORES unselected-300x100.png");
    private Picture picture = new Picture(0, 0, Menu.BACKGROUND_IMAGE);
    private Picture noObstacles = new Picture(540, 500, "assets/Menu/original1-300x100.png");
    private Picture obstacles = new Picture(540, 600, "assets/Menu/obstacles-300x100.png");
    private Picture obstaclesSelected = new Picture(540, 600, "assets/Menu/obstaclesEdge-300x100.png");
    private Picture noObstaclesSelected = new Picture(540, 500, "assets/Menu/originalEdge-300x100.png");


    private Picture player1ButtonSelected = new Picture(540, 500, "assets/Menu/1PlayerEdge-300x100.png");
    private Picture player1Button = new Picture(540, 500, "assets/Menu/1Player-300x100.png");
    private Picture player2ButtonSelected = new Picture(540, 600, "assets/Menu/2PlayersEdge-300x100.png");
    private Picture player2Button = new Picture(540, 600, "assets/Menu/TWOPlayers unselected-300x100.png");

    private OurKeyboardHandler handler;

    private boolean isStartButtonSelected = true;
    private boolean isPlayer1Selected;
    private boolean isPlayer2Selected;
    private boolean isScoreButtonSelected;
    private boolean isExitButtonSelected;
    private boolean isObstaclesSelected;
    private boolean isNoobSelected;
    private SnakeGame game;
    public int playerType;
    private Sound music;
    private String filePathMusic;
    public int gameType;
    private int selection;
    Runnable games;

    public StartMenu(OurKeyboardHandler handler) {
        this.handler = handler;

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
        music = new Sound(filePathMusic);

    }

    public void init() throws InterruptedException {


        music.play(true);
        picture.draw();
        snakeLogo.draw();
        //start button selected - Default
        startButtonSelected.draw();
        //exit button
        exitButton.draw();
        //scores button
        scoresButton.draw();

        //instructions
 /*       Text text = new Text(630, 510, "Press Space Key to select");
        text.setColor(Color.RED);
        text.grow(85, 15);
        text.draw(); */

    }


    public int getSelection() {
        return selection;
    }

    @Override
    public void pressed(KeyboardEvent e) throws InterruptedException {
        switch (e.getKey()) {
            case KeyboardEvent.KEY_DOWN:
                if (isStartButtonSelected) {
                    startButtonSelected.delete();
                    startButton.draw();
                    exitButtonSelected.draw();
                    isStartButtonSelected = false;
                    isExitButtonSelected = true;
                    break;
                } else if (isExitButtonSelected) {
                    //   exitButtonSelected.delete();
                    //  scoresButtonSelected.draw();
                    //   isExitButtonSelected = false;
                    break;
                } else if (isPlayer1Selected) {
                    player1ButtonSelected.delete();
                    player1Button.draw();
                    player2ButtonSelected.draw();
                    player2Button.delete();
                    isPlayer1Selected = false;
                    isPlayer2Selected = true;
                    break;
                } else if (isNoobSelected) {
                    isNoobSelected = false;
                    noObstaclesSelected.delete();
                    noObstacles.draw();
                    obstaclesSelected.draw();
                    obstacles.delete();
                    isObstaclesSelected = true;
                    break;
                }
            case KeyboardEvent.KEY_UP:
                if (isScoreButtonSelected) {
                    scoresButtonSelected.delete();
                    exitButtonSelected.draw();
                    startButtonSelected.draw();
                    isScoreButtonSelected = false;
                    isStartButtonSelected = true;
                    break;

                } else if (isExitButtonSelected) {
                    startButton.delete();
                    exitButtonSelected.delete();
                    startButtonSelected.draw();
                    isExitButtonSelected = false;
                    isStartButtonSelected = true;
                    break;

                } else if (isPlayer2Selected) {
                    player2Button.draw();
                    player2ButtonSelected.delete();
                    player1ButtonSelected.draw();
                    isPlayer1Selected = true;
                    isPlayer2Selected = false;
                    break;
                } else if (isObstaclesSelected) {
                    isObstaclesSelected = false;
                    obstaclesSelected.delete();
                    obstacles.draw();
                    noObstacles.delete();
                    noObstaclesSelected.draw();
                    isNoobSelected = true;
                    break;
                } else if (isStartButtonSelected) {
                    break;
                } else if (isPlayer1Selected) {
                    break;
                } else if (isNoobSelected) {
                    break;
                }

            case KeyboardEvent.KEY_SPACE:
                if (isStartButtonSelected) {
                    isStartButtonSelected = false;
                    isPlayer1Selected = true;
                    scoresButton.delete();
                    exitButton.delete();
                    startButtonSelected.delete();
                    startButton.delete();
                    player1ButtonSelected.draw();
                    player2Button.draw();
                    break;
                } else if (isExitButtonSelected) {
                    System.out.println("Bye bye! Maybe we finish it next....");
                    System.exit(1);
                    break;
                } else if (isPlayer1Selected) {
                    isPlayer1Selected = false;
                    player1ButtonSelected.delete();
                    player2Button.delete();
                    playerType = 1;
                    obstacles.draw();
                    noObstaclesSelected.draw();
                    isNoobSelected = true;
                    break;
                } else if (isPlayer2Selected) {
                    isPlayer2Selected = false;
                    playerType = 2;
                    player1ButtonSelected.delete();
                    player2Button.delete();
                    obstacles.draw();
                    noObstaclesSelected.draw();
                    isNoobSelected = true;
                    break;
                } else if (isNoobSelected) {
                    if (playerType == 1) {
                        obstaclesSelected.delete();
                        noObstaclesSelected.delete();
                        picture.delete();
                        snakeLogo.delete();
                        music.close();
                        gameType = 1;
                        break;
                    } else if (playerType == 2) {
                        obstaclesSelected.delete();
                        noObstaclesSelected.delete();
                        picture.delete();
                        snakeLogo.delete();
                        music.close();
                        gameType = 1;
                        break;
                    }
                } else if (isObstaclesSelected) {
                    if (playerType == 1) {
                        obstaclesSelected.delete();
                        noObstacles.delete();
                        picture.delete();
                        snakeLogo.delete();
                        music.close();
                        gameType = 2;
                        break;
                    } else if (playerType == 2) {
                        obstaclesSelected.delete();
                        noObstacles.delete();
                        picture.delete();
                        snakeLogo.delete();
                        music.close();
                        gameType = 2;
                        break;
                    }
                }
        }
    }

}

