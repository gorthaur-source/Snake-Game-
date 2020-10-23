package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame1P;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        OurKeyboardHandler ourKeyboard = new OurKeyboardHandler();
        Keyboard keyboard = new Keyboard(ourKeyboard);

        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);

        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(right);

        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_UP);

        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_DOWN);
        keyboard.addEventListener(down);


        KeyboardEvent leftTwo = new KeyboardEvent();
        leftTwo.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftTwo.setKey(KeyboardEvent.KEY_A);

        keyboard.addEventListener(leftTwo);

        KeyboardEvent rightTwo = new KeyboardEvent();
        rightTwo.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightTwo.setKey(KeyboardEvent.KEY_W);

        keyboard.addEventListener(rightTwo);

        KeyboardEvent upTwo = new KeyboardEvent();
        upTwo.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        upTwo.setKey(KeyboardEvent.KEY_D);

        keyboard.addEventListener(upTwo);

        KeyboardEvent downTwo = new KeyboardEvent();
        downTwo.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        downTwo.setKey(KeyboardEvent.KEY_S);
        keyboard.addEventListener(downTwo);

        SnakeGame snakeGame = new SnakeGame1P(new SnakeGridNormal());
        ourKeyboard.setKeyHandling((KeyHandler) snakeGame);
        snakeGame.init();
        snakeGame.start();


    }
}
