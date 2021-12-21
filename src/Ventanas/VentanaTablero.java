package Ventanas;

import Caballo.Tablero;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author luisb
 */
public class VentanaTablero extends JFrame {

    private final int tamaño;
    private final Tablero tablero;
    private final boolean solucionar;

    private JButton[][] casillas;
    private ImageIcon imagen;
    private static final int CASILLA = 80;
    
    private static final JMenuBar barraMenu = new JMenuBar();
    private static final JMenu solucion = new JMenu();
    private static final JMenuItem solucion1 = new JMenuItem();
    private static final JMenuItem solucion2 = new JMenuItem();
    private static final JMenuItem solucion3 = new JMenuItem();

    /**
     * Método constructor de la clase
     * 
     * @param n 
     */
    public VentanaTablero(int n) {
        super("Juego del Caballo");

        this.tamaño = n;
        this.tablero = new Tablero(tamaño);
        this.solucionar = false;

        initComponents();
    }

    /**
     * Método constructor de la clase
     * 
     * @param tablero
     * @param n 
     */
    public VentanaTablero(Tablero tablero, int n) {
        super("Juego del Caballo");

        this.tamaño = n;
        this.tablero = tablero;
        this.solucionar = true;

        initComponents();
    }

    /**
     * Método que inicializa y gestiona los componentes de la ventana
     * 
     */
    private void initComponents() {
        this.setSize(tamaño * CASILLA + 16, tamaño * CASILLA + 42);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(tamaño, tamaño));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        casillas = new JButton[tamaño][tamaño];
        initTablero();

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j].addActionListener((ActionEvent ae) -> {
                    JButton boton = (JButton) ae.getSource();
                    int x = boton.getX() / 78;
                    int y = boton.getY() / 75;

                    solucionTablero(x, y);
                });
            }
        }
        
        solucion1.setText("Solución 1");
        solucion1.addActionListener((ActionEvent e) -> {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        
        solucion2.setText("Solución 2");
        solucion2.addActionListener((ActionEvent e) -> {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        
        solucion3.setText("Solución 3");
        solucion3.addActionListener((ActionEvent e) -> {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        
        solucion.setText("Soluciones");
        solucion.add(solucion1);
        solucion.add(solucion2);
        solucion.add(solucion3);
        
        barraMenu.add(solucion);
        
        this.setJMenuBar(barraMenu);
    }

    /**
     * Método que inicializa el tablero
     * 
     */
    private void initTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new JButton();
                
                if (solucionar && tablero.getValor(i, j) > 0) {
                    casillas[i][j].setText(String.valueOf(tablero.getValor(i, j)));
                }
                // Blanco par, negro impar
                else if ((i + j) % 2 == 0) {
                    imagen = new ImageIcon("imagenes/white.jpg");
                    casillas[i][j].setIcon(imagen);

                } else {
                    imagen = new ImageIcon("imagenes/black.jpg");
                    casillas[i][j].setIcon(imagen);
                }
            }
        }

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                this.add(casillas[i][j]);
            }
        }
    }

    /**
     * Método que crea un tablero y una solución a partir de la casilla pasada
     * por parámetro
     *
     * @param x
     * @param y
     */
    private void solucionTablero(int x, int y) {
        this.setVisible(false);

        VentanaTablero ventanaTablero = new VentanaTablero(new Tablero(tamaño, y, x), tamaño);

        ventanaTablero.setVisible(true);
    }
}
