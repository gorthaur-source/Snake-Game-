package org.academiadecodigo.tailormoons.snake.Node;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame1P;

public class Consumable extends Node{

    private Picture apple;

    public Consumable(int x, int y) {
        super(x, y);

        apple = new Picture(x* SnakeGame1P.CELL_SIZE, y* SnakeGame1P.CELL_SIZE-4, "assets/Apple1.png");
    }

    public void hide() {
        apple.delete();
    }

    public void show(){
        apple.draw();
    }
}
