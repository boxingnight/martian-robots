package com.lme.martianrobots.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

class RobotTest {

    @Mock
    private Mars marsMock;

    private Robot robot;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        robot = new Robot(1, 1, Direction.N, marsMock);
    }

    @Test
    public void shouldTurnLeft() {
        robot.execute('L');
        assertThat(robot.getDirection()).isEqualTo(Direction.W);
        robot.execute('L');
        assertThat(robot.getDirection()).isEqualTo(Direction.S);
        robot.execute('L');
        assertThat(robot.getDirection()).isEqualTo(Direction.E);
        robot.execute('L');
        assertThat(robot.getDirection()).isEqualTo(Direction.N);
    }

    @Test
    public void shouldTurnRight() {
        robot.execute('R');
        assertThat(robot.getDirection()).isEqualTo(Direction.E);
        robot.execute('R');
        assertThat(robot.getDirection()).isEqualTo(Direction.S);
        robot.execute('R');
        assertThat(robot.getDirection()).isEqualTo(Direction.W);
        robot.execute('R');
        assertThat(robot.getDirection()).isEqualTo(Direction.N);
    }

    @Test
    public void shouldMoveForward() {
        robot.execute('F');
        assertThat(robot.getDirection()).isEqualTo(Direction.N);
        assertThat(robot.getX()).isEqualTo(1);
        assertThat(robot.getY()).isEqualTo(2);
        robot.execute('R');
        robot.execute('F');
        assertThat(robot.getDirection()).isEqualTo(Direction.E);
        assertThat(robot.getX()).isEqualTo(2);
        assertThat(robot.getY()).isEqualTo(2);
        robot.execute('R');
        robot.execute('F');
        assertThat(robot.getDirection()).isEqualTo(Direction.S);
        assertThat(robot.getX()).isEqualTo(2);
        assertThat(robot.getY()).isEqualTo(1);
        robot.execute('R');
        robot.execute('F');
        assertThat(robot.getDirection()).isEqualTo(Direction.W);
        assertThat(robot.getX()).isEqualTo(1);
        assertThat(robot.getY()).isEqualTo(1);
    }
}