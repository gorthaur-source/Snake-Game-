package org.academiadecodigo.tailormoons.snake.Keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class OurKeyboardHandler implements KeyboardHandler {

    private KeyHandler keyHandling;

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        try {
            keyHandling.pressed(keyboardEvent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void keyMapping() {

        Keyboard keyboard = new Keyboard(this);

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

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(space);

    }

    public void setKeyHandling(KeyHandler keyHandling) {
        this.keyHandling = keyHandling;
    }


}
