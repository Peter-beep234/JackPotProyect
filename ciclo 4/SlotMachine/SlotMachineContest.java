import java.util.*;


/**
 * Write a description of class SlotMachineContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachineContest
{
    private static boolean simular = false;
    public static final int COLORES = 5;
    
    public static int[][] solve(int n)
    {
        ArrayList<int[]> acciones = new ArrayList<int[]>();
        if(n == 0 || n > COLORES){
            return new int [0][0];
        }
        SlotMachine maquina = new SlotMachine(n);
        if (simular) {
            maquina.makeVisible();
        } else {
            maquina.makeInvisible();
        }
        if (n > 1 && maquina.distinctSymbols() > 1) {
            for (int rueda = 1; rueda <= n && maquina.distinctSymbols() < n; rueda++) {
                int mejor = 0;
                int maximo = maquina.distinctSymbols();
                for (int i = 1; i < n; i++) {
                    maquina.spin(rueda, 1);
                    acciones.add(new int[]{rueda, 1});
                    if (maquina.distinctSymbols() > maximo) {
                        maximo = maquina.distinctSymbols();
                        mejor = i;
                    }
                }
                int pasos = (mejor + 1) % n;
                if (pasos > 0) {
                    maquina.spin(rueda, pasos);
                    acciones.add(new int[]{rueda, pasos});
                }
            }
            maquina.spin(1, 1);
            acciones.add(new int[]{1, 1});
            int[] objetivo = new int[n + 1];
            for (int rueda = 2; rueda <= n; rueda++) {
                int maximo = -1;
                for (int p = 1; p <= n; p++) {
                    maquina.spin(rueda, 1);
                    acciones.add(new int[]{rueda, 1});
                    if (p < n && maquina.distinctSymbols() > maximo) {
                        maximo = maquina.distinctSymbols();
                        objetivo[rueda] = p;
                    }
                }
            }
            maquina.spin(1, n - 1);
            acciones.add(new int[]{1, n - 1});
            for (int rueda = 2; rueda <= n; rueda++) {
                maquina.spin(rueda, objetivo[rueda]);
                acciones.add(new int[]{rueda, objetivo[rueda]});
            }
        }
        return acciones.toArray(new int[0][]);
    }
    public static void simulate(int n) {
        simular = true;
        solve(n);
        simular = false;
    }
}