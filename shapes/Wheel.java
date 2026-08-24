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
        
        Symbols.add("black");
        
    }
    
    public void addSymbol(String Symbol){
        this.Symbols.add(Symbol);
    }
    
    public void Spin(){
        int index = random.nextInt(Symbols.size());
        String ncolor = Symbols.get(index);
        this.Usymbol.changeColor(ncolor);
    }
}