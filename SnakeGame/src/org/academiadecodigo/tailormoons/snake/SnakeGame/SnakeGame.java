package org.academiadecodigo.tailormoons.snake.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGrid;

public interface SnakeGame {


    public void snakeInit();

    public void init();

    public void keyboardSnakeInit();

    public void start() throws InterruptedException;

    public boolean isGameOver();

    public void updateScore();

    public boolean snakeHasEaten(Consumable food);

    public Snake getSnake();

    public SnakeGrid getGrid();

}
