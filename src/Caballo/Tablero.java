package Caballo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author luisb
 */
public class Tablero {
    
    private int[][] tablero;
    private int n;
    private int caballos;
    
    private static final int[] X_MOVE = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVE = {1, 2, 2, 1, -1, -2, -2, -1};
    
    private ArrayList<Object> soluciones = new ArrayList<>();
    
    /**
     * Método constructor de la clase
     * 
     * @param tamaño 
     */
    public Tablero(int tamaño) {
        this.tablero = new int[tamaño][tamaño];
        this.n = tamaño;
        this.caballos = 0;
        
        initTablero();
    }
    
    public Tablero(int[][] tablero) {
        this.tablero = tablero;
        this.n = tablero.length;
        this.caballos = n*n;
    }
    
    public void solucionar(int x, int y) {
        // Introducimos el primer caballo en la posición pasada por paráemetro
        tablero[x][y] = 1;
        caballos++;
        
        if (!solucion(soluciones, tablero, caballos, x, y, X_MOVE, Y_MOVE)) {
            JOptionPane.showMessageDialog(null, "No hay solución, prueba otra casilla.");
        } 
        
        //print();
    }
    
    /**
     * Método que inicializa el tablero
     * 
     */
    private void initTablero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tablero[i][j] = 0;                
            }     
        }
    }
    
    /**
     * Método que verifica si es posible colocar un caballo en la posición del 
     * tablero pasada por parámetro
     * 
     * @param x
     * @param y
     * @return 
     */
    private boolean esPosible(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n && tablero[x][y] == 0);
    }
    
    /**
     * Método que verifica si existe una solución en la posición del tablero pasada
     * por parámetro
     * 
     * @param tablero
     * @param caballos
     * @param x
     * @param y
     * @param xMove
     * @param yMove
     * @return 
     */
    private boolean solucion(ArrayList<Object> soluciones, int[][] tablero, int caballos, int x, int y, int[] X_MOVE, int[] Y_MOVE) {
        int xSig, ySig;
        
        if (caballos == n*n) {
            //print(tablero);
            soluciones.add(copy(tablero));
            
            for (int i = 0; i < soluciones.size(); i++) {
                System.out.println("Solucion " + i + ": ");
                print((int[][]) soluciones.get(i));
            }
            
            System.out.println("//////////////////");
            
            return soluciones.size() == 3;
        }
        
        // Probamos todos los movimientos posibles
        for (int i = 0; i < 8; i++) {            
            // Realizamos el movimiento
            xSig = x + X_MOVE[i];
            ySig = y + Y_MOVE[i];
            
            // Verififcamos si es posible
            if (esPosible(xSig, ySig)) {
                tablero[xSig][ySig] = caballos + 1;
                
                if (solucion(soluciones, tablero, caballos + 1, xSig, ySig, X_MOVE, Y_MOVE)) {
                    return true;
                } else {
                    tablero[xSig][ySig] = 0;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Método que devuelve el contenido del atributo tablero
     * 
     * @param x
     * @param y
     * @return 
     */
    public int getValor(int x, int y) {
        return tablero[x][y];
    }
    
    public ArrayList getSoluciones() {
        return soluciones;
    }
    
    public void setSoluciones(ArrayList soluciones) {
        this.soluciones = soluciones;
    }
    
    public Tablero getSolucion(int num) {  
        System.out.println("Solucion tablero:");
        print((int[][])soluciones.get(num));
        return new Tablero((int[][])soluciones.get(num));
    }
    
    private int[][] copy(int[][] a) {
        int[][] b = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[i][j];
            }
        }
        
        return b;
    }
    
    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tablero[i][j] + " ");
                
            }
            System.out.println("");
        }
    }
    
    public void print(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
                
            }
            System.out.println("");
        }
    }
}
