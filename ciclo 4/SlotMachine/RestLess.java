import java.util.ArrayList;

/**
 * Write a description of class RestLess here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RestLess extends Wheel
{
    private SlotMachine maquina;
    
    public RestLess (ArrayList<Symbol> initialSymbols, String type, ArrayList<Wheel> ruedas, SlotMachine maquina){
        super(initialSymbols, type);
        wheels = ruedas;
        this.maquina = maquina;
    }
    
    @Override
    public void Spin(int steps){
        int n = symbols.size();
        position = ((position + steps) % n + n) % n;
        int tamaño = wheels.size();
        if (tamaño > 1) {
            int index = wheels.indexOf(this);
            int suma = 1;
            if (index + suma >= tamaño) {
                suma = -1;
            } else if (index + suma <= 0) {
                suma = 1;
            }
            maquina.swap(index + 1, index + suma + 1);
        }
    }
}