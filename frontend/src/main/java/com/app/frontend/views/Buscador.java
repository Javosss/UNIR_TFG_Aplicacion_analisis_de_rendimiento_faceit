package com.app.frontend.views;

import com.app.frontend.controllers.BuscadorController;
import com.app.frontend.models.Jugador;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.utils.GestionIdiomas;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;

/**
 * Interfaz principal de la aplicación:
 * Consiste de un buscador y un boton para buscar por nickname.
 * @author Javier
 */
public class Buscador extends javax.swing.JFrame {
    private BuscadorController controlador;
    
    public Buscador() {
        initComponents();
        this.controlador = new BuscadorController(this); // Se crea el controlador pasandole la propia vista como argumento
        
        ConfigurarEventos(); // Se llama a la función que configura los eventos
        
    }
    
    private void ConfigurarEventos() { // Asignación de los eventos del Controlador para la vista
        btnBuscar.addActionListener(e -> controlador.buscarJugador(labelEstadoBusqueda)); // Evento al presionar el boton
        
        fieldBuscador.addFocusListener(new FocusListener() {   
            @Override
            public void focusGained(FocusEvent e) {
            // Si el campo tiene el texto predeterminado, lo limpia
            if (fieldBuscador.getText().equals(GestionIdiomas.getMensaje("field_nickname_1"))) {
                fieldBuscador.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
            // Si el campo está vacío, se vuelve a poner el texto predeterminado
            if (fieldBuscador.getText().trim().isEmpty()) {
            fieldBuscador.setText(GestionIdiomas.getMensaje("field_nickname_1"));
                }
            }
        });
    }

    public String getNickname() { // Devuelve el nickname del text field de la interfaz
        return fieldBuscador.getText().trim();
    }
    
    public void mostrarInfoJugador(Jugador jugador) {
        //labelInfoJugador.setText(jugador.getNickname() + " - " + GestionIdiomas.getMensaje("player_elo") + " " + jugador.getElo());
        panelTarjeta.setVisible(true);
    }
    
    public void actualizarInfoJugador(String info, String avatarUrl) { // Función para añadir la información de la API en la tarjeta
        labelInfoJugador.setText(info); // Se añade la información al label
        CargarImagenDesdeURL.cargarImagen(labelFotoJugador, avatarUrl); // Se carga la imagen desde la URL
        panelTarjeta.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        fieldBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        panelTarjeta = new javax.swing.JPanel();
        labelInfoJugador = new javax.swing.JLabel();
        labelFotoJugador = new javax.swing.JLabel();
        labelEstadoBusqueda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(153, 204, 255));

        // Se le asigna el texto traducido al Text Field
        fieldBuscador.setText(GestionIdiomas.getMensaje("field_nickname_1"));

        // Se le asigna el texto traducido al boton
        btnBuscar.setText(GestionIdiomas.getMensaje("boton_buscar_1"));

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));

        labelInfoJugador.setText("jLabel1");

        labelFotoJugador.setText("jLabel1");

        javax.swing.GroupLayout panelTarjetaLayout = new javax.swing.GroupLayout(panelTarjeta);
        panelTarjeta.setLayout(panelTarjetaLayout);
        panelTarjetaLayout.setHorizontalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInfoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(labelFotoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTarjetaLayout.setVerticalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFotoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInfoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(fieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(panelTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(labelEstadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEstadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(panelTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buscador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JTextField fieldBuscador;
    private javax.swing.JLabel labelEstadoBusqueda;
    private javax.swing.JLabel labelFotoJugador;
    private javax.swing.JLabel labelInfoJugador;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTarjeta;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
}
