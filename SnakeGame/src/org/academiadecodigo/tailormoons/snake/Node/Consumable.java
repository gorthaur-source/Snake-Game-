package org.academiadecodigo.tailormoons.snake.Node;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame1P;

public class Consumable extends Node{

    private Picture planet;
    String filePath;

    public Consumable(int x, int y) {
        super(x, y);

        int zeroToSix = (int) (Math.random() * 7);
        switch(zeroToSix) {
            case 0:
                filePath = "assets/Consumables/earth.png";
                break;
            case 1:
                filePath = "assets/Consumables/saturn.png";
                break;
            case 2:
                filePath = "assets/Consumables/weird.png";
                break;
            case 3:
                filePath = "assets/Consumables/ice.png";
                break;
            case 4:
                filePath = "assets/Consumables/moon.png";
                break;
            case 5:
                filePath = "assets/Consumables/green.png";
                break;
            case 6:
                filePath = "assets/Consumables/red.png";
                break;
        }
        planet = new Picture(x* SnakeGame1P.CELL_SIZE, y* SnakeGame1P.CELL_SIZE-4, filePath);
    }

    public void hide() {
        planet.delete();
    }

    public void show(){
        planet.draw();
    }
}
