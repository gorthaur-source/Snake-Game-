package org.academiadecodigo.tailormoons.snake.Node;

abstract public class Node {

    protected int x;
    protected int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
