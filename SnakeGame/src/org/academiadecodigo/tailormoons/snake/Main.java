package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Menu.GameOver;
import org.academiadecodigo.tailormoons.snake.Menu.StartMenu;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridObstacles;

public class Main {
    public static void main(String[] args) {

        OurKeyboardHandler ourKeyboard = new OurKeyboardHandler();
        ourKeyboard.keyMapping();
        StartMenu startMenu = new StartMenu();
        GameOver over = new GameOver();

        boolean gameOver = false;
        boolean isStartEngaged = true;
        SnakeGame game = null;
        boolean play = false;

        while (true) {
            if (isStartEngaged) {
                over.setBackTo();
                play = true;
                ourKeyboard.setKeyHandling(startMenu);
                startMenu.init();
                isStartEngaged = false;
                gameOver = true;
                over.setPlayAgain(false);
            }
            if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 1 && play) {
                startMenu.setPlayerType(-1);
                game = new SnakeGame(new SnakeGridNormal());
                game.setPlayerNumber(1);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                isStartEngaged = false;
                gameOver = true;
                play = false;

            } else if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 2 && play) {
                game = new SnakeGame(new SnakeGridObstacles());
                game.setPlayerNumber(1);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                isStartEngaged = false;
                gameOver = true;
                play = false;
                startMenu.setPlayerType(-1);
            } else if (startMenu.getPlayerType() == 2 && startMenu.getGameType() == 2 && play) {
                game = new SnakeGame(new SnakeGridObstacles());
                game.setPlayerNumber(2);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                gameOver = true;
                play = false;
                startMenu.setPlayerType(-1);
            } else if (startMenu.getPlayerType() == 2 && startMenu.getGameType() == 1 && play) {
                game = new SnakeGame(new SnakeGridNormal());
                game.setPlayerNumber(2);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                gameOver = true;
                play = false;
                startMenu.setPlayerType(-1);
            }
            if (game != null) {
                if (gameOver && !play) {
                    ourKeyboard.setKeyHandling(over);
                    over.show();
                    gameOver = false;
                }
                if (over.backTo()) {
                    game.deactivateMusic();
                    isStartEngaged = true;
                    gameOver = false;
                    play = false;
                } else if (over.getPlay()) {
                    play = true;
                    game.deactivateMusic();
                    startMenu.navigationUpdate();
                }
            }
        }
    }
}





