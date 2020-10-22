package org.academiadecodigo.tailormoons.snake.Keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

import java.awt.event.KeyEvent;

public interface KeyHandler {

    void pressed(KeyboardEvent e) throws InterruptedException;
}
