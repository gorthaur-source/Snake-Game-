package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Node.Consumable;

public interface SnakeGrid {


    void initGrid();

    void createFood();


    Consumable getFood();

    void checkCollision();

    void setSnake(Snake snake);
}



