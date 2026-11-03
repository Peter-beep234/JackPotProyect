import java.util.ArrayList;


/**
 * Write a description of class rebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rebel extends Wheel
{
    public Rebel(ArrayList<Symbol> initialSymbols, String type){
        super(initialSymbols, type);
    }
    
    public boolean canLock() {
        return false;
    }

    public boolean canSwap() {
        return false;
    }

    public boolean canDelete() {
        return false;
    }
}