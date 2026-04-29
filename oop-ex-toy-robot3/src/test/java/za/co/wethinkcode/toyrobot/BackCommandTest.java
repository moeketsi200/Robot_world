package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackCommandTest {
    @Test
    void testMoveBack() {
        Robot robot = new Robot("Hal"); // Starts at [0,0] facing NORTH
        Command back = new BackCommand("10");
        assertTrue(robot.handleCommand(back));
        
        Position expectedPosition = new Position(0, -10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved back by 10 steps.", robot.getStatus());
    }
}