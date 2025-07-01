package com.app.frontend.controllers;

import com.app.frontend.models.EquipoJugador;
import com.app.frontend.models.Jugador;
import com.app.frontend.services.ApiService;
import com.app.frontend.views.Equipos;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase controladora para la interfaz Equipos
 * @author Javier
 */

public class EquiposController {
    private final Equipos vista;
    private final Jugador jugador;
    private List<EquipoJugador> equipos;
    private EquipoJugador equipoSeleccionado;
    
    public EquiposController(Equipos vista, Jugador jugador) {
        this.vista = vista;
        this.jugador = jugador;
        initController();
    }
    
    private void initController() {
        cargarTablaEquipos();
        configurarListeners();
    }
    
    // Listeners de la interfaz Equipos
    private void configurarListeners() {
        // Cuando se selecciona un elemento de la tabla de historial de equipos
        vista.getTablaEquipos().getSelectionModel().addListSelectionListener(e -> { // Se accede al modelo de seleccion de la tabla
            if (!e.getValueIsAdjusting()) {
                int selectedRow = vista.getTablaEquipos().getSelectedRow(); // Índice de la tabla que se ha seleccionado
                
                if (selectedRow >= 0 && selectedRow < equipos.size()) { // para evitar valores negativos o indices que no sean válidos
                    equipoSeleccionado = equipos.get(selectedRow); // Se obtiene el equipo correspondiente a la fila seleccionada
                    // Se cargan los datos en las otras tablas (los datos del equipo seleccionado)
                    cargarTablaMiembros();
                    cargarTablaTorneos();
                }
            }
        });
    }
    
    private void cargarTablaEquipos() {
        equipos = ApiService.getEquiposJugador(jugador.getPlayer_id()); // Se obtienen todos los equipos del jugador llamando a la API  
        
        if (equipos == null || equipos.isEmpty()) {// Hay bastantes jugadores que no tienen ningún equipo asociado
            System.err.println("No se encontraron equipos o hubo un error al cargarlos");
            return;
        }
        // Crear el modelo de tabla (y hacer que la tabla no sea editable)
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.setColumnIdentifiers(new String[]{"Foto", "Nombre", "Abreviatura"}); // Configuración de las columnas de la tabla     
        
        // Se recorren los equipos y se llena la tabla con esa información
        for (EquipoJugador equipo : equipos) {
            ImageIcon iconoEquipo = null; // Para las tablas, se utilikza ImageIcon en vez de la función auxiliar
            try {
                if (equipo.getFotoEquipo() != null && !equipo.getFotoEquipo().isEmpty()) {
                    java.net.URL url = new java.net.URL(equipo.getFotoEquipo());
                    iconoEquipo = new ImageIcon(url);
                    // Escalar la imagen
                    iconoEquipo.setImage(iconoEquipo.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
                }
            } catch (Exception e) {
                System.err.println("Error al cargar imagen del equipo: " + e.getMessage());
            }
            
            Object[] fila = {iconoEquipo,equipo.getNombreEquipo(),equipo.getNicknameEquipo()};
            model.addRow(fila);
        }
        
        // Se asigna el modelo a la tabla de equipos
        JTable tabla = vista.getTablaEquipos();
        tabla.setModel(model);       
        // Al igual que en las interfaces anteriores, es necesario ajustar el ancho de las columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        // Configurar el renderizador para centrar las imágenes en la columna de la tabla (TableCellRender es el componente que controla cómo se muestra la información en la tabla)
        tabla.getColumnModel().getColumn(0).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {// La columna 0 es la de las fotos del equipo
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);                
                if (value instanceof ImageIcon) { // Si el valor es un ImageIcon, se muestra el icono en la columna
                    setIcon((ImageIcon) value);
                    setText("");
                } else {
                    setIcon(null);
                    setText(value != null ? value.toString() : "");
                }               
                setHorizontalAlignment(javax.swing.JLabel.CENTER);// Se centra la imagen en el espacio de la columna de la tabla
                return this;
            }
        });
    }
    
    // Método para cargar todos los miembros de un equipo (al seleccionar el equipo en la tabla de miembros)
    public void cargarTablaMiembros() {
        if (equipoSeleccionado == null || equipoSeleccionado.getMiembros() == null) return;
        // Se crea el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) { //Se especifica que la primera y tercera columna contienen ImageIcon
                return columnIndex == 0 || columnIndex == 2 ? ImageIcon.class : Object.class;
            }
        };

        model.setColumnIdentifiers(new String[]{"Foto", "Nickname", "País"});
        // Se llena la tabla con los datos de los miembros (recorriendo los miembros del equipo)
        for (EquipoJugador.Miembro miembro : equipoSeleccionado.getMiembros()) {
            ImageIcon iconoMiembro = null;
            ImageIcon banderaPais = null;
            try {
                // Cargar avatar del miembro
                if (miembro.getAvatar() != null && !miembro.getAvatar().isEmpty()) {
                    java.net.URL url = new java.net.URL(miembro.getAvatar());
                    iconoMiembro = new ImageIcon(url);
                    iconoMiembro.setImage(iconoMiembro.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
                }

                // Cargar bandera del país
                if (miembro.getPais() != null && !miembro.getPais().isEmpty()) {
                    String banderaUrl = "https://flagcdn.com/h20/" + miembro.getPais().toLowerCase() + ".png";
                    java.net.URL url = new java.net.URL(banderaUrl);
                    banderaPais = new ImageIcon(url);
                    banderaPais.setImage(banderaPais.getImage().getScaledInstance(30, 20, java.awt.Image.SCALE_SMOOTH));
                }
            } catch (Exception e) {
                System.err.println("Error al cargar avatar del miembro o bandera: " + e.getMessage());
            }

            model.addRow(new Object[]{iconoMiembro, miembro.getApodo(), banderaPais});
        }

        JTable tabla = vista.getTablaMiembros();
        tabla.setModel(model);

        // Ancho de las columnas para que no esté descompensado
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);   // Avatar
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);  // Nickname
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);   // Bandera

        // Se renderizan las imágenes (para ambas columnas con iconos)
        javax.swing.table.DefaultTableCellRenderer renderer = new javax.swing.table.DefaultTableCellRenderer() {
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
        };

        tabla.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabla.getColumnModel().getColumn(2).setCellRenderer(renderer);
    }
    
    // Método para cargar los torneos jugados de un equipo (al seleccionar el equipo en la tabla de miembros)
    public void cargarTablaTorneos() {
        if (equipoSeleccionado == null || equipoSeleccionado.getTorneos() == null) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(new String[]{"Nombre", "Región", "Juego", "Núm. jugadores", "Fecha"});
        // Se llena la tabla con los torneos del equipo
        for (EquipoJugador.Torneo torneo : equipoSeleccionado.getTorneos()) {
            model.addRow(new Object[]{torneo.getNombre(), torneo.getRegion(), torneo.getJuego(), torneo.getNumeroJugadores(),torneo.getHoraInicio() });
        }

        JTable tabla = vista.getTablaTorneos();
        tabla.setModel(model);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabla.revalidate();
        tabla.repaint();
    }   
}