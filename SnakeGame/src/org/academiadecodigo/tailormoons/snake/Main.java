package org.academiadecodigo.tailormoons.snake;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SnakeGame game = new SnakeGame(new SnakeGridObstacles());
        game.init();
        game.start();
    }
}
