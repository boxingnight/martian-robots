package com.lme.martianrobots.component;

import com.lme.martianrobots.domain.Direction;
import com.lme.martianrobots.domain.Mars;
import com.lme.martianrobots.domain.Robot;
import com.lme.martianrobots.exception.MartianRobotsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeployMartianRobots {

    private final Scanner scanner;

    @EventListener
    public void init(ContextRefreshedEvent event) {

        int marsX = scanner.nextInt();
        int marsY = scanner.nextInt();
        validateMartianGrid(marsX, marsY);

        Mars mars = new Mars(marsX, marsY);

        boolean lineWithRobotInstructions = true;
        Robot robot = null;

        // Brittle and makes an assumption that the input file is always the correct format
        while (scanner.hasNextLine()) {
            if (lineWithRobotInstructions) {
                robot = initialiseRobot(scanner, mars);
            } else {
                executeRobotInstructions(scanner, robot, mars);
            }
            lineWithRobotInstructions = !lineWithRobotInstructions;
        }
    }

    private void validateMartianGrid(int marsX, int marsY) {
        if (marsX <= 0 || marsX > 50 || marsY <= 0 || marsY > 50) {
            throw new MartianRobotsException("Please enter a valid martian grid range");
        }
    }

    private Robot initialiseRobot(Scanner scanner, Mars mars) {
        return new Robot(scanner.nextInt(),
                scanner.nextInt(),
                Direction.valueOf(scanner.next()),
                mars
        );
    }

    private void executeRobotInstructions(Scanner scanner, Robot robot, Mars mars) {
        String robotInstructions = scanner.next();
        if (robotInstructions.toCharArray().length > 99) {
            throw new MartianRobotsException("Robot instructions strings must be less than 100 characters");
        }

        int currentRobotX = robot.getX();
        int currentRobotY = robot.getY();

        boolean lostRobot = false;

        for (char instruction : robotInstructions.toCharArray()) {
            robot.execute(instruction);

            if (mars.isOutOfBounds(robot.getX(), robot.getY())) {
                mars.saveOutOfBoundsCoordinate(robot.getX(), robot.getY());
                lostRobot = true;
                break;
            }

            currentRobotX = robot.getX();
            currentRobotY = robot.getY();
        }

        log.info(currentRobotX + " "
                        + currentRobotY + " "
                        + robot.getDirection()
                        + (lostRobot ? " LOST" : ""
                )
        );
    }
}
