package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeftCommandTest {
    @Test
    void testLeftTurn() {
        Robot robot = new Robot("Hal"); 
        Command left = new LeftCommand();
        robot.handleCommand(left);
        assertEquals(Direction.WEST, robot.getCurrentDirection()); 
    }   
}