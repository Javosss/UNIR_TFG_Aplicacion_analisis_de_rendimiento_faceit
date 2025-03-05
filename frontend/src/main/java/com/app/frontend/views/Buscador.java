package com.app.frontend.views;

import com.app.frontend.controllers.BuscadorController;
import com.app.frontend.models.Jugador;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.utils.GestionIdiomas;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

    
    public void actualizarInfoJugador(String nickname, String pais, int nivel_cs2, int elo_cs2, String avatarUrl) { // Función para añadir la información de la API en la tarjeta
        // Se convierte a String para utilizar setText()
        String nivel_cs2_str = String.valueOf(nivel_cs2);
        String elo_cs2_str = String.valueOf(elo_cs2);
        
        // Se cargan todos los datos
        labelNickname.setText(nickname);
        labelElo.setText("Elo: " + elo_cs2_str);
        CargarImagenDesdeURL.cargarImagen(labelFotoJugador, avatarUrl); // Se carga la imagen desde la URL
        
        // Cargar el icono del nivel correspondiente (Nivel 1-10)
        String nivelIconPath = "/icons/niveles_faceit/" + nivel_cs2 + ".png"; // Ruta donde se guardan los 11 iconos
        java.net.URL nivelIconUrl = getClass().getResource(nivelIconPath); // URL completa

        if (nivelIconUrl != null) {
            ImageIcon nivelIcon = new ImageIcon(nivelIconUrl); // Crear un objeto ImageIcon
            labelNivel.setIcon(nivelIcon); // Asignar el icono al JLabel
            labelNivel.setText(""); // Es necesario limpiar el texto porque si no muestra un texto
        } else { // En caso de que no se encuentre el icono, se muestra el nivel en texto
            labelNivel.setText("Nivel: " + nivel_cs2);
        }
         
        /* Para cargar el país como logo en vez de como código (Por ejemplo, es), he buscado alguna API que me pueda proporcionar las banderar a partir de una URL,
        de esta forma puedo reutilizar CargarImagenDesdeURL que he desarrollado.
        El recurso que he encontrado sería: https://flagcdn.com/ , servicio proporcionado por parte de "Flagpedia"
        De todas las que ofrece la página, las que mejor quedan en mi interfaz son las de "https://flagcdn.com/h20/" , hay multiples tipos
        */
        String banderaUrl = "https://flagcdn.com/h20/" + pais.toLowerCase() + ".png"; // URL completa que proporciona la bandera ("pais" pasado como parámetro es el código del país)
        CargarImagenDesdeURL.cargarImagen(labelPais, banderaUrl);
        panelTarjeta.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelElo1 = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        fieldBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        panelTarjeta = new javax.swing.JPanel();
        labelNickname = new javax.swing.JLabel();
        labelFotoJugador = new javax.swing.JLabel();
        labelNivel = new javax.swing.JLabel();
        labelPais = new javax.swing.JLabel();
        labelElo = new javax.swing.JLabel();
        labelEstadoBusqueda = new javax.swing.JLabel();

        labelElo1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(153, 204, 255));

        // Se le asigna el texto traducido al Text Field
        fieldBuscador.setText(GestionIdiomas.getMensaje("field_nickname_1"));

        // Se le asigna el texto traducido al boton
        btnBuscar.setText(GestionIdiomas.getMensaje("boton_buscar_1"));

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));

        labelNickname.setText("jLabel1");

        labelNivel.setText("jLabel1");

        labelElo.setText("jLabel1");

        javax.swing.GroupLayout panelTarjetaLayout = new javax.swing.GroupLayout(panelTarjeta);
        panelTarjeta.setLayout(panelTarjetaLayout);
        panelTarjetaLayout.setHorizontalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFotoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(labelPais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addGroup(panelTarjetaLayout.createSequentialGroup()
                        .addComponent(labelNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelElo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTarjetaLayout.setVerticalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFotoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNivel)
                    .addComponent(labelNickname))
                .addGap(8, 8, 8)
                .addComponent(labelElo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPais)
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
    private javax.swing.JLabel labelElo;
    private javax.swing.JLabel labelElo1;
    private javax.swing.JLabel labelEstadoBusqueda;
    private javax.swing.JLabel labelFotoJugador;
    private javax.swing.JLabel labelNickname;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelPais;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTarjeta;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
}
