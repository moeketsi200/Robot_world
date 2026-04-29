package za.co.wethinkcode.toyrobot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RightCommandTest {
@Test
void testRightTurn() {
    Robot robot = new Robot("Hal"); 
    Command right = new RightCommand();
    robot.handleCommand(right);
    assertEquals(Direction.EAST, robot.getCurrentDirection()); 
    }   
}