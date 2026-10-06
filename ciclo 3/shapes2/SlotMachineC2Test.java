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
    private SlotMachine machine;
    @Before
    public void setUp() {
        machine = new SlotMachine();
        
        machine.addSymbol(1, "red");
        machine.addSymbol(2, "green");
        machine.addSymbol(3, "yellow");
        
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);
        
        for(int i = 1; i <= machine.symbols().length; i++){
            machine.placeSymbol(i, "red");
        }
    }
    
    //SlotMachine()
    @Test
    public void shouldCreateEmptyMachine() {
        SlotMachine empty = new SlotMachine();
        int tamaño = empty.symbols().length;
        assertEquals(0, tamaño);
    }
    
    @Test
    public void shouldNotCreateEmptyMachine() {
        SlotMachine notEmpty = new SlotMachine();
        notEmpty.addSymbol(1, "red");
        notEmpty.addWheel(1);
        assertEquals(1, notEmpty.symbols().length);
    }
    
    //SlotMachine(n)
    
    public void shouldCreateMachineWithNWheels() {
        SlotMachine m = new SlotMachine(4);
        assertEquals(4, m.configuration().length);
        assertEquals(5, m.symbols().length);
    }
    
    @Test
    public void shouldNotCreateWheelsWhenNIsZero() {
        SlotMachine m = new SlotMachine(0);
        assertEquals(0, m.configuration().length);
    }
    
    //addWheel(pos)
    
    @Test
    public void shouldAddWheel() {
        machine.addWheel(4);
        assertEquals(4, machine.configuration().length);
    }
    
    @Test
    public void shouldNotAddWheel() {
        SlotMachine empty = new SlotMachine();
        empty.addWheel(1);
        assertEquals(0, empty.configuration().length);
    }
    
    //delWheel(pos)
    
    @Test
    public void shouldDeleteWheel() {
        machine.delWheel(1);
        assertEquals(2, machine.configuration().length);
    }

    public void shouldNotDeleteWheelThatDoesNotExist() {
        SlotMachine empty = new SlotMachine();
        empty.delWheel(1);
        assertEquals(0, machine.configuration().length);
    }
    
    //swap(wheel1, wheel2)
    
    @Test
    public void shouldSwapWheels() {
        machine.spin(1, 1);
        machine.swap(1, 2);
        assertArrayEquals(new String[]{"red", "green", "red"}, machine.configuration());
    }
    
    @Test
    public void shouldNotSwapWheelThatDoesNotExist() {
        machine.swap(1, 1);
        assertEquals(new String[]{"red", "red", "red"}, machine.configuration());
    }
    
    //lock(wheel)
    
    @Test
    public void shouldLockWheel() {
        machine.lock(1);
        machine.spin(1, 1);
        assertEquals("red", machine.configuration()[0]);
    }
    
    @Test
    public void shouldNotLockOtherWheels() {
        machine.lock(1);
        machine.spin(2, 1);
        assertEquals("green", machine.configuration()[1]);
    }
    
    //unLock(wheel)
    
    @Test
    public void shouldUnlockWheel() {
        machine.lock(1);
        machine.unlook(1);
        machine.spin(1, 1);
        assertEquals("green", machine.configuration()[0]);
    }

    @Test
    public void shouldNotUnlockOtherWheels() {
        machine.lock(1);
        machine.lock(2);
        machine.unlook(1);
        machine.spin(2, 1);
        assertEquals("red", machine.configuration()[1]);
    }
    
    //addSymbol(pos, color)
    
    @Test
    public void shouldAddSymbolInPosition() {
        machine.addSymbol(2, "blue");
        assertEquals(new String[]{"red", "blue", "green", "yellow"}, machine.symbols());
    }

    public void shouldNotAddSymbolInPositionZero() {
        machine.addSymbol(0, "green");
        assertEquals(new String[]{"red", "green", "yellow"}, machine.symbols());
    }

    //delSymbol(color)
    
    @Test
    public void shouldDeleteSymbol() {
        machine.delSymbol("yellow");
        assertArrayEquals(new String[]{"red", "green"}, machine.symbols());
    }

    @Test
    public void shouldNotDeleteSymbolThatDoesNotExist() {
        machine.delSymbol("blue");
        assertEquals(3, machine.symbols().length);
    }
    
    //placeSymbol(wheel, color)
    
    @Test
    public void shouldPlaceSymbol() {
        machine.placeSymbol(2, "green");
        assertEquals("green", machine.configuration()[1]);
    }

    @Test
    public void shouldNotPlaceSymbolThatDoesNotExist() {
        machine.placeSymbol(2, "blue");
        assertEquals("red", machine.configuration()[1]);
    }
    
    //spin(wheel)
    
    @Test
    public void shouldSpinWheelToAValidSymbol() {
        machine.spin(1);
        String s = machine.configuration()[0];
        assertEquals(s, "red");
    }

    @Test
    public void shouldNotSpinLockedWheel() {
        machine.lock(1);
        machine.spin(1);
        assertEquals("red", machine.configuration()[0]);
    }
    
    //spin()
    
    @Test
    public void shouldSpinAllWheelsToValidSymbols() {
        machine.spin();
        for (String s : machine.configuration()) {
            assertTrue(s.equals("red") || s.equals("green") || s.equals("yellow"));
        }
    }

    @Test
    public void shouldNotSpinWhenFirstWheelIsLocked() {
        machine.lock(1);
        machine.spin();
        assertEquals("red", machine.configuration()[0]);
    }
    
    //spin(wheel, steps)
    
    @Test
    public void shouldSpinWheelSteps() {
        machine.spin(1, 2);
        assertEquals("yellow", machine.configuration()[0]);
        machine.spin(1, 1);
        assertEquals("red", machine.configuration()[0]);
    }

    @Test
    public void shouldNotSpinLockedWheelSteps() {
        machine.lock(3);
        machine.spin(3, 2);
        assertEquals("red", machine.configuration()[2]);
    }
    
    //spin(String[])
    
    @Test
    public void shouldSetConfiguration() {
        machine.spin(new String[]{"yellow", "green", "red"});
        assertArrayEquals(new String[]{"yellow", "green", "red"}, machine.configuration());
    }
    
    @Test
    public void shouldNotSetSymbolThatDoesNotExist() {
        machine.spin(new String[]{"green", "blue", "green"});
        assertEquals(new String[]{"green", "red", "green"}, machine.configuration());
    }
    
    //symbols()
    
    @Test
    public void shouldReturnSymbolsInOrder() {
        assertArrayEquals(new String[]{"red", "green", "yellow"}, machine.symbols());
    }
    
    @Test
    public void shouldNotChangeMachineWhenChangingReturnedSymbols() {
        String[] s = machine.symbols();
        s[0] = "green";
        assertEquals("red", machine.symbols()[0]);
    }
    
    //distinctSymbols()
    
    @Test
    public void shouldHaveDiferentSymbols() {
        machine.spin(2, 1);
        machine.spin(3, 2);
        assertEquals(3, machine.distinctSymbols());
    }
    
    @Test
    public void shouldNotHaveDiferentSymbols() {
        assertNotEquals(3, machine.distinctSymbols());
    }
    
    //configuration()
    
    @Test
    public void shouldReturnVisibleSymbols() {
        machine.spin(2, 1);
        assertArrayEquals(new String[]{"red", "green", "red"}, machine.configuration());
    }
    
    @Test
    public void shouldNotReturnSymbolsWithoutWheels() {
        SlotMachine empty = new SlotMachine();
        empty.addSymbol(1, "red");
        assertEquals(0, empty.configuration().length);
    }
    
    //isJackpot()
    
    public void shouldBeJackpot() {
        assertEquals(true, machine.isJackpot());
    }    
    
    public void shouldNotBeJackpot() {
        machine.spin(2, 1);
        assertEquals(false, machine.isJackpot());
    }
    
    //makeVisible()
    
    @Test
    public void shouldMakeVisibleKeepingConfiguration() {
        machine.makeVisible();
        assertArrayEquals(new String[]{"red", "red", "red"}, machine.configuration());
    }
    
    @Test
    public void shouldNotChangeSymbolsWhenMakingVisible() {
        machine.makeVisible();
        assertEquals(3, machine.symbols().length);
    }
    
    //makeInvisibe()
    
    @Test
    public void shouldMakeInvisibleKeepingConfiguration() {
        machine.makeInvisible();
        assertArrayEquals(new String[]{"red", "red", "red"}, machine.configuration());
    }

    @Test
    public void shouldNotDeleteWheelsWhenMakingInvisible() {
        machine.makeInvisible();
        assertEquals(3, machine.configuration().length);
    }
    
    //exit()
    
    @Test
    public void shouldExitKeepingSymbols() {
        machine.exit();
        assertEquals(3, machine.symbols().length);
    }

    @Test
    public void shouldNotChangeConfigurationWhenExiting() {
        machine.exit();
        assertArrayEquals(new String[]{"red", "red", "red"}, machine.configuration());
    }
    
    //ok()
    
    @Test
    public void shouldBeOkAfterValidOperation() {
        machine.addWheel(4);
        assertTrue(machine.ok());
    }

    @Test
    public void shouldNotBeOkAfterInvalidOperation() {
        machine.placeSymbol(1, "green");
        assertFalse(!machine.ok());
    }
    
    
    @After
    public void erase(){
        machine.makeInvisible();
    }
}