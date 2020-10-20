package org.academiadecodigo.tailormoons.snake;

import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame2P;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridObstacles;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SnakeGame2P game = new SnakeGame2P(new SnakeGridObstacles());
        game.init();
        game.start();
    }
}
