package Ventanas;

import Caballo.Tablero;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    private Tablero tablero;
    private final boolean solucionar;

    private JButton[][] casillas;
    private ImageIcon imagen;
    private static final int CASILLA = 80;

    private JMenuBar barraMenu = new JMenuBar();
    private JMenu solucion = new JMenu();
    private JMenuItem solucion1 = new JMenuItem();
    private JMenuItem solucion2 = new JMenuItem();
    private JMenuItem solucion3 = new JMenuItem();

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

        // Creamos una matriz de botones
        casillas = new JButton[tamaño][tamaño];
        initTablero();

        // Listener que nos permite registrar que boton ha sido pulsado
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

        // Gestion de los diferentes items de la barra de menu
        solucion1.setText("Solución 1");
        solucion1.addActionListener((ActionEvent e) -> {
            mostrarSolucion(0);
        });

        solucion2.setText("Solución 2");
        solucion2.addActionListener((ActionEvent e) -> {
            mostrarSolucion(1);
        });

        solucion3.setText("Solución 3");
        solucion3.addActionListener((ActionEvent e) -> {
            mostrarSolucion(2);
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
                } // Blanco par, negro impar
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

        Tablero t = new Tablero(tamaño);
        t.solucionar(x, y);
        VentanaTablero ventanaTablero = new VentanaTablero(t, tamaño);

        ventanaTablero.setVisible(true);
    }

    /**
     * Método que muestra la solución correspondiente al entero pasado por parámetro
     * del ArrayList de soluciones
     * 
     * @param num 
     */
    private void mostrarSolucion(int num) {
        try {
            // Creamos un Tablero auxiliar con la solución que queremos
            Tablero aux = tablero.getSolucion(num);
            
            // Guardamos el ArrayList de soluciones para no perderlo
            ArrayList solucionesAux = tablero.getSoluciones();

            // Actualizamos la nueva solución
            this.setVisible(false);
            VentanaTablero ventanaTablero = new VentanaTablero(aux, tamaño);

            // Actualizamos el ArrayList de soluciones
            tablero.setSoluciones(solucionesAux);

            ventanaTablero.setVisible(true);
        } catch (Exception e) {
        }
    }
}
