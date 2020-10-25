package org.academiadecodigo.tailormoons.snake.Keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public interface KeyHandler {

    void pressed(KeyboardEvent e) throws InterruptedException;
}
