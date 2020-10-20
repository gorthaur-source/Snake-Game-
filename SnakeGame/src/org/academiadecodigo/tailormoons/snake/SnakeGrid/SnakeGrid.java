package org.academiadecodigo.tailormoons.snake.SnakeGrid;

import org.academiadecodigo.tailormoons.snake.Node.Consumable;
import org.academiadecodigo.tailormoons.snake.Snake.Snake;

public interface SnakeGrid {


    void initGrid(int rows, int columns);

    void createFood();

    Consumable getFood();

    void checkCollision();

    void setSnake(Snake[] snake);
}



