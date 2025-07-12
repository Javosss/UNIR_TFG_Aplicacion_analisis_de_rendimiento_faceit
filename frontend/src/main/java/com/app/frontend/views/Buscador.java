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
    private Jugador jugadorActual;
    
    public Buscador() {
        initComponents();
        this.controlador = new BuscadorController(this); // Se crea el controlador pasandole la propia vista como argumento
        
        // Funciones a inicializar
        ConfigurarEventos();
        ConfigurarComboBoxIdiomas();
    }
    
    // Funcion para configurar y definir el comportamiento de eventos
    private void ConfigurarEventos() { // Asignación de los eventos del Controlador para la vista
        btnBuscar.addActionListener(e -> controlador.buscarJugador(labelEstadoBusqueda)); // Evento al presionar el boton
        
        // Eliminar texto del TextField si se hace click para escribir
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
        
        // LLevar a la siguiente interfaz haciendo click en la tarjeta
        /* Para este evento es importante hacerlo para todos los componentes que hay en el panel, porque si no se bloquean entre ellos,
        y si se hace click en el label, por ejemplo, no ejecuta el evento. */
        MouseAdapter eventoClick = new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Si el panel es visible, singifica que la busqueda del jugador ha sido exitosa
                if (panelTarjeta.isVisible()) {
                    if (jugadorActual != null) {
                        // Se crea la nueva interfaz
                        Dashboard dashboard = new Dashboard(jugadorActual);
                        dashboard.setVisible(true);
                        dispose(); // Cerrar la ventana actual
                    }
                }
            }
        };
        // Se añade el evento al panel y luego a todos los componentes
        panelTarjeta.addMouseListener(eventoClick);
        for (Component component : panelTarjeta.getComponents()) { // Se iteran todos los componentes del panel, y se añade el evento
            component.addMouseListener(eventoClick);
        }
        
    }
    
    public String getNickname() { // Devuelve el nickname del text field de la interfaz
        return fieldBuscador.getText().trim();
    }
    
    // Función para actualizar la Tarjeta con la información del jugador tras hacer la petición a la API
    public void actualizarInfoJugador(Jugador jugador) { // Función para añadir la información de la API en la tarjeta
        this.jugadorActual = jugador; // Para actualizar el jugador
        // Se convierte a String para utilizar setText()
        String nivel_cs2_str = String.valueOf(jugador.getRegion_cs2());
        String elo_cs2_str = String.valueOf(jugador.getElo_cs2());
        
        // Se cargan todos los datos
        labelNickname.setText(jugador.getNickname());
        labelElo.setText("Elo: " + elo_cs2_str);
        CargarImagenDesdeURL.cargarImagen(labelFotoJugador, jugador.getAvatar(),450,210); // Se carga la imagen desde la URL
        
        // Cargar el icono del nivel correspondiente (Nivel 1-10)
        String nivelIconPath = "/icons/niveles_faceit/" + jugador.getNivel_cs2() + ".png"; // Ruta donde se guardan los 11 iconos
        java.net.URL nivelIconUrl = getClass().getResource(nivelIconPath); // URL completa

        if (nivelIconUrl != null) {
            ImageIcon nivelIcon = new ImageIcon(nivelIconUrl); // Crear un objeto ImageIcon
            labelNivel.setIcon(nivelIcon); // Asignar el icono al JLabel
            labelNivel.setText(""); // Es necesario limpiar el texto porque si no muestra un texto
        } else { // En caso de que no se encuentre el icono, se muestra el nivel en texto
            labelNivel.setText("Nivel: " + jugador.getNivel_cs2());
        }
         
        /* Para cargar el país como logo en vez de como código (Por ejemplo, es), he buscado alguna API que me pueda proporcionar las banderar a partir de una URL,
        de esta forma puedo reutilizar CargarImagenDesdeURL que he desarrollado.
        El recurso que he encontrado sería: https://flagcdn.com/ , servicio proporcionado por parte de "Flagpedia"
        De todas las que ofrece la página, las que mejor quedan en mi interfaz son las de "https://flagcdn.com/h20/" , hay multiples tipos
        */
        String banderaUrl = "https://flagcdn.com/h20/" + jugador.getPais().toLowerCase() + ".png"; // URL completa que proporciona la bandera ("pais" pasado como parámetro es el código del país)
        CargarImagenDesdeURL.cargarImagen(labelPais2, banderaUrl,70,70);
        panelTarjeta.setVisible(true);    
    }
    
    // Función para configurar el JComboBox
    public void ConfigurarComboBoxIdiomas() {
        //Añadir las opciones de idiomas al JComboBox
        comboBoxCambiarIdioma.addItem("Español");
        comboBoxCambiarIdioma.addItem("English");
        comboBoxCambiarIdioma.addItem("Français");
        //Añadir el action listener para cambiar el idioma
        comboBoxCambiarIdioma.addActionListener(e -> CambiarIdioma());
    }
    
    // Funcion para hacer el cambio de idioma
    private void CambiarIdioma() {
        // Se coge el idioma que esta seleccionado en el comboBox para coger el codigo de idioma y llamar a la función de cambio de idioma
        String idiomaSeleccionado = (String) comboBoxCambiarIdioma.getSelectedItem();
        String codigoIdioma = "";
        
        if (idiomaSeleccionado.equals("Español")) {
            codigoIdioma = "es";
        }
        if (idiomaSeleccionado.equals("English")) {
            codigoIdioma = "en";
        }
        if (idiomaSeleccionado.equals("Français")) {
            codigoIdioma = "fr";
        }
        
        // Se cambia el idioma y se actualizan los componentes de la interfaz
        GestionIdiomas.setIdioma(codigoIdioma);
        fieldBuscador.setText(GestionIdiomas.getMensaje("field_nickname_1"));
        btnBuscar.setText(GestionIdiomas.getMensaje("boton_buscar_1"));
    }
    
    public JLabel getLabelEstadoRespuesta() {
        return labelEstadoRespuesta;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelElo1 = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        fieldBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        panelTarjeta = new javax.swing.JPanel();
        labelFotoJugador = new javax.swing.JLabel();
        labelPais = new javax.swing.JLabel();
        panelInfoJugador = new javax.swing.JPanel();
        labelNickname = new javax.swing.JLabel();
        labelElo = new javax.swing.JLabel();
        labelNivel = new javax.swing.JLabel();
        labelPais2 = new javax.swing.JLabel();
        labelEstadoBusqueda = new javax.swing.JLabel();
        comboBoxCambiarIdioma = new javax.swing.JComboBox<>();
        labelEstadoRespuesta = new javax.swing.JLabel();
        labelInformacion = new javax.swing.JLabel();

        labelElo1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(204, 204, 204));

        // Se le asigna el texto traducido al Text Field
        fieldBuscador.setText(GestionIdiomas.getMensaje("field_nickname_1"));

        // Se le asigna el texto traducido al boton
        btnBuscar.setText(GestionIdiomas.getMensaje("boton_buscar_1"));

        panelTarjeta.setBackground(new java.awt.Color(255, 255, 255));

        panelInfoJugador.setLayout(new java.awt.GridLayout());
        panelInfoJugador.add(labelNickname);
        panelInfoJugador.add(labelElo);
        panelInfoJugador.add(labelNivel);
        panelInfoJugador.add(labelPais2);

        javax.swing.GroupLayout panelTarjetaLayout = new javax.swing.GroupLayout(panelTarjeta);
        panelTarjeta.setLayout(panelTarjetaLayout);
        panelTarjetaLayout.setHorizontalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarjetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInfoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFotoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTarjetaLayout.setVerticalGroup(
            panelTarjetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTarjetaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFotoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelInfoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPais)
                .addContainerGap())
        );

        comboBoxCambiarIdioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        labelEstadoRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstadoRespuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelInformacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInformacion.setText(GestionIdiomas.getMensaje("label_informacion_buscador"));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(labelEstadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1120, Short.MAX_VALUE))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(569, 569, 569)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxCambiarIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldBuscador)
                    .addComponent(labelEstadoRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(labelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(fieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxCambiarIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelEstadoRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(labelEstadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JComboBox<String> comboBoxCambiarIdioma;
    private javax.swing.JTextField fieldBuscador;
    private javax.swing.JLabel labelElo;
    private javax.swing.JLabel labelElo1;
    private javax.swing.JLabel labelEstadoBusqueda;
    private javax.swing.JLabel labelEstadoRespuesta;
    private javax.swing.JLabel labelFotoJugador;
    private javax.swing.JLabel labelInformacion;
    private javax.swing.JLabel labelNickname;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelPais;
    private javax.swing.JLabel labelPais2;
    private javax.swing.JPanel panelInfoJugador;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTarjeta;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
}
