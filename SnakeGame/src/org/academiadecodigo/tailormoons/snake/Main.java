package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
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

        while(true) {
            System.out.println("Waiting for game selection");
            if(startMenu.playerType == 1 && startMenu.gameType == 1) break;
            else if (startMenu.playerType == 1 && startMenu.gameType == 2) break;
            else if (startMenu.playerType == 2 && startMenu.gameType == 2) break;
            else if (startMenu.playerType == 2 && startMenu.gameType == 1) break;


        }
        if(startMenu.playerType == 1 && startMenu.gameType == 1) {
            SnakeGame1P game = new SnakeGame1P(new SnakeGridNormal());
            game.setPlayerNumber(1);
            ourKeyboard.setKeyHandling(game);
            game.init();
            game.start();
        }
       else if (startMenu.playerType == 1) {
           SnakeGame1P game = new SnakeGame1P(new SnakeGridObstacles());
           game.setPlayerNumber(1);
           ourKeyboard.setKeyHandling(game);
           game.init();
           game.start();
       }
       else if (startMenu.gameType == 1) {
           SnakeGame1P game = new SnakeGame1P((new SnakeGridNormal()));
           game.setPlayerNumber(2);
           ourKeyboard.setKeyHandling(game);
           game.init();
           game.start();
       }
       else if (startMenu.gameType == 2) {
           SnakeGame1P game = new SnakeGame1P(new SnakeGridObstacles());
           game.setPlayerNumber(2);
           ourKeyboard.setKeyHandling(game);
           game.init();
           game.start();
       }

    }
}




