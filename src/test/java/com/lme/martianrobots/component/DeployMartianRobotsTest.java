package com.lme.martianrobots.component;


import com.lme.martianrobots.exception.MartianRobotsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(OutputCaptureExtension.class)
class DeployMartianRobotsTest {

    @Mock
    private Scanner scannerMock;

    @Mock
    private ContextRefreshedEvent contextRefreshedEventMock;

    private DeployMartianRobots deployMartianRobots;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        deployMartianRobots = new DeployMartianRobots(scannerMock);
    }

    @Test
    public void shouldDeployMartianRobot(CapturedOutput capturedOutput) {

        when(scannerMock.nextInt()).thenReturn(5).thenReturn(3)
                .thenReturn(1).thenReturn(1);
        when(scannerMock.hasNextLine()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(scannerMock.next()).thenReturn("E").thenReturn("RFRFRFRF");

        deployMartianRobots.init(contextRefreshedEventMock);

        assertThat(capturedOutput).contains("1 1 E");
    }

    @Test
    public void shouldDeployLostMartianRobot(CapturedOutput capturedOutput) {

        when(scannerMock.nextInt()).thenReturn(5).thenReturn(3)
                .thenReturn(3).thenReturn(2)
                .thenReturn(0).thenReturn(3);
        when(scannerMock.hasNextLine()).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(false);
        when(scannerMock.next()).thenReturn("N").thenReturn("FRRFLLFFRRFLL")
                .thenReturn("W").thenReturn("LLFFFLFLFL");

        deployMartianRobots.init(contextRefreshedEventMock);

        assertThat(capturedOutput).contains("3 3 N LOST");
        assertThat(capturedOutput).contains("2 3 S");
    }

    @Test
    public void shouldThrowExceptionWhenInvalidGridRange() {
        when(scannerMock.nextInt()).thenReturn(0).thenReturn(0);
        assertThatThrownBy(() -> deployMartianRobots.init(contextRefreshedEventMock))
                .isInstanceOf(MartianRobotsException.class)
                .hasMessageContaining("Please enter a valid martian grid range");

        when(scannerMock.nextInt()).thenReturn(51).thenReturn(51);
        assertThatThrownBy(() -> deployMartianRobots.init(contextRefreshedEventMock))
                .isInstanceOf(MartianRobotsException.class)
                .hasMessageContaining("Please enter a valid martian grid range");
    }

    @Test
    public void shouldThrowExceptionWhenInvalidRobotInstructions() {
        when(scannerMock.nextInt()).thenReturn(5).thenReturn(3)
                .thenReturn(1).thenReturn(1);
        when(scannerMock.hasNextLine()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(scannerMock.next()).thenReturn("E").thenReturn("RFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRF" +
                "RFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRFRF");

        assertThatThrownBy(() -> deployMartianRobots.init(contextRefreshedEventMock))
                .isInstanceOf(MartianRobotsException.class)
                .hasMessageContaining("Robot instructions strings must be less than 100 characters");
    }
}