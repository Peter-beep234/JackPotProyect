import java.util.ArrayList;
import java.util.Random;

public class Wheel{
    
    private ArrayList<String> symbols;
    private Random random;
    private boolean isVisible;
    private int position;
    public boolean locked;
    private Circle symbol;
    
    public Wheel(ArrayList<String> initialSymbols) {
        symbols = new ArrayList<String>(initialSymbols);
        position = 0;
        locked = false;
        isVisible = false;
        symbol = new Circle(symbols.get(position));
        symbol.draw();
    }
    
    public String getSymbol(){
        return symbols.get(position);
    }
    
    public void addSymbol(int pos, String symbol){
        symbols.add(pos, symbol);
    }
    
    public void delSymbol(String color){
        symbols.remove(color);
    }
    
    public void Spin(int steps){
        int n = symbols.size();
        position = ((position + steps) % n + n) % n;
    }
    
    public void place(String color) {
        for(int i=0; i < symbols.size(); i++)
            if(symbols.get(i) == color){
                position = i;
            }
    }
    
    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
    
    public boolean isLocked() {
        return locked;
    }
    
}