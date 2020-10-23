package org.academiadecodigo.tailormoons.snake.Keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
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


    }

    public void setKeyHandling(KeyHandler keyHandling) {
        this.keyHandling = keyHandling;
    }


}
