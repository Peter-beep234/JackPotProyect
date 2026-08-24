import java.util.ArrayList;

public class Machine {

    private Rectangle superior;
    private Rectangle inferior;
    private int nWheels = 10;
    private ArrayList<Rectangle> separaciones;

    public Machine() {
        this.nWheels = nWheels;
        superior = new Rectangle();
        superior.changeColor("black");
        superior.changeSize(20, (80*(this.nWheels-1))+100);
        superior.makeVisible();
        superior.moveHorizontal(0);
        superior.moveVertical(100);
        
        
        inferior = new Rectangle(); 
        inferior.changeColor("black"); 
        inferior.changeSize(20, (80*(this.nWheels-1))+100);
        inferior.makeVisible();
        inferior.moveHorizontal(0); 
        inferior.moveVertical(180);
        
        separaciones = new ArrayList<Rectangle>();
        
        for (int i = 0; i < (nWheels + 1); i++){
            Rectangle separacion = new Rectangle();
            
            separacion.changeColor("black");   
            separacion.changeSize(60, 20);     
            separacion.moveHorizontal(0); 
            separacion.moveVertical(120);
            separacion.makeVisible();
            separaciones.add(separacion);
            
        }
        
        for (int i = 0; i < separaciones.size(); i++){
            Rectangle separacion = separaciones.get(i);
            separacion.moveHorizontal(80*i);
        }
    }
    
        public void erase() {
        superior.erase();
        inferior.erase();

        for (int i = 0; i < separaciones.size(); i++) {
            separaciones.get(i).erase();
        }
    }

    
    public void Win(){
        this.superior.changeColor("green");
        this.inferior.changeColor("green");
        for (int i = 0; i < this.separaciones.size(); i++){
            Rectangle separacion = separaciones.get(i);
            separacion.changeColor("green");
        }
    }
}