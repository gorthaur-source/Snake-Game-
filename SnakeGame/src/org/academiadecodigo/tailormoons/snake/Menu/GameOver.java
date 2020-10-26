package org.academiadecodigo.tailormoons.snake.Menu;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;
import org.academiadecodigo.tailormoons.snake.Keyboard.OurKeyboardHandler;

public class GameOver implements KeyHandler {


    private Picture picture = new Picture(340, 226, "assets/Menu/GameOver.jpg");

    private boolean isPlayButtonSelected = true;
    private boolean isBackButtonSelected;
    private OurKeyboardHandler handler;
    private Picture startSelected = new Picture(540, 500, "assets/Menu/PlayEdge.png");
    private Picture start = new Picture(540, 500, "assets/Menu/Play.png");
    private Picture menuSelected = new Picture(540, 600, "assets/Menu/StartEdge.png");
    private Picture menu = new Picture(540, 600, "assets/Menu/Start.png");


    public GameOver(OurKeyboardHandler handler) {
        this.handler = handler;
        System.out.println("checking");

    }

    public void show() {
        picture.draw();
    }

    @Override
    public void pressed(KeyboardEvent e) throws InterruptedException {

    }
}
