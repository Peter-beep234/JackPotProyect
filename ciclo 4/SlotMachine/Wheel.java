import java.util.ArrayList;
import java.util.Random;

public class Wheel{
    protected ArrayList<Wheel> wheels;
    protected ArrayList<Symbol> symbols;
    protected Random random;
    protected boolean isVisible;
    protected int position;
    protected String type;
    protected boolean locked;
    
    public Wheel(ArrayList<Symbol> initialSymbols, String newType){
        this(initialSymbols);
        type = type;
    }
    
    public Wheel(ArrayList<Symbol> initialSymbols) {
        position = 0;
        locked = false;
        isVisible = false;
        type = "normal";
        symbols = new ArrayList<Symbol>();
        for (Symbol s : initialSymbols) {
        symbols.add(s.copy());
        }
    }
    
    public int getPos(){
        return position;
    }
    
    public ArrayList<Symbol> getSymbols(){
        return symbols;
    }
    
    public String getSymbol(){
        return symbols.get(position).getColor();
    }
    
    public void addSymbol(int pos, String symbol){
        symbols.add(pos, new Symbol(symbol));
    }
    
    public void delSymbol(String color){
        if(position == symbols.size() - 1 && symbols.size() > 1){
            position -= 1;
        }
        for(int i = 0; i < symbols.size(); i++){
            if(symbols.get(i).getColor().equals(color)){
                symbols.remove(i);
            }
        }
               
    }
    
    public void Spin(int steps){
        int n = symbols.size();
        position = ((position + steps) % n + n) % n;
        if (symbols.get(position).getType().equals("ephemeral")) {
            symbols.get(position).changeColor(symbols.get(position).getColor());
            symbols.get(position).selected();
        } else if (symbols.get(position).getType().equals("shy")) {
            symbols.get(position).changeColor(symbols.get(position).getColor());
            symbols.get(position).selected();
        }
    }
    
    public void place(String color) {
        for(int i = 0; i < symbols.size(); i++)
            if(symbols.get(i).getColor().equals(color)){
                position = i;
                symbols.get(position).changeColor(symbols.get(position).getColor());
            }
        
    }
    
    public String getType(){
        return type;
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
        symbols.get(position).changeColor(symbols.get(position).getColor());
        symbols.get(position).makeVisible();
        isVisible = true;
    }
    
    public void makeInvisible() {
        symbols.get(position).makeInvisible();
        isVisible = false;
    }
    
    public boolean canLock() {
        return true;
    }

    public boolean canSwap() {
        return true;
    }

    public boolean canDelete() {
        return true;
    }

    public Circle getShape() {
        return symbols.get(position).getShape();
    }
    
    
}