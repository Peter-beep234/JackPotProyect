import java.util.ArrayList;
import java.util.Random;

public class Wheel{
    
    private ArrayList<String> Symbols;
    
    private Random random = new Random();
    private Symbol Usymbol;
    private int position;
    
    public Wheel(int position){
        Usymbol = new Symbol(position, "black");
        
        Symbols = new ArrayList<String>();
        
        for (int i = 0; i < 8; i++){
            Symbols.add(" ");
        }
    }
    
    public void addSymbol(int pos, String Symbol){
        this.Symbols.set(pos, Symbol);
    }
    
    public void delSymbol(String color){
        this.Symbols.remove(color);
    }
    
    public void Spin(){
        int index = random.nextInt(Symbols.size());
        String ncolor = Symbols.get(index);
        this.Usymbol.changeColor(ncolor);
    }
    
    public void delWheel(){
        Symbols.clear();
        Usymbol.erase();
    }
}