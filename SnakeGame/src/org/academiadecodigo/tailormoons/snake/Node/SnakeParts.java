package org.academiadecodigo.tailormoons.snake.Node;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tailormoons.snake.SnakeGame.SnakeGame1P;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tailormoons.snake.Direction;
import org.academiadecodigo.tailormoons.snake.SnakeGrid.SnakeGridNormal;
import org.academiadecodigo.tailormoons.snake.Sprites.*;

public class SnakeParts extends org.academiadecodigo.tailormoons.snake.Node.Node {

    private Sprites sprite;
    private Direction direction;
    private Direction previousDirection;
    private Direction nextDirection;
    private SnakeParts previous;
    private SnakeParts next;
    private String typeOfSnakePart;


    public SnakeParts(int x, int y) {
        super(x, y);
        direction = Direction.UP;
        previousDirection = Direction.UP;
        sprite = new BodySprite(x * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING, y * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING);
        typeOfSnakePart = "body";
    }

    public SnakeParts(int x, int y, String typeOfSnakePart) {
        super(x, y);
        direction = Direction.UP;
        previousDirection = Direction.UP;
        if (typeOfSnakePart.equals("tail")) {
            sprite = new TailSprite(x * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING, y * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING);
            this.typeOfSnakePart = typeOfSnakePart;
            return;
        }
        this.typeOfSnakePart = typeOfSnakePart;
        sprite = new HeadSprite(x * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING, y * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING);
    }


    public Direction getDirection() {
        return direction;
    }

    public void moveInDirection() {

        switch (direction) {
            case LEFT:
                if (x == 0) {
                    x = SnakeGridNormal.COLS - 1;
                    sprite.translate(((SnakeGridNormal.COLS - 1) * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING), 0);
                    return;
                }
                x--;
                sprite.translate(-SnakeGame1P.CELL_SIZE, 0);
                break;
            case DOWN:
                if (y == SnakeGridNormal.ROWS - 1) {
                    y = 0;
                    sprite.translate(0, -((SnakeGridNormal.ROWS - 1) * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING));
                    return;
                }
                y++;
                sprite.translate(0, SnakeGame1P.CELL_SIZE);
                break;
            case UP:
                if (y == 0) {
                    y = SnakeGridNormal.ROWS - 1;
                    sprite.translate(0, (SnakeGridNormal.ROWS - 1) * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING);
                    return;
                }
                y--;
                sprite.translate(0, -SnakeGame1P.CELL_SIZE);
                break;
            case RIGHT:
                if (x == SnakeGridNormal.COLS - 1) {
                    x = 0;
                    sprite.translate(-((SnakeGridNormal.COLS - 1) * SnakeGame1P.CELL_SIZE + SnakeGame1P.PADDING), 0);
                    return;
                }
                x++;
                sprite.translate(SnakeGame1P.CELL_SIZE, 0);
                break;
        }
    }

    public void copyDirection(SnakeParts part) {
        previousDirection = part.getPreviousDirection();
        direction = part.getDirection();
        previous = part.getPrevious();
        next = part.getNext();
        sprite.setSprite(( (BodySprite) part.getSprite() ).getTypeOfSprite());
    }

    public SnakeParts getPrevious() {
        return previous;
    }

    public SnakeParts getNext() {
        return next;
    }

    public Sprites getSprite() {
        return sprite;
    }

    public void setDirection(SnakeParts previous, SnakeParts next) {
        direction = previous.getPreviousDirection();
        this.previous = previous;
        this.next = next;
    }

    public void updateSprites() {

        if (typeOfSnakePart.equals("head") || typeOfSnakePart.equals("tail")) {
            updateSpriteHeadAndTail();
            return;
        }

        //Condição de tudo igual

        //Condição de Saída
        if (direction != previousDirection && direction == previous.getPreviousDirection()) {
            System.out.println("A sair");
            if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                sprite.setSprite(TypeOfSprite.LEFT);
                return;
            }

            if (direction == Direction.UP || direction == Direction.DOWN) {
                sprite.setSprite(TypeOfSprite.UP);
                return;
            }
        }


        //Condição de entrada
        if (direction != previous.getPreviousDirection()) {

            System.out.println("A entrar");
            if (direction == Direction.UP && previous.getPreviousDirection() == Direction.RIGHT || direction == Direction.LEFT && previous.getPreviousDirection() == Direction.DOWN) {
                sprite.setSprite(TypeOfSprite.CONERUPRIGHT);
                return;
            }

            if (direction == Direction.UP && previous.getPreviousDirection() == Direction.LEFT || direction == Direction.RIGHT && previous.getPreviousDirection() == Direction.DOWN) {
                sprite.setSprite(TypeOfSprite.CORNERUPLEFT);
                return;
            }

            if (direction == Direction.RIGHT && previous.getPreviousDirection() == Direction.UP || direction == Direction.DOWN && previous.getPreviousDirection() == Direction.LEFT) {
                sprite.setSprite(TypeOfSprite.CORNERRIGHTUP);
                return;
            }

            if (direction == Direction.LEFT && previous.getPreviousDirection() == Direction.UP || direction == Direction.DOWN && previous.getPreviousDirection() == Direction.RIGHT) {
                sprite.setSprite(TypeOfSprite.CORNERLEFTUP);
                return;
            }
        }


        if (direction == previousDirection) {
            // System.out.println("Tudo igual");
            return;
        }


    }

    private void updateSpriteHeadAndTail() {


        if (typeOfSnakePart.equals("tail")) {
            Direction dir = previous.getPreviousDirection();
            System.out.println("Tail activited");
            switch (dir) {
                case LEFT:
                    sprite.setSprite(TypeOfSprite.LEFT);
                    return;
                case RIGHT:
                    sprite.setSprite(TypeOfSprite.RIGHT);
                    return;
                case DOWN:
                    sprite.setSprite(TypeOfSprite.DOWN);
                    return;
                case UP:
                    sprite.setSprite(TypeOfSprite.UP);
                    return;
            }
        }

        if (previousDirection == direction) {
            return;
        }

        switch (direction) {
            case LEFT:
                sprite.setSprite(TypeOfSprite.LEFT);
                return;
            case RIGHT:
                sprite.setSprite(TypeOfSprite.RIGHT);
                return;
            case DOWN:
                sprite.setSprite(TypeOfSprite.DOWN);
                return;
            case UP:
                sprite.setSprite(TypeOfSprite.UP);
                return;
        }
    }


    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setPreviousDirection(Direction direction) {
        previousDirection = direction;
    }

    public void setNextDirection(Direction direction) {
        nextDirection = direction;
    }

    public void setDirection(Direction direction) {
        if (this.direction.isOpposite(direction)) {
            return;
        }

        this.direction = direction;
        updateSprites();
        System.out.println("Changed Direction of Head" + direction);
    }
}
