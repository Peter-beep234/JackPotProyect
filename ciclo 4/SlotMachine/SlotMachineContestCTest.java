import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
/**
 * The test class SlotMachineContestCTest.
 *
 * @author  (RodriguezH - SarmientoM)
 */ 
public class SlotMachineContestCTest
{
    @Before  
    public void setUp()
    {
        int [][] pasos = SlotMachineContest.solve(3);
    }
    
    @Test
    public void shouldSimulateTheMachine(){
        SlotMachineContest.simulate(3);
    }
    
    @Test
    public void shouldNotSimulateAnEmptyMachine(){
        SlotMachineContest.simulate(0);
    }
    
    @Test
    public void shouldSolveAMachine() {
        assertNotEquals(0, SlotMachineContest.solve(3).length);
        assertNotEquals(0, SlotMachineContest.solve(SlotMachineContest.COLORES).length);
    }
    
    @Test
    public void shouldNotSolveInvalidSizes() {
        assertEquals(0, SlotMachineContest.solve(0).length);
        assertEquals(0, SlotMachineContest.solve(SlotMachineContest.COLORES + 1).length);
    }
    
    @After
    public void erase(){
        
    }
}