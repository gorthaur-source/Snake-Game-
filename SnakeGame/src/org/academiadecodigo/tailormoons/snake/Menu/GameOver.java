package org.academiadecodigo.tailormoons.snake.Menu;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.Keyboard.KeyHandler;

public class GameOver implements KeyHandler {


    private Picture picture = new Picture(340, 35, "assets/Menu/GameOver.jpg");

    private volatile boolean playAgain;
    private  boolean backToMenu;
    private boolean isPlayButtonSelected;
    private boolean isBackButtonSelected;
    private Picture playButtonSelected = new Picture(540, 500, "assets/Menu/PlayEdge.png");
    private Picture playButton = new Picture(540, 500, "assets/Menu/Play.png");
    private Picture backButtonSelected = new Picture(540, 600, "assets/Menu/StartEdge.png");
    private Picture backButton = new Picture(540, 600, "assets/Menu/Start.png");


    public GameOver() {


    }

    public void show() {
        isPlayButtonSelected = true;
        picture.draw();
        playButtonSelected.draw();
        backButton.draw();
        playAgain = false;
    }

    @Override
    public void pressed(KeyboardEvent e) throws InterruptedException {

        switch (e.getKey()) {
            case KeyboardEvent.KEY_DOWN:
                if (isPlayButtonSelected) {
                    System.out.println("checking");
                    isPlayButtonSelected = false;
                    playButtonSelected.delete();
                    playButton.draw();
                    backButton.delete();
                    backButtonSelected.draw();
                    isBackButtonSelected = true;
                    break;
                } else if (isBackButtonSelected) {
                    break;
                }
            case KeyboardEvent.KEY_UP:
                if (isBackButtonSelected) {
                    isBackButtonSelected = false;
                    backButtonSelected.delete();
                    backButton.draw();
                    playButtonSelected.draw();
                    playButton.delete();
                    isPlayButtonSelected = true;
                    break;
                }
                if (isPlayButtonSelected) break;
            case KeyboardEvent.KEY_SPACE:
                if(isPlayButtonSelected) {
                    isPlayButtonSelected = false;
                    backButton.delete();
                    backButtonSelected.delete();
                    playButton.delete();
                    playButtonSelected.delete();
                    picture.delete();
                    playAgain = true;
                    break;
                }
                else if (isBackButtonSelected) {
                    isBackButtonSelected = false;
                    backButton.delete();
                    backButtonSelected.delete();
                    playButton.delete();
                    playButtonSelected.delete();
                    picture.delete();
                    backToMenu = true;
                }
        }

    }

    public boolean getPlay() {
        return playAgain;
    }
    public void setPlayAgain(boolean value) {
        playAgain = value;
    }
    public boolean backTo() {
        return backToMenu;
    }

    public void setBackTo() {
        backToMenu = false;
    }
}
