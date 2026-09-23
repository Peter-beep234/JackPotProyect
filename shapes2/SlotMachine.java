import java.util.ArrayList;
import java.util.Random;

public class SlotMachine {
    private ArrayList<Wheel> wheels;
    private ArrayList<String> symbols;
    private boolean isVisible;
    
    public SlotMachine() {
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<String>();
        isVisible = false;
    }

    public void addWheel(int pos) {     
        if(pos > wheels.size()){
            pos = wheels.size();
            wheels.add(pos, new Wheel(symbols));
        } else if(pos < 1){
            pos = 1;
            wheels.add(pos, new Wheel(symbols));
        }
        wheels.add(pos, new Wheel(symbols));
    }
    
    public void delWheel(int pos) {
        if(pos > 10 && pos < 0){
            wheels.remove(pos);
        }else{
            System.out.println("Maximo posicion 10 y minimo posicion 0");
        }
        wheels.remove(pos);
    }
    
    public void swap(int wheel1, int wheel2) {
        Wheel auxiliar = wheels.get(wheel1);
        wheels.set(wheel1 - 1, wheels.get(wheel2 - 1));
        wheels.set(wheel2 - 1, auxiliar);
    }
    
    public void look(int wheel) {
        Wheel rueda = wheels.get(wheel-1);
        rueda.lock();
        wheels.set(wheel-1, rueda);
    }
    
    public void unlook(int wheel) {
        Wheel rueda = wheels.get(wheel-1);
        rueda.unlock();
        wheels.set(wheel-1, rueda);
    }
    
    public void addSymbol(int pos, String color) {
        symbols.add(pos, color);
        for (Wheel w : wheels) {
            w.addSymbol(pos, color);
        }
    }

    public void delSymbol(String symbol) {
        symbols.remove(symbol);
        for (Wheel w : wheels) {
            w.delSymbol(symbol);
        }
    }

    public void placeSymbol(int wheel, String symbol) {
        Wheel rueda = this.wheels.get(wheel-1);
        rueda.place(symbol);
        wheels.set(wheel-1, rueda);
    }

    public void spin(int wheel) {
        if(wheels.get(wheel-1).isLocked() == true) {
            return;
        }
        Wheel rueda = wheels.get(wheel-1);
        rueda.Spin((int) (Math.random() * symbols.size()) + 1);
    }

    public void spin() {
        for(int i = 0; i < wheels.size();i++)
            if(wheels.get(i).isLocked() == true) {
                return;
            } else {
                Wheel rueda = wheels.get(i);
                rueda.Spin((int) (Math.random() * symbols.size()) + 1);
            }
    }
    
    public void spin(int wheel, int steps) {
        if(wheels.get(wheel-1).isLocked() == true) {
            return;
        }
        Wheel rueda = wheels.get(wheel-1);
        rueda.Spin(steps);
    }
    
    public void spin(String[] setSymbols) {
        for (int i = 0; i < setSymbols.length; i++) {
            wheels.get(i).place(setSymbols[i]);
        }
    }

    public String[] symbols() {
        String[] currentSymbols= new String[symbols.size()];
        for(int i = 0; i <  symbols.size();i++){
            currentSymbols[i] = symbols.get(i);
        }
        return currentSymbols;
    }

    public int distinctSymbols() {
        ArrayList<String> currentSymbols = new ArrayList<String>();
        String[] actualSymbols = configuration();
        for(int i = 0; i < actualSymbols.length; i++){
            currentSymbols.add(actualSymbols[i]);
        } 
        for (int i = 0; i < currentSymbols.size(); i++) {
            for (int j = i + 1; j < currentSymbols.size(); j++) {
                if (currentSymbols.get(i).equals(currentSymbols.get(j))) {
                    currentSymbols.remove(j);
                    j--;
                }
            }
        }
        return currentSymbols.size();
    }

    public String[] configuration() {
        String[] config = new String[wheels.size()];
        for (int i = 0; i < config.length; i++) {
            config[i] = wheels.get(i).getSymbol();
        }
        return config;
    }

    public boolean isJackpot() {
        if(distinctSymbols() == 1){;
        return true;
        }
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