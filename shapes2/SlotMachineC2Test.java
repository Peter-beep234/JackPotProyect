import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


/**
 * The test class SlotMachineC2Test.
 *
 * @author  (RodriguezH - SarmientoM)
 * @version (6/09/2026)
 */
public class SlotMachineC2Test
{
    @Before
    public void setUp() {
        
    }
    
    @Test
    public void shouldCreateEmptyMachine() {
        SlotMachine empty = new SlotMachine();
        assertEquals(0, empty.symbols().length);
    }
    
    @Test
    public void shouldHaveDiferentSymbols() {
        SlotMachine machine = new SlotMachine();
        
        machine.addSymbol(0, "yellow");
        machine.addSymbol(1, "black");
        machine.addSymbol(2, "blue");
        
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);
        
        machine.spin(2,1);
        machine.spin(3,2);
        
        assertEquals(3, machine.distinctSymbols());
    }
    
    @Test
    public void shouldNotHaveDiferentSymbols() {
        SlotMachine machine = new SlotMachine();
        
        machine.addSymbol(0, "yellow");
        machine.addSymbol(1, "black");
        machine.addSymbol(2, "blue");
        
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);
        
        machine.spin(2,1);
        machine.spin(3,1);
        
        assertEquals(2, machine.distinctSymbols());
    }
    
    @After
    public void erase(){
    }
}