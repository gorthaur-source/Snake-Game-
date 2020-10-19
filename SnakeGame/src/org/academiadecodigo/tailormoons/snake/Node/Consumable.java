package org.academiadecodigo.tailormoons.snake.Node;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.SnakeGame;

public class Consumable extends Node{

    private Ellipse consumable;
    private Picture apple;

    public Consumable(int x, int y) {
        super(x, y);
        consumable = new Ellipse(x * SnakeGame.CELL_SIZE, y * SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE, SnakeGame.CELL_SIZE);
        consumable.setColor(Color.GREEN);
        consumable.fill();
        apple = new Picture(x*SnakeGame.CELL_SIZE, y*SnakeGame.CELL_SIZE-3, "assets/Apple1.png");
        apple.grow(0.1,0.1);
        apple.draw();
    }

    public void hide() {
        consumable.delete();
        apple.delete();
    }

    public void show(){
        consumable.draw();
        apple.draw();
    }
}
