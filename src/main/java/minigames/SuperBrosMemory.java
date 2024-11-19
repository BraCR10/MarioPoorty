package minigames;

import MainGame.Player;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperBrosMemory extends JFrame {
    private Player player;
    
    private static final int FILAS = 3;
    private static final int COLUMNAS = 6;
    private static final int PAREJAS = (FILAS * COLUMNAS) / 2;
    private static final int TIEMPO_SEGUNDOS = 35; // Cambia el tiempo en segundos si deseas ajustar el límite
    
    private JLabel[][] etiquetas = new JLabel[FILAS][COLUMNAS];
    private List<String> imagenes = new ArrayList<>();
    private JLabel primeraSeleccion = null;
    private int parejasEncontradas = 0;
    private Timer temporizador;
    private int tiempoRestante = TIEMPO_SEGUNDOS;
    private JLabel etiquetaTemporizador;
    
    public SuperBrosMemory(Player player) {
        this.player = player;
        configurarJuego();
    }

    private void configurarJuego() {
        setTitle("SuperBrosMemory ["+player.name+"]");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        player.board.setVisible(false);

        JPanel tablero = new JPanel(new GridLayout(FILAS, COLUMNAS));
        agregarImagenes();
        

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JLabel etiqueta = new JLabel("?", SwingConstants.CENTER);
                etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
                etiqueta.setOpaque(true);
                etiqueta.setBackground(Color.LIGHT_GRAY);
                etiqueta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                final int fila = i;
                final int columna = j;
                
                etiqueta.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        try {
                            manejarClick(fila, columna);
                        } catch (IOException ex) {
                            Logger.getLogger(SuperBrosMemory.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                etiquetas[i][j] = etiqueta;
                tablero.add(etiqueta);
            }
        }

        add(tablero, BorderLayout.CENTER);


        etiquetaTemporizador = new JLabel("Tiempo restante: " + tiempoRestante + " segundos", SwingConstants.CENTER);
        etiquetaTemporizador.setFont(new Font("Arial", Font.BOLD, 18));
        add(etiquetaTemporizador, BorderLayout.NORTH);
        
        iniciarTemporizador();
        setVisible(true);
    }

    private void agregarImagenes() {
        // Crea las parejas de imágenes (simuladas con números)
        for (int i = 1; i <= PAREJAS; i++) {
            imagenes.add(String.valueOf(i));
            imagenes.add(String.valueOf(i));
        }
        Collections.shuffle(imagenes); // Mezcla las imágenes
    }

    private void iniciarTemporizador() {
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    etiquetaTemporizador.setText("Tiempo restante: " + tiempoRestante + " segundos");
                } else {
                    temporizador.cancel();
                    try {
                        mostrarMensaje("Tiempo agotado. ¡Inténtalo de nuevo!");
                    } catch (IOException ex) {
                        Logger.getLogger(SuperBrosMemory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }, 1000, 1000); // Ejecuta cada segundo
    }

    private void manejarClick(int fila, int columna) throws IOException {
        JLabel etiquetaSeleccionada = etiquetas[fila][columna];
        
        if (etiquetaSeleccionada.getText().equals("?") && tiempoRestante > 0) { // Solo responde si la carta está volteada
            etiquetaSeleccionada.setText(imagenes.get(fila * COLUMNAS + columna));
            
            if (primeraSeleccion == null) {
                // Primer clic de pareja
                primeraSeleccion = etiquetaSeleccionada;
            } else {
                // Segundo clic de pareja
                if (primeraSeleccion.getText().equals(etiquetaSeleccionada.getText())) {
                    parejasEncontradas++;
                    primeraSeleccion.setBackground(Color.GREEN);
                    etiquetaSeleccionada.setBackground(Color.GREEN);
                    primeraSeleccion = null;
                    
                    if (parejasEncontradas == PAREJAS) {
                        temporizador.cancel();
                        mostrarMensaje("¡Felicidades! Has encontrado todas las parejas.");
                    }
                } else {

                    TimerTask tareaOcultar = new TimerTask() {
                        @Override
                        public void run() {
                            primeraSeleccion.setText("?");
                            etiquetaSeleccionada.setText("?");
                            primeraSeleccion = null;
                        }
                    };
                    new Timer().schedule(tareaOcultar, 500);
                }
            }
        }
    }

    private void mostrarMensaje(String mensaje) throws IOException {
        this.player.out.writeInt(parejasEncontradas);        
        
        String name = player.in.readUTF();
        boolean win = player.in.readBoolean();
        System.out.println("Winner.: " + name+ "["+win+"] PAIRS ---> "+parejasEncontradas);
        
        if (name.equals(this.player.name) && win){this.player.Condition = "Roll";}
        player.board.setVisible(true);
        System.out.println("[SuperBrosMemory] ----> Finished");
        this.dispose();
    }

}
