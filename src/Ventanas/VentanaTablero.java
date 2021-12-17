/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author luisb
 */
public class VentanaTablero extends JFrame {

    private int tamaño;
    private Tablero tablero;
    private static final String RUTA_REINA = "imagenes/reina.png";
    private boolean solucionar;

    private JButton[][] casillas;
    private ImageIcon imagen;
    private static final int TAMAÑO_CASILLA = 80;

    public VentanaTablero(int n) {
        super("Juego de las N-Reinas");

        this.tamaño = n;
        this.tablero = new Tablero(tamaño);
        this.solucionar = false;

        initComponents();
    }

    public VentanaTablero(Tablero tablero, int n) {
        super("Juego de las N-Reinas");

        this.tamaño = n;
        this.tablero = tablero;
        this.solucionar = true;

        initComponents();
    }

    private void initComponents() {
        this.setSize(tamaño * TAMAÑO_CASILLA + 16, tamaño * TAMAÑO_CASILLA + 39);
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
    }

    private void initTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new JButton();

                if (solucionar && tablero.getValor(i, j) == 1) {
                    imagen = new ImageIcon("imagenes/reina.png");
                    casillas[i][j].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(TAMAÑO_CASILLA - 20, TAMAÑO_CASILLA - 20, Image.SCALE_DEFAULT)));
                    casillas[i][j].setOpaque(true);
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
