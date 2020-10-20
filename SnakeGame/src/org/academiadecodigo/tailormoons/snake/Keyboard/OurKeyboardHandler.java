package org.academiadecodigo.tailormoons.snake.Keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.awt.event.KeyEvent;

public class OurKeyboardHandler implements KeyboardHandler {

    private KeyHandler keyHandling;

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        System.out.println("Hello");
        keyHandling.pressed(keyboardEvent);
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
