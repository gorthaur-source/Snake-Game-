package org.academiadecodigo.tailormoons.snake.Menu;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridObstacles;

import java.security.Key;


public class StartMenu implements KeyHandler {


    private Picture snakeLogo = new Picture(375, 150, "assets/Menu/snake1.png");
    private Picture startButtonSelected = new Picture(540, 500, "assets/Menu/PLAY Selected-300x100.png");
    private Picture startButton = new Picture(540, 500, "assets/Menu/PLAY unselected-300x100.png");
    private Picture exitButtonSelected = new Picture(540, 600, "assets/Menu/EXIT Selected-300x100.png");
    private Picture exitButton = new Picture(540, 600, "assets/Menu/EXIT unselected-300x100.png");
    private Picture picture = new Picture(0, 0, Menu.BACKGROUND_IMAGE);
    private Picture noObstacles = new Picture(540, 500, "assets/Menu/original1-300x100.png");
    private Picture obstacles = new Picture(540, 600, "assets/Menu/obstacles-300x100.png");
    private Picture obstaclesSelected = new Picture(540, 600, "assets/Menu/obstaclesEdge-300x100.png");
    private Picture noObstaclesSelected = new Picture(540, 500, "assets/Menu/originalEdge-300x100.png");
    private Picture player1ButtonSelected = new Picture(540, 500, "assets/Menu/1PlayerEdge-300x100.png");
    private Picture player1Button = new Picture(540, 500, "assets/Menu/1Player-300x100.png");
    private Picture player2ButtonSelected = new Picture(540, 600, "assets/Menu/2PlayersEdge-300x100.png");
    private Picture player2Button = new Picture(540, 600, "assets/Menu/TWOPlayers unselected-300x100.png");

    private boolean isP1;
    private boolean isSpacePress;
    private boolean isP2;
    private boolean isStartButtonSelected = true;
    private boolean isPlayer1Selected;
    private boolean isPlayer2Selected;
    private boolean isExitButtonSelected;
    private boolean isObstaclesSelected;
    private boolean isNoobSelected;
    private OurKeyboardHandler handler;
    private volatile int playerType;
    private volatile int gameType;
    int randomMusic = (int) (Math.random() * MUSICS.length);
    private Sound music = new Sound(MUSICS[randomMusic]);

    private static final String[] MUSICS = {
            "/assets/Sounds/Music/1.wav",
            "/assets/Sounds/Music/2.wav",
            "/assets/Sounds/Music/3.wav",
            "/assets/Sounds/Music/4.wav"
    };

    public StartMenu() {


    }

    public void init() {

        isSpacePress = false;
        gameType = -1;
        isStartButtonSelected = true;
        music.play(true);
        picture.draw();
        snakeLogo.draw();
        startButtonSelected.draw();
        exitButton.draw();


    }


    public void navigationUpdate() {

        if (isSpacePress) {
            if (isP1) playerType = 1;
            else if (isP2) playerType = 2;
            if (isObstaclesSelected) gameType = 2;
            else if (isNoobSelected) gameType = 1;
        }
    }


    @Override
    public void pressed(KeyboardEvent e) {

        switch (e.getKey()) {
            case KeyboardEvent.KEY_DOWN:
                if (isStartButtonSelected) {
                    startButtonSelected.delete();
                    startButton.draw();
                    exitButtonSelected.draw();
                    isStartButtonSelected = false;
                    isExitButtonSelected = true;
                    break;
                } else if (isPlayer1Selected) {
                    player1ButtonSelected.delete();
                    player1Button.draw();
                    player2ButtonSelected.draw();
                    isPlayer1Selected = false;
                    isPlayer2Selected = true;
                    break;
                } else if (isNoobSelected) {
                    isNoobSelected = false;
                    noObstaclesSelected.delete();
                    noObstacles.draw();
                    obstaclesSelected.draw();
                    isObstaclesSelected = true;
                    break;
                }
                else if (isExitButtonSelected) break;
                else if (isPlayer2Selected) break;
                else if (isObstaclesSelected) break;
            case KeyboardEvent.KEY_UP:
                if (isExitButtonSelected) {
                    isExitButtonSelected = false;
                    exitButtonSelected.delete();
                    startButtonSelected.draw();
                    exitButton.draw();
                    isStartButtonSelected = true;
                    break;

                } else if (isPlayer2Selected) {
                    isPlayer2Selected = false;
                    player2Button.draw();
                    player2ButtonSelected.delete();
                    player1ButtonSelected.draw();
                    isPlayer1Selected = true;
                    break;
                } else if (isObstaclesSelected) {
                    isObstaclesSelected = false;
                    obstacles.draw();
                    obstaclesSelected.delete();
                    noObstaclesSelected.draw();
                    isNoobSelected = true;
                    break;
                }
                else if (isStartButtonSelected) break;
                else if (isPlayer1Selected) break;
                else if (isNoobSelected) break;


            case KeyboardEvent.KEY_SPACE:
                if (isStartButtonSelected) {
                    unDrawAll();
                    unSelectAll();
                    isStartButtonSelected = false;
                    isPlayer1Selected = true;
                    player1ButtonSelected.draw();
                    player2Button.draw();
                    break;
                } else if (isExitButtonSelected) {
                    System.exit(1);
                    break;
                } else if (isPlayer1Selected) {
                    unDrawAll();
                    unSelectAll();
                    isP1 = true;
                    isP2 = false;
                    playerType = 1;
                    obstacles.draw();
                    noObstaclesSelected.draw();
                    isNoobSelected = true;
                    break;
                } else if (isPlayer2Selected) {
                    unDrawAll();
                    unSelectAll();
                    isP1 = false;
                    isP2 = true;
                    playerType = 2;
                    obstacles.draw();
                    noObstaclesSelected.draw();
                    isNoobSelected = true;
                    break;
                } else if (isNoobSelected) {
                    unDrawAll();
                    unSelectAll();
                    picture.delete();
                    snakeLogo.delete();
                    isSpacePress = true;
                    isObstaclesSelected = false;
                    music.close();
                    gameType = 1;
                    isNoobSelected = false;
                    break;
                }
                if (isObstaclesSelected) {
                    unDrawAll();
                    unSelectAll();
                    picture.delete();
                    snakeLogo.delete();
                    isNoobSelected = false;
                    isSpacePress = true;
                    music.close();
                    gameType = 2;
                    isObstaclesSelected = false;
                    break;
                }

        }
    }


    public void unDrawAll() {
        startButtonSelected.delete();
        startButton.delete();
        exitButtonSelected.delete();
        exitButton.delete();
        noObstacles.delete();
        obstacles.delete();
        obstaclesSelected.delete();
        noObstaclesSelected.delete();
        player1ButtonSelected.delete();
        player1Button.delete();
        player2ButtonSelected.delete();
        player2Button.delete();
    }

    public void unSelectAll() {
        isStartButtonSelected = false;
        isPlayer1Selected = false;
        isPlayer2Selected = false;
        isExitButtonSelected = false;
        isObstaclesSelected = false;
        isNoobSelected = false;
        isSpacePress = false;
    }

    public void setHandler(OurKeyboardHandler handler) {
        this.handler = handler;
    }

    public int getPlayerType() {
        return playerType;
    }

    public int getGameType() {
        return gameType;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }
}
