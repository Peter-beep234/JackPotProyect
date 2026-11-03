
/**
 * Write a description of class Symbol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Symbol {
    protected String color;
    protected int size;
    protected boolean visible;
    protected String type;
    protected Circle shape;

    public Symbol(String color, String newType) {
        this(color);
        type = newType;
    }
    
    public Symbol(String color) {
        this.color = color;
        size = 70;
        type = "normal";
        visible = true;
        shape = new Circle(color);
        shape.moveHorizontal(5);
        shape.moveVertical(5);
    }

    public String getColor() {
        return color;
    }
    
    public void changeColor(String color) {
        shape.changeColor(color);
    }
    
    public void makeVisible() {
        if (visible) {
            shape.makeVisible();
        } else {
            shape.makeInvisible();
        }
    }   

    public void makeInvisible() {
        shape.makeInvisible();
    }
    
    public String getType() {
        return type;
    }
    
    public int getSize() {
        return size;
    }
    
    public void changeSize() {
    }
    
    public Circle getShape() {
        return shape;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public void selected() {
        
    }
    
    public Symbol copy() {
    return new Symbol(color, type);
    }
}