package Ventanas;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luisb
 */
public class VentanaInicio extends JFrame {

    public static final int ANCHURA = 300;
    public static final int ALTURA = 400;

    private JPanel panel;
    private JTextField texto;
    private JButton intro;
    private JLabel titulo;

    /**
     * Método constructor de la clase
     * 
     */
    public VentanaInicio() {
        super("Juego del Caballo");

        initComponents();
    }

    /**
     * Método que inicializa los componentes de la ventana
     * 
     */
    private void initComponents() {
        this.setSize(ANCHURA, ALTURA);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        this.panel = new JPanel();
        this.titulo = new JLabel("Introduce la dimensión del tablero");
        this.texto = new JTextField();
        this.intro = new JButton("Empezar");

        this.panel.setLayout(new GridLayout(3, 1, 0, 50));

        this.panel.add(titulo);
        this.panel.add(texto);
        this.panel.add(intro);

        this.panel.setVisible(true);

        // Listener para registrar cuando el usuario pulsa el boton y se ejecutan
        // las correspondientes acciones
        intro.addActionListener((ActionEvent ae) -> {
            try {
                int d = Integer.parseInt(texto.getText());

                if (d < 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un valor entero positivo");
                } else if (d < 5) {
                    JOptionPane.showMessageDialog(null, "No hay solución posible");
                } else {
                    VentanaTablero ventanaTablero = new VentanaTablero(d);
                    ventanaTablero.setVisible(true);

                    // Cerramos la ventana actual
                    this.dispose();
                }

            } catch (HeadlessException | NumberFormatException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        });

        // Set Bounds (x, y, anchura, altura)
        this.panel.setBounds(50, ALTURA - 350, ALTURA - 200, 200);

        this.getContentPane().add(panel);
    }
}
