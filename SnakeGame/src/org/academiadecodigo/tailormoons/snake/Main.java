package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Menu.GameOver;
import org.academiadecodigo.tailormoons.snake.Menu.StartMenu;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame1P;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridObstacles;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        OurKeyboardHandler ourKeyboard = new OurKeyboardHandler();
        ourKeyboard.keyMapping();
        StartMenu startMenu = new StartMenu(ourKeyboard);
        ourKeyboard.setKeyHandling(startMenu);
        startMenu.init();

        boolean gameOver = false;

        while(true) {
            System.out.println("Waiting for game selection"); //?
            if(startMenu.getPlayerType() == 1 && startMenu.getGameType()== 1) break;
            else if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 2) break;
            else if (startMenu.getPlayerType() == 2 && startMenu.getGameType() == 2) break;
            else if (startMenu.getPlayerType() == 2 && startMenu.getGameType() == 1) break;


        }
            if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 1) {
                SnakeGame1P game = new SnakeGame1P(new SnakeGridNormal());
                game.setPlayerNumber(1);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            } else if (startMenu.getPlayerType() == 1 && startMenu.getGameType() == 2) {
                SnakeGame1P game = new SnakeGame1P(new SnakeGridObstacles());
                game.setPlayerNumber(1);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            } else if (startMenu.getGameType() == 1 && startMenu.getPlayerType() == 2)  {
                SnakeGame1P game = new SnakeGame1P((new SnakeGridNormal()));
                game.setPlayerNumber(2);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            } else if (startMenu.getGameType() == 2 && startMenu.getPlayerType() == 2) {
                SnakeGame1P game = new SnakeGame1P(new SnakeGridObstacles());
                game.setPlayerNumber(2);
                ourKeyboard.setKeyHandling(game);
                game.init();
                game.start();
                if(game.isGameOver()) gameOver = true;
            }


            GameOver start = new GameOver(ourKeyboard);
            ourKeyboard.setKeyHandling(start);
            start.show();
    }
}




