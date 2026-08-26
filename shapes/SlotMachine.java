import java.util.ArrayList;

public class SlotMachine {

    private Machine machine;
    private ArrayList<Wheel> wheels;

    public SlotMachine() {
        this.machine = new Machine();
        this.wheels = new ArrayList<Wheel>();
    }

    public void addWheel(int pos) {         
        if(pos < 10 || pos > 0){
            this.wheels.add(new Wheel(pos));
        }else{
            System.out.println("Maximo posicion 10 y minimo posicion 0");
        }
    }
    
    public void delWheel(int pos) {
        if(pos < 10 || pos > 0){
            this.wheels.get(pos-1).delWheel();;
        }else{
            System.out.println("Maximo posicion 10 y minimo posicion 0");
        }
    }

    public void addSymbol(int pos, String color) {
        for (int i = 0; i < wheels.size(); i++){
            this.wheels.get(i).addSymbol(pos, color);
        }
    }

    public void delSymbol(int pos, String symbol) {
        for (int i = 0; i < wheels.size(); i++){
            this.wheels.get(i).delSymbol(symbol);
        }
    }

    public void placeSymbol(int wheel, String symbol) {
        
    }

    public void spinWheel(int wheel) {
        this.wheels.get(wheel).Spin();
    }

    public void spin() {
        for (int i = 0; i < wheels.size(); i++){
            this.wheels.get(i).Spin();
        }
    }

    public String[] symbols() {
        return null;
    }

    public int distinctSymbols() {
        return 0;
    }

    public String[] configuration() {
        return null;
    }

    public boolean jackpot() {
        return false;
    }

    public void makeVisible() {
    }

    public void makeInvisible() {
    }

    public void exit() {
    }

    public boolean ok() {
        return false;
    }
}