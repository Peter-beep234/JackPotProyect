import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author  (RodriguezH - SarmientoM)
 * @version (ciclo 4)
 */
public class SlotMachineC4Test
{
    private SlotMachine machine;
    private ArrayList<Symbol> simbolos;

    @Before
    public void setUp() {
        machine = new SlotMachine();
        machine.addSymbol(1, "red");
        machine.addSymbol(2, "green");
        machine.addSymbol(3, "yellow");
        simbolos = new ArrayList<Symbol>();
        simbolos.add(new Symbol("red"));
        simbolos.add(new Symbol("green"));
        simbolos.add(new Symbol("yellow"));
    }

    private int posicionRestLess() {
        for (int i = 0; i < machine.getWheels().size(); i++) {
            if (machine.getWheels().get(i) instanceof RestLess) {
                return i + 1;
            }
        }
        return -1;
    }

    @Test
    public void shouldAddWheelsOfEveryType() {
        machine.addWheel("normal", 1);
        machine.addWheel("lefty", 2);
        machine.addWheel("rebel", 3);
        machine.addWheel("saltarina", 4);
        assertEquals(4, machine.configuration().length);
        assertTrue(machine.getWheels().get(1) instanceof Lefty);
        assertTrue(machine.getWheels().get(2) instanceof Rebel || machine.getWheels().get(3) instanceof Rebel);
        assertTrue(posicionRestLess() > 0);
    }

    @Test
    public void shouldLockNormalWheel() {
        machine.addWheel("normal", 1);
        machine.lock(1);
        assertTrue(machine.getWheels().get(0).isLocked());
    }

    @Test
    public void shouldNotLockRebel() {
        machine.addWheel("rebel", 1);
        machine.lock(1);
        assertFalse(machine.getWheels().get(0).isLocked());
    }

    @Test
    public void shouldDeleteNormalWheel() {
        machine.addWheel("normal", 1);
        machine.addWheel("normal", 2);
        machine.delWheel(2);
        assertEquals(1, machine.configuration().length);
    }

    @Test
    public void shouldNotDeleteRebel() {
        machine.addWheel("normal", 1);
        machine.addWheel("rebel", 2);
        machine.delWheel(2);
        assertEquals(2, machine.configuration().length);
    }

    @Test
    public void shouldSwapNormalWheels() {
        machine.addWheel("normal", 1);
        machine.addWheel("normal", 2);
        Wheel primera = machine.getWheels().get(0);
        machine.swap(1, 2);
        assertSame(primera, machine.getWheels().get(1));
    }

    @Test
    public void shouldNotSwapRebel() {
        machine.addWheel("normal", 1);
        machine.addWheel("rebel", 2);
        Wheel primera = machine.getWheels().get(0);
        machine.swap(1, 2);
        assertSame(primera, machine.getWheels().get(0));
    }

    @Test
    public void shouldCopyLeftWheelWhenLeftySpins() {
        machine.addWheel("normal", 1);
        machine.addWheel("lefty", 2);
        for (int i = 0; i < 10; i++) {
            machine.spin(1);
            machine.spin(2, 1);
            assertEquals(machine.configuration()[0], machine.configuration()[1]);
        }
    }

    @Test
    public void shouldSpinNormallyWhenLeftyIsFirst() {
        machine.addWheel("lefty", 1);
        String antes = machine.configuration()[0];
        machine.spin(1, 1);
        assertNotEquals(antes, machine.configuration()[0]);
    }

    @Test
    public void shouldMoveRestLessWhenItSpins() {
        machine.addWheel(1);
        machine.addWheel("saltarina", 2);
        assertEquals(1, posicionRestLess());
        machine.spin(posicionRestLess(), 1);
        assertEquals(2, posicionRestLess());
        machine.spin(posicionRestLess(), 1);
        assertEquals(1, posicionRestLess());
    }

    @Test
    public void shouldNotMoveRestLessWhenItIsAlone() {
        machine.addWheel("saltarina", 1);
        machine.spin(1, 1);
        assertEquals(1, machine.configuration().length);
        assertEquals(1, posicionRestLess());
    }

    @Test
    public void shouldNotMoveRestLessOverARebel() {
        machine.addWheel("rebel", 1);
        machine.addWheel("saltarina", 2);
        machine.spin(posicionRestLess(), 1);
        assertEquals(2, posicionRestLess());
        assertTrue(machine.getWheels().get(0) instanceof Rebel);
    }

    @Test
    public void shouldKeepWorkingWhenSpinningAllWithRestLess() {
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel("saltarina", 2);
        for (int i = 0; i < 20; i++) {
            machine.spin();
        }
        assertEquals(3, machine.configuration().length);
    }

    @Test
    public void shouldGiveEachWheelItsOwnSymbols() {
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addSymbol("ephemeral", 4, "blue");
        Symbol enRueda1 = machine.getWheels().get(0).getSymbols().get(3);
        Symbol enRueda2 = machine.getWheels().get(1).getSymbols().get(3);
        assertNotSame(enRueda1, enRueda2);
        assertTrue(enRueda1 instanceof Ephemeral);
    }

    @Test
    public void shouldDeleteSymbolFromTheMachine() {
        machine.addWheel(1);
        machine.delSymbol("green");
        assertArrayEquals(new String[]{"red", "yellow"}, machine.symbols());
    }

    @Test
    public void shouldShrinkEphemeralWhenWheelLandsOnIt() {
        simbolos.add(1, new Ephemeral("blue", "ephemeral"));
        Wheel w = new Wheel(simbolos);
        w.Spin(1);
        assertEquals("blue", w.getSymbol());
        assertEquals(60, w.getSymbols().get(1).getSize());
    }

    @Test
    public void shouldNotShrinkEphemeralBelowAPoint() {
        Symbol e = new Ephemeral("blue", "ephemeral");
        for (int i = 0; i < 20; i++) {
            e.selected();
        }
        assertEquals(5, e.getSize());
    }

    @Test
    public void shouldNotShrinkNormalSymbol() {
        Wheel w = new Wheel(simbolos);
        for (int i = 0; i < 6; i++) {
            w.Spin(1);
        }
        assertEquals(70, w.getSymbols().get(0).getSize());
    }

    @Test
    public void shouldNotShrinkEphemeralInOtherWheels() {
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addSymbol("ephemeral", 4, "blue");
        int antes = machine.getWheels().get(1).getSymbols().get(3).getSize();
        machine.placeSymbol(1, "yellow");
        machine.spin(1, 1);
        assertEquals(antes, machine.getWheels().get(1).getSymbols().get(3).getSize());
    }

    @Test
    public void shouldHideShyWhenSelected() {
        simbolos.add(1, new Shy("blue", "shy"));
        Wheel w = new Wheel(simbolos);
        w.Spin(1);
        assertEquals("blue", w.getSymbol());
        assertFalse(w.getSymbols().get(1).isVisible());
    }

    @Test
    public void shouldShowShyWhenSelectedAgain() {
        simbolos.add(1, new Shy("blue", "shy"));
        Wheel w = new Wheel(simbolos);
        w.Spin(1);
        w.Spin(4);
        assertTrue(w.getSymbols().get(1).isVisible());
    }

    @Test
    public void shouldNotRemoveShyFromConfiguration() {
        machine.addWheel(1);
        machine.addSymbol("shy", 4, "blue");
        machine.placeSymbol(1, "blue");
        assertEquals("blue", machine.configuration()[0]);
    }
}
