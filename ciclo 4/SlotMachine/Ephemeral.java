
/**
 * Write a description of class Ephemeral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ephemeral extends Symbol
{
    private static final int MINIMO = 5;
    private static final int REDUCCION = 10;
    
    public Ephemeral (String color, String type){
        super(color, type);
    }
    
    @Override
    public void selected() {
        int antes = size;
        size = Math.max(MINIMO, size - REDUCCION);
        getShape().changeSize(size);
        getShape().moveVertical((antes-size) / 2);
    }
    
    @Override
    public Symbol copy() {
    return new Ephemeral(getColor(), getType());
    }
    
}