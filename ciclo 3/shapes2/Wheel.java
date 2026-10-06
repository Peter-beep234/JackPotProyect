import java.util.ArrayList;
import java.util.Random;

public class Wheel{
    
    private ArrayList<String> symbols;
    private Random random;
    private boolean isVisible;
    private int position;
    public boolean locked;
    public Circle symbol;
    
    public Wheel(ArrayList<String> initialSymbols) {
        symbols = new ArrayList<String>(initialSymbols);
        position = 0;
        locked = false;
        isVisible = false;
        symbol = new Circle(symbols.get(position));
        symbol.moveHorizontal(5);
        symbol.moveVertical(5);
    }
    
    public int getPos(){
        return position;
    }
    
    public String getSymbol(){
        return symbols.get(position);
    }
    
    public void addSymbol(int pos, String symbol){
        symbols.add(pos, symbol);
    }
    
    public void delSymbol(String color){
        if(position == symbols.size() - 1 && symbols.size() > 1){
            position -= 1;
        }
        symbols.remove(color);
    }
    
    public void Spin(int steps){
        int n = symbols.size();
        position = ((position + steps) % n + n) % n;
        symbol.changeColor(symbols.get(position));
    }
    
    public void place(String color) {
        for(int i = 0; i < symbols.size(); i++)
            if(symbols.get(i).equals(color)){
                position = i;
                symbol.changeColor(symbols.get(position));
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
    
    public void makeVisible() {
        symbol.changeColor(symbols.get(position));
        symbol.makeVisible();
        isVisible = true;
    }
    
    public void makeInvisible() {
        symbol.makeInvisible();
        isVisible = false;
    }
}