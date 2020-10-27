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
        ourKeyboard.setKeyHandling(startMenu);
        startMenu.setHandler(ourKeyboard);
        startMenu.init();
        boolean gameOver = false;

        while(true) {
            startMenu.navigationUpdate();
            if(startMenu.getPlayerType() == 1 && startMenu.getGameType()== 1) break;
            else if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 2) break;
            else if (startMenu.getPlayerType() == 2 && startMenu.getGameType() == 2) break;
            else if (startMenu.getPlayerType() == 2 && startMenu.getGameType() == 1) break;


        }
            if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 1) {
                System.out.println("check2");
                SnakeGame game = new SnakeGame(new SnakeGridNormal());
                game.setPlayerNumber(1);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            } else if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 2) {
                SnakeGame game = new SnakeGame(new SnakeGridObstacles());
                game.setPlayerNumber(1);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            } else if (startMenu.getGameType() == 1 && startMenu.getPlayerType() == 2)  {
                SnakeGame game = new SnakeGame((new SnakeGridNormal()));
                game.setPlayerNumber(2);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            } else if (startMenu.getGameType() == 2 && startMenu.getPlayerType() == 2) {
                SnakeGame game = new SnakeGame(new SnakeGridObstacles());
                game.setPlayerNumber(2);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            }


            GameOver over = new GameOver(ourKeyboard);
            ourKeyboard.setKeyHandling(over);
            over.show();
    }
}




