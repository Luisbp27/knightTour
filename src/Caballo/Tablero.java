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
    
    /**
     * Método constructor de la clase
     * 
     * @param tablero 
     */
    public Tablero(int[][] tablero) {
        this.tablero = tablero;
        this.n = tablero.length;
        this.caballos = n*n;
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
     * Método que soluciona el juego
     * 
     * @param x
     * @param y 
     */
    public void solucionar(int x, int y) {
        // Introducimos el primer caballo en la posición pasada por paráemetro
        tablero[x][y] = 1;
        caballos++;
        
        // Llamamos al método recursivo
        if (!solucion(soluciones, tablero, caballos, x, y, X_MOVE, Y_MOVE)) {
            JOptionPane.showMessageDialog(null, "No hay solución, prueba otra casilla.");
            tablero[x][y] = 0;
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
        
        // Si rellena todo el tablero, es decir, tiene una posible solución
        if (caballos == n*n) {
            // Añadimos la solución al ArrayList de soluciones
            soluciones.add(copy(tablero));
            
            // Comprobamos si tiene las 3 soluciones que queremos
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
     * Método que devuelve la solución del indice pasado por parámetro del
     * ArrayList de soluciones
     * 
     * @param num
     * @return 
     */
    public Tablero getSolucion(int num) {  
        return new Tablero((int[][])soluciones.get(num));
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
    
    /**
     * Método que devuelve el contenido del atributo soluciones
     * 
     * @return 
     */
    public ArrayList<Object> getSoluciones() {
        return soluciones;
    }
    
    /**
     * Método que modifica el contenido del atributo soluciones por la variable
     * pasada por parámetro
     * 
     * @param soluciones 
     */
    public void setSoluciones(ArrayList<Object> soluciones) {
        this.soluciones = soluciones;
    }
    
    /**
     * Método que devuelve una copia de la matriz de enteros pasada por parámetro
     * 
     * @param a
     * @return 
     */
    private int[][] copy(int[][] a) {
        int[][] b = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[i][j];
            }
        }
        
        return b;
    }
}
