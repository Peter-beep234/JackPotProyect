import java.util.ArrayList;


/**
 * Write a description of class Lefty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lefty extends Wheel
{
    public Lefty (ArrayList<Symbol> initialSymbols, String type, ArrayList<Wheel> ruedas){
        super(initialSymbols, type);
        wheels = ruedas;
    }
    
    @Override
    public void Spin(int steps){
        int i = wheels.indexOf(this);
        if (i > 0) {
            place(wheels.get(i-1).getSymbol());
        } else {
            super.Spin(steps);
        }
    }
}