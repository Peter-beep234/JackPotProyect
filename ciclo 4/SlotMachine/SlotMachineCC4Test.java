import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author  (RodriguezH - SarmientoM)
 * @version (ciclo 4)
 */
public class SlotMachineCC4Test
{
    @Test
    public void ShouldMakeJackpotWithLefty() {
        SlotMachine machine = new SlotMachine();
        machine.addSymbol(1, "red");
        machine.addSymbol(2, "green");
        machine.addWheel("normal", 1);
        machine.addWheel("lefty", 2);
        machine.spin(2, 1);
        assertTrue(machine.isJackpot());
    }

    @Test
    public void ShouldNotLockRebel() {
        SlotMachine machine = new SlotMachine();
        machine.addSymbol(1, "red");
        machine.addSymbol(2, "green");
        machine.addWheel("rebel", 1);
        machine.lock(1);
        assertFalse(machine.getWheels().get(0).isLocked());
    }
}
