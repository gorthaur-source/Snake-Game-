package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;

public interface SnakeGame {




    public void init() throws InterruptedException;


    public void start() throws InterruptedException;

    public boolean isGameOver();

    public void updateScore();

    public boolean snakeHasEaten(Snake snake);

}
