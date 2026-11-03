
/**
 * Write a description of class Shy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shy extends Symbol
{
    public Shy (String color, String type){
        super(color, type);
    }
    
    @Override
    public void selected() {
        visible = !visible;
    }
    
    @Override
    public Symbol copy() {
    return new Shy(getColor(), getType());
    }
}