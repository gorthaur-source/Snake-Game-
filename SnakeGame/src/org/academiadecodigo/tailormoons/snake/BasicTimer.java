package org.academiadecodigo.tailormoons.snake;

public class BasicTimer {
    private int fps;
    private long timeThen;
    boolean newVersion = true;

    public BasicTimer(int frameRate) {
        if (System.getProperty("java.version").startsWith("1.4"))
            newVersion = false;
        if (newVersion) {
            fps = frameRate;
            timeThen = System.nanoTime();
        } else {
            fps = frameRate;
            timeThen = System.currentTimeMillis();
        }
    }

    public void changeFPS(int frameRate) {
        fps = frameRate;
    }

    public void sync() {
        if (newVersion) {
            long gapTo = 1000000000L / fps + timeThen;
            long timeNow = System.nanoTime();

            while (gapTo > timeNow) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                timeNow = System.nanoTime();
            }

            timeThen = timeNow;
        } else {
            long gapTo = 1000 / fps + timeThen;
            long timeNow = System.currentTimeMillis();

            while (gapTo > timeNow) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                timeNow = System.currentTimeMillis();
            }

            timeThen = timeNow;
        }
    }
}