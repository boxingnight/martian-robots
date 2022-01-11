package com.lme.martianrobots.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Robot {

    private int x;
    private int y;
    private Direction direction;
    private Mars mars;

    public void execute(char instruction) {
        switch (instruction) {
            case 'L':
                left();
                break;
            case 'R':
                right();
                break;
            case 'F':
                forward();
        }
    }

    private void left() {
        switch (direction) {
            case N:
                direction = Direction.W;
                break;
            case E:
                direction = Direction.N;
                break;
            case S:
                direction = Direction.E;
                break;
            case W:
                direction = Direction.S;
        }
    }

    private void right() {
        switch (direction) {
            case N:
                direction = Direction.E;
                break;
            case E:
                direction = Direction.S;
                break;
            case S:
                direction = Direction.W;
                break;
            case W:
                direction = Direction.N;
        }
    }

    private void forward() {
        switch (direction) {
            case N:
                if (!mars.isExistingOutOfBoundsCoordinate(x, y + 1)) {
                    y++;
                }
                break;
            case E:
                if (!mars.isExistingOutOfBoundsCoordinate(x + 1, y)) {
                    x++;
                }
                break;
            case S:
                if (!mars.isExistingOutOfBoundsCoordinate(x, y - 1)) {
                    y--;
                }
                break;
            case W:
                if (!mars.isExistingOutOfBoundsCoordinate(x - 1, y)) {
                    x--;
                }
        }
    }

}
