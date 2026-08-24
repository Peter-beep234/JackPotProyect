import java.util.ArrayList;

public class Symbol{
    
    private Circle symbol;
    private String color;
    private int position;

    public Symbol(int position, String color){ 
        symbol = new Circle();
        symbol.changeColor(color);
        symbol.makeVisible();
        symbol.changeSize(50);
        symbol.moveHorizontal(75 + ((position-1) * 80)); 
        symbol.moveVertical(125);
    }
    
    public void changeColor(String color){
        symbol.changeColor(color);
    }
}