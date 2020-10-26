package org.academiadecodigo.tailormoons.snake.Keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.HashMap;
import java.util.Map;

public class OurKeyboardHandler implements KeyboardHandler {


    private Map<Integer, Boolean> keyStates;
    private Keyboard myKeyboard;

    private int[] KEYS = {
            KeyboardEvent.KEY_UP,
            KeyboardEvent.KEY_DOWN,
            KeyboardEvent.KEY_LEFT,
            KeyboardEvent.KEY_RIGHT,
            KeyboardEvent.KEY_W,
            KeyboardEvent.KEY_S,
            KeyboardEvent.KEY_A,
            KeyboardEvent.KEY_D,
            KeyboardEvent.KEY_SPACE,


    };
    private KeyHandler keyHandling;

    public OurKeyboardHandler() {
        myKeyboard = new Keyboard(this);
        keyStates = new HashMap<>();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        keyStates.put(keyboardEvent.getKey(), true);

        try {
            keyHandling.pressed(keyboardEvent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        keyStates.put(keyboardEvent.getKey(), false);
    }

    private void subscribe(int code, KeyboardEventType type) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(code);
        event.setKeyboardEventType(type);
        myKeyboard.addEventListener(event);
    }

    public void keyMapping() {
        for (int code : KEYS) {
            for (KeyboardEventType type : KeyboardEventType.values()) {
                subscribe(code, type);
            }
        }
    }

    public void setKeyHandling(KeyHandler keyHandling) {
        this.keyHandling = keyHandling;
    }


}
