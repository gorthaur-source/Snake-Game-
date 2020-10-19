package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardListener implements KeyboardHandler {

    private final Snake player;

    public KeyboardListener(Snake player) {
       this.player = player;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if(!player.isDirectionChanged()) {
            player.setDirectionChanged(true);
            switch (e.getKey()) {
                case KeyboardEvent.KEY_LEFT: {
                    player.changeDirection(Direction.LEFT);
                }
                case KeyboardEvent.KEY_RIGHT: {
                    player.changeDirection(Direction.RIGHT);
                    break;
                }
                case KeyboardEvent.KEY_UP: {
                    player.changeDirection(Direction.UP);
                    break;
                }
                case KeyboardEvent.KEY_DOWN: {
                    player.changeDirection(Direction.DOWN);
                    break;
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyboardEvent e) {

    }

}
