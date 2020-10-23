package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;

public interface SnakeGame {


    void init() throws InterruptedException;

    void start() throws InterruptedException;

    boolean isGameOver();

    void updateScore();

    boolean snakeHasEaten(Snake snake);

}
