package Caballo;

import javax.swing.JOptionPane;

/**
 *
 * @author luisb
 */
public class Tablero {
    
    private final int[][] tablero;
    private final int n;
    private int caballos;
    
    private static final int[] X_MOVE = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVE = {1, 2, 2, 1, -1, -2, -2, -1};
    
    private Object[] soluciones = new Object[3];
    private int numSol;
    
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
     * Método constructor de la clase que crea una solución a partir de una
     * posición pasada por parámetro
     * 
     * @param tamaño
     * @param x
     * @param y 
     */
    public Tablero(int tamaño, int x, int y) {
        this.tablero = new int[tamaño][tamaño];
        this.n = tamaño;
        this.caballos = 1;
        this.numSol = 0;
        
        initTablero();
        // Introducimos el primer caballo en la posición pasada por paráemetro
        tablero[x][y] = 1;
        
        if (!solucion(tablero, caballos, x, y, X_MOVE, Y_MOVE)) {
            JOptionPane.showMessageDialog(null, "No hay solución, prueba otra casilla.");
        } 
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
    private boolean solucion(int[][] tablero, int caballos, int x, int y, int[] X_MOVE, int[] Y_MOVE) {
        int xSig, ySig;
        
        if (caballos == n*n) {
            return true;
        }
        
        // Probamos todos los movimientos posibles
        for (int i = 0; i < 8; i++) {
            // Realizamos el movimiento
            xSig = x + X_MOVE[i];
            ySig = y + Y_MOVE[i];
            
            // Verififcamos si es posible
            if (esPosible(xSig, ySig)) {
                tablero[xSig][ySig] = caballos + 1;
                
                if (solucion(tablero, caballos + 1, xSig, ySig, X_MOVE, Y_MOVE)) {
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
}
