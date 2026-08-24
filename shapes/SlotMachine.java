import java.util.ArrayList;

public class SlotMachine {

    private Machine machine;
    private ArrayList<Wheel> wheels;

    public SlotMachine() {
        this.machine = new Machine();
        this.wheels = new ArrayList<Wheel>();
        this.wheels.add(new Wheel(1));
    }

    public void addWheel(int pos) {
        this.wheels.add(new Wheel(pos));
    }

    public void addSymbol(int pos, String color) {
    }

    public void delSymbol(String symbol) {
    }

    public void placeSymbol(int wheel, String symbol) {
    }

    public void spinWheel(int wheel) {
    }

    public void spin() {
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