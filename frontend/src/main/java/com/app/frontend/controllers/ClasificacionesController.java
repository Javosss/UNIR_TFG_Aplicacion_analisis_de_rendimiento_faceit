package com.app.frontend.controllers;

import com.app.frontend.models.Jugador;
import com.app.frontend.models.JugadorClasificacion;
import com.app.frontend.services.ApiService;
import com.app.frontend.views.Clasificaciones;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase controladora de la interfaz Clasificaciones
 * @author Javier
 */
public class ClasificacionesController {
    private final Clasificaciones vista;
    private final Jugador jugador;
    
    public ClasificacionesController(Clasificaciones vista, Jugador jugador) {
        this.vista = vista;
        this.jugador = jugador;
        initController();
    }
    
    private void initController() {

        //cargarTablaClasificacion();     
        vista.getBotonFiltrar().addActionListener(e -> aplicarFiltros());
    }
    
    // Método para aplicar los filtros seleccionados y ejecutar la carga en la tabla con esos filtros aplicados
    private void aplicarFiltros() {
        // Obtener valores de los filtros (necesario hacer el Cast al coger el elemento del combobox)
        String juego = (String) vista.getComboBoxJuego().getSelectedItem();
        String region = (String) vista.getComboBoxRegion().getSelectedItem();      
        String minusculaPais = (String) vista.getComboBoxPais().getSelectedItem();
        String pais = minusculaPais.toLowerCase();// Tiene que ser en minuscula porque si se hace la petición con FR en vez de fr, devuelve otros datos distintos
        
        // Obtener posición de comienzo y límite (con valores por defecto si están vacíos)
        int comienzo = 0;
        int limite = 100;
        try {
            comienzo = Integer.parseInt(vista.getTextFieldPosicionComienzo().getText().trim());
        } catch (Exception e) {
        }
        try {
            limite = Integer.parseInt(vista.getTextFieldLimite().getText().trim());
        } catch (Exception e) {
        }       
        cargarTablaClasificacion(region, juego, pais, comienzo, limite);// Se carga la tabla con los filtros aplicados
    }
    
    // Cargar valores por defecto (en la tabla de clasificaciones de la derecha)
    /*private void cargarTablaClasificacion() {
        // Valores por defecto: región del jugador, juego cs2, sin país, comienzo 0, límite 100
        String region = jugador.getRegion_cs2();
        cargarTablaClasificacion(region, "cs2", "", 0, 100);
    } */
    
    // Método principal para cargar la tabla de clasificación
    private void cargarTablaClasificacion(String region, String juego, String pais, int comienzo, int limite) {
        
        String respuestaAPI = ApiService.getClasificacionFiltros(region, juego, pais, comienzo, limite); // Obtener datos de la API con los filtros aplicados
        
        if (respuestaAPI == null || respuestaAPI.isEmpty()) {
            System.err.println("No se recibieron datos de la API");
            return;
        }
        
        // Se convierte el JSON a un array de objetos JugadorClasificacion (para luego recorrerlo y cargarlo en la tabla)
        Gson gson = new Gson();
        JugadorClasificacion[] jugadores = gson.fromJson(respuestaAPI, JugadorClasificacion[].class);
        
        // Modelo de la tabla
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? ImageIcon.class : Object.class; // La columna de país será ImageIcon
            }
        };
        
        model.setColumnIdentifiers(new String[]{"Posición", "Nickname", "País", "Elo"});        
        // Se llena la tabla con los datos del array convertido del JSON
        for (JugadorClasificacion jugador : jugadores) {
            ImageIcon bandera = cargarBandera(jugador.getCountry());            
            model.addRow(new Object[]{jugador.getPosicion(), jugador.getNickname(), bandera, jugador.getFaceit_elo()});
        }       
        // Asignar modelo a la tabla
        JTable tabla = vista.getTablaClasificacion();
        tabla.setModel(model);       
        // Configurar ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        // Ajustes estéticos para la tabla
        tabla.setRowHeight(25); // Fijar altura de filas
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Fuente Segoe UI
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); // Header de las columnas en negrita
        tabla.setGridColor(new Color(220, 220, 220)); // Color de grid más suave
        
        // Configurar renderizador para la columna de banderas
        tabla.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value instanceof ImageIcon) {
                    setIcon((ImageIcon) value);
                    setText("");                   
                }
                setHorizontalAlignment(javax.swing.JLabel.CENTER);
                return this;
            }
        });
    }
    
    // Método para cargar la bandera de un país desde su código de país
    private ImageIcon cargarBandera(String codigoPais) {
        if (codigoPais == null || codigoPais.isEmpty()) {
            return null;
        }       
        try {
            String banderaUrl = "https://flagcdn.com/h20/" + codigoPais.toLowerCase() + ".png";
            java.net.URL url = new java.net.URL(banderaUrl);
            ImageIcon bandera = new ImageIcon(url);
            bandera.setImage(bandera.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH)); // Escalado para que quede bien la bandera dentro de la columna
            return bandera;
        } catch (Exception e) {
            System.err.println("Error al cargar bandera para país " + codigoPais + ": " + e.getMessage());
            return null;
        }
    }
}
