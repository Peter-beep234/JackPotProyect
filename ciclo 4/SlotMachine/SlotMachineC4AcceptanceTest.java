import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Pruebas de aceptacion del ciclo 4 para la presentacion (modo visible).
 * Muestran la maquina en pantalla, hacen los movimientos con una pausa
 * para que se puedan ver, y revisan solas que el resultado sea el correcto.
 *
 * @author  (RodriguezH - SarmientoM)
 * @version (ciclo 4)
 */
public class SlotMachineC4AcceptanceTest
{
    private static final int PAUSA = 1500;
    private void pausa() {
        try {
            Thread.sleep(PAUSA);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Tipos de rueda. Orden: normal, lefty, restless, normal.
     * La lefty (rueda 2) copia el color de la rueda 1 y la restless (rueda 3)
     * se cambia de lugar con la rueda 4 cuando gira.
     */
    @Test
    public void shouldShowLeftyAndRestLess() {
        SlotMachine machine = new SlotMachine();
        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "yellow");
        machine.addSymbol(4, "green");
        machine.addWheel("normal", 1);
        machine.addWheel("lefty", 2);
        machine.addWheel("normal", 3);
        machine.addWheel("saltarina", 4);
        machine.spin(new String[]{"red", "red", "blue", "green"});
        machine.makeVisible();
        pausa();
        machine.spin(1, 1);
        pausa();
        machine.spin(2, 1);
        pausa();
        assertEquals("blue", machine.configuration()[0]);
        assertEquals("blue", machine.configuration()[1]);

        machine.spin(3, 1);
        pausa();
        assertTrue(machine.getWheels().get(3) instanceof RestLess);
        assertEquals("green", machine.configuration()[2]);
        machine.makeInvisible();
    }

    /**
     * Tipos de simbolo: el efimero (amarillo) se encoge cada vez que la rueda 1
     * cae en el; el timido (azul) se esconde cuando la rueda 2 lo selecciona.
     */
    @Test
    public void shouldShowEphemeralAndShy() {
        SlotMachine machine = new SlotMachine();
        machine.addSymbol(1, "red");
        machine.addWheel(1);
        machine.addWheel(2);
        machine.addSymbol("ephemeral", 2, "yellow");
        machine.addSymbol("shy", 3, "blue");
        machine.makeVisible();
        pausa();
        for (int i = 0; i < 3; i++) {
            machine.placeSymbol(1, "red");
            machine.spin(1, 1);
            pausa();
        }
        assertEquals("yellow", machine.configuration()[0]);
        assertEquals(40, machine.getWheels().get(0).getSymbols().get(1).getSize());

        machine.placeSymbol(2, "yellow");
        machine.spin(2, 1);
        pausa();
        assertEquals("blue", machine.configuration()[1]);
        assertFalse(machine.getWheels().get(1).getSymbols().get(2).isVisible());
        machine.makeInvisible();
    }
}
