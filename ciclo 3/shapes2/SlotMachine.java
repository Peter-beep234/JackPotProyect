import java.util.ArrayList;
import java.util.Random;

public class SlotMachine {
    private ArrayList<Wheel> wheels;
    private ArrayList<String> symbols;
    private boolean isVisible;
    public Rectangle fondo1;
    public Rectangle fondo2;
    public int posicion;
    
    public SlotMachine() {
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<String>();
        isVisible = false;
        posicion = 0;
        fondo1 = new Rectangle();
        fondo2 = new Rectangle();
        fondo2.changeSize(80, 80);
        fondo2.moveHorizontal(10);
        fondo2.moveVertical(10);
        fondo2.changeColor("white");
    }
    
    public SlotMachine(int n) {
        wheels = new ArrayList<Wheel>();
        symbols = new ArrayList<String>();
        posicion = 0;
        fondo1 = new Rectangle();
        fondo2 = new Rectangle();
        fondo2.changeSize(80, 80);
        fondo2.moveHorizontal(10);
        fondo2.moveVertical(10);
        fondo2.changeColor("white");
        String[] colores = {"red", "green", "yellow", "blue", "magenta"};
        for(int i = 0; i < n; i++){
            addSymbol(i + 1, colores[i]);
        }
        
        for(int i = 0; i < n; i++){
            addWheel(i);
        }
        actualizar();
    }
    
    public void addWheel(int pos) {
        if (symbols.size() < 1){
            System.out.println("no se puede añadir una rueda sin simbolos");
            actualizar();
        } else {
            if (pos < 1) {
                pos = 1;
            }
            
            if (pos > wheels.size() + 1) {
                pos = wheels.size() + 1;
            }
            wheels.add(pos - 1, new Wheel(symbols));
            spin(pos);
            actualizar();
        }
    }
    
    public void delWheel(int pos) {
        if(pos > wheels.size() && pos < 0){
            System.out.println("Maximo posicion" + wheels.size() + "y minimo posicion 1");
        }else{
            wheels.get(pos - 1).makeInvisible();
            wheels.remove(pos-1);
        }
        actualizar();
    }
    
    public void swap(int wheel1, int wheel2) {
        if(wheel1 == wheel2){
            return;
        } 
        Wheel auxiliar = wheels.get(wheel1 - 1);
        wheels.set(wheel1 - 1, wheels.get(wheel2 - 1));
        wheels.set(wheel2 - 1, auxiliar);
        actualizar();
    }
    
    public void lock(int wheel) {
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
        for(int i = 0; i < symbols.size(); i++){
            if (symbols.get(i) == color){
                System.out.println("el symbolo ya esta en la maquina");
                return;
            }
        }
        symbols.add(pos - 1, color);
        for (Wheel w : wheels) {
            w.addSymbol(pos - 1, color);
        }
    }

    public void delSymbol(String symbol) {
        for (Wheel w : wheels) {
            w.delSymbol(symbol);
        }
        symbols.remove(symbol);
        actualizar();
    }

    public void placeSymbol(int wheel, String symbol) {
        wheels.get(wheel-1).place(symbol);       
        actualizar();
    }

    public void spin(int wheel) {
        if(wheels.get(wheel-1).isLocked() == true) {
            return;
        }
        Wheel rueda = wheels.get(wheel-1);
        rueda.Spin((int) (Math.random() * symbols.size()) + 1);
        actualizar();
    }

    public void spin() {
        for(int i = 0; i < wheels.size();i++)
            if(wheels.get(i).isLocked() == true) {
                continue;
            } else {
                Wheel rueda = wheels.get(i);
                rueda.Spin((int) (Math.random() * symbols.size()) + 1);
            }
        actualizar();
    }
    
    public void spin(int wheel, int steps) {
        if(wheels.get(wheel-1).isLocked() == true) {
            return;
        }
        Wheel rueda = wheels.get(wheel-1);
        for (int i = 0; i < steps;i++){
            rueda.Spin(1);
            actualizar();
        }
    }
    
    public void spin(String[] setSymbols) {
        for (int i = 0; i < setSymbols.length; i++) {
            wheels.get(i).place(setSymbols[i]);
        }
        actualizar();
    }

    public String[] symbols() {
        String[] currentSymbols = new String[symbols.size()];
        for(int i = 0; i <  symbols.size(); i++){
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
        if(distinctSymbols() == 1){
            return true;
        } else {
            return false;
        }
    }

    public void makeVisible() {
        fondo1.makeVisible();
        fondo2.makeVisible();
        for(Wheel w: wheels){
            w.makeVisible();
        }
        isVisible = true;
    }

    public void makeInvisible() {
        fondo1.makeInvisible();
        fondo2.makeInvisible();
        for(Wheel w: wheels){
            w.makeInvisible();
        }
        isVisible = false;
    }

    public void exit() {
        makeInvisible();
    }

    public boolean ok() {
        return true;
    }
    
    public ArrayList<Wheel> getWheels(){
        return wheels;
    }
    
    public void actualizar() {
        int n = Math.max(wheels.size(), 1);
        int anchoFondo1 = n * 100;
        fondo1.changeSize(100, anchoFondo1);
        fondo2.changeSize(80, anchoFondo1 - 20);
        for (int i = 0; i < wheels.size(); i++) {
            posicion = i * 100;
            wheels.get(i).symbol.moveHorizontalTo(posicion);
        }
        if(isVisible){
            if (isJackpot() == true || distinctSymbols() == 1) {
                fondo1.changeColor("green");
            } else {
                fondo1.changeColor("black");
            }
            makeVisible();
        }
    }
}