package com.app.frontend.controllers;

import com.app.frontend.models.EstadisticasPartido;
import com.app.frontend.models.Jugador;
import com.app.frontend.models.Partido;
import com.app.frontend.services.ApiService;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.views.Partidos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Controlador para cargar partidos y sus estadísticas en la interfaz Partidos
 * @author Javier
 */
public class PartidosController {
    private final Partidos vista;
    private final Jugador jugador;
    
    public PartidosController(Partidos vista, Jugador jugador) {
        this.vista = vista;
        this.jugador = jugador;
        initController();
    }
    
    private void initController() {
        cargarHistorialPartidos();
        configurarListeners();
    }
    
    /*
    Documentación de la clase SwingWorker
    https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingWorker.html
    */
    
    // Cargar el historial de partidos del jugador ( se utiliza SwingWorker para no bloquear la interfaz mientras se cargan los datos en segundo plano)
    private void cargarHistorialPartidos() {
        new SwingWorker<List<Partido>, Void>() {
            @Override
            protected List<Partido> doInBackground() throws Exception {
                return ApiService.getHistorialPartidos(jugador.getPlayer_id(), "cs2", 300); // Petición a la API para obtener los últimos 300 partidos
            }
            
            @Override
            protected void done() {
                try {
                    List<Partido> partidos = get();
                    if (partidos != null && !partidos.isEmpty()) {
                        configurarTablaPartidos(partidos);
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se encontraron partidos recientes", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(vista, "Error al cargar partidos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    // Configuración de la tabla partidos con los datos recibidos de los partidos
    private void configurarTablaPartidos(List<Partido> partidos) {
        DefaultTableModel model = new DefaultTableModel() { // Modelo de tabla no editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.setColumnIdentifiers(new String[]{"Partido", "ID Partido", "Competición", "Resultado", "Modo", "Región", "Fecha"}); // Columnas de la tabla

        // Se añaden los partidos en la fila de la tabla
        for (Partido partido : partidos) {
            model.addRow(new Object[]{partido, partido.getIdPartido(), partido.getNombreCompeticion(), partido.getResultadoPartido(), partido.getModo(), partido.getRegion(),               
                partido.getHoraComienzo()
            });
        }
        
        vista.getTablaPartidos().setModel(model);
        
        // Renderizador para las celdas y cambiar el color de la fila en función de si ganó la partida o la perdió
        vista.getTablaPartidos().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String resultado = table.getModel().getValueAt(row, 3).toString();
                
                if ("Ganada".equalsIgnoreCase(resultado)) { // Si es ganada se pone en verde
                    c.setBackground(new Color(200, 255, 200));
                } else if ("Perdida".equalsIgnoreCase(resultado)) { // Si es perdida, en rojo
                    c.setBackground(new Color(255, 200, 200));
                } else {
                    c.setBackground(Color.WHITE);
                }
                
                if (isSelected) { // Se pone de color azul la fila cuando se seleccione
                    c.setBackground(new Color(150, 200, 255));
                }
               
                return c;
            }
        });
        
        //Ajustar los anchos de la tabla para que quede bien
        TableColumnModel columnModel = vista.getTablaPartidos().getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(120);
        
        // Ajustes estéticos para la tabla
        vista.getTablaPartidos().setRowHeight(25); // Fijar altura de filas
        vista.getTablaPartidos().setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Fuente Segoe UI
        vista.getTablaPartidos().getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); // Header de las columnas en negrita
        vista.getTablaPartidos().setGridColor(new Color(220, 220, 220)); // Color de grid más suave
    }
    
    // Configuración de los listeners para cuando el usuario interactue con la tabla
    private void configurarListeners() {
        // Listener para cuando se selecciona una fila de la tabla
        vista.getTablaPartidos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarEstadisticasPartido();
            }
        });
    }
    
    // Método para extraer las estadísticas del partido que se ha seleccionado
    private void cargarEstadisticasPartido() {
        int filaSeleccionada = vista.getTablaPartidos().getSelectedRow();
        
        // Cuando se selecciona una fila válida
        if (filaSeleccionada >= 0) {
            Partido partido = (Partido) vista.getTablaPartidos().getModel().getValueAt(filaSeleccionada, 0); // Se obtiene el partido seleccionado
            String idPartido = partido.getIdPartido();
            
            cargarEquiposPartido(partido); // Se cargan los equipos a los paneles
            
            // Otra vez SwingWorker para cargar las estadisticas sin que se bloquee la interfaz
            new SwingWorker<EstadisticasPartido, Void>() {
                @Override
                protected EstadisticasPartido doInBackground() throws Exception {
                    return ApiService.getEstadisticasPartido(idPartido, jugador.getPlayer_id()); // Llamada a la API para extraer las estadísticas del jugador en ese partido
                }
                
                @Override
                protected void done() {
                    try {
                        EstadisticasPartido stats = get();
                        if (stats != null) {
                            actualizarEstadisticas(stats);
                        } else {
                            limpiarEstadisticas();
                            JOptionPane.showMessageDialog(vista, "No se encontraron estadísticas detalladas", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        limpiarEstadisticas();
                        JOptionPane.showMessageDialog(vista, "Error al cargar estadísticas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }.execute();
        }
    }
    
    // Cargar información de los equipos (los jugadores del equipo) del partido que se ha seleccionado en la tabla
    private void cargarEquiposPartido(Partido partido) {
        limpiarPanelesEquipos(); // Antes de cargar los nuevos datos, es importante limpiar los paneles primero
        
        // Se carga el equipo del jugador (en caso de que exista, porque hay veces (en casos especiales) en los que por algún motivo la API no los devuelve)
        if (partido.getEquipoJugador() != null && partido.getEquipoJugador().getJugadores() != null) {
            List<Partido.JugadorPartido> jugadoresEquipo = partido.getEquipoJugador().getJugadores();
            cargarJugadoresEnPaneles(jugadoresEquipo, true);
        }
        // Se carga también el equipo contrario si existe
        if (partido.getEquipoContrario() != null && partido.getEquipoContrario().getJugadores() != null) {
            List<Partido.JugadorPartido> jugadoresContrario = partido.getEquipoContrario().getJugadores();
            cargarJugadoresEnPaneles(jugadoresContrario, false);
        }
    }
    
    // Limpiar todos los paneles de jugadores (porque a veces se quedan datos anteriores)
    private void limpiarPanelesEquipos() {
        for (int i = 1; i <= 5; i++) { // Se utiliza un for para limpiiar los 5 paneles de cada equipo (1 panel por jugador)
            // Limpiar el panel del equipo del jugador
            limpiarPanelJugador(getPanelJugadorEquipo(i), getLabelNombreJugadorEquipo(i), getImagenJugadorEquipo(i), 
                    getImagenNivelJugadorEquipo(i));
            
            // Limpiar el panel del equipo contrario
            limpiarPanelJugador(getPanelJugadorContrario(i), getLabelNombreJugadorContrario(i), 
                              getImagenJugadorContrario(i), getImagenNivelJugadorContrario(i));
        }
    }
    
  
    
    // Cargar la lista de jugadores en los paneles
    private void cargarJugadoresEnPaneles(List<Partido.JugadorPartido> jugadores, boolean esEquipoJugador) {
        for (int i = 0; i < jugadores.size() && i < 5; i++) { // Se utiliza un for para cargar los 5 jugadores de cada equipo
            Partido.JugadorPartido jugador = jugadores.get(i);
            // Se cargan los jugadores del equipo del jugador buscado
            if (esEquipoJugador) {
                cargarJugadorEnPanel(jugador,getPanelJugadorEquipo(i+1),getLabelNombreJugadorEquipo(i+1),getImagenJugadorEquipo(i+1),getImagenNivelJugadorEquipo(i+1));
                
                
            } else {
                // Se cargan los jugadores del equipo contrario del jugador buscado
                cargarJugadorEnPanel(jugador,getPanelJugadorContrario(i+1),getLabelNombreJugadorContrario(i+1),getImagenJugadorContrario(i+1),
                    getImagenNivelJugadorContrario(i+1));
            }
        }
    }
    
    // Cargar datos de un jugador del equipo (en la partida) en un panel específico
    private void cargarJugadorEnPanel(Partido.JugadorPartido jugador, JPanel panel, JLabel labelNombre, JLabel labelImagen, JLabel labelNivel) {     
        labelNombre.setText(jugador.getNickname()); // Nombre del jugador
        // Avatar del jugador (su foto)
        if (jugador.getAvatar() != null && !jugador.getAvatar().isEmpty()) {
            CargarImagenDesdeURL.cargarImagen(labelImagen, jugador.getAvatar(), 80, 80);
        
        } else {
            labelImagen.setIcon(null);
            labelImagen.setText("Sin avatar");
        }
        // Nivel de FACEIT del jugador
        String rutaNivel = "src/main/resources/icons/niveles_faceit/" + jugador.getSkillLevel() + ".png"; // La ruta donde están los iconos de los niveles de FACEIT (usado en DashboardController tambien)
        labelNivel.setIcon(new ImageIcon(rutaNivel));
        panel.setVisible(true);
    }
    
    
    
    // Actualizar los JTextArea de la interfaz con las estadísticas del partido seleccionado
    private void actualizarEstadisticas(EstadisticasPartido stats) {
        vista.getTextAreaEstadisticasIndividuales().setText(
            String.format("Mapa: %s\n"+"Resultado: %s\n"+"Kills: %s\n"+"Deaths: %s\n"+"Assists: %s\n"+"ADR: %s\n"+"Damage: %s\n"+"Headshots: %s\n"+"HS%%: %s\n"+"K/D Ratio: %s\n"+"MVPs: %s",                   
                stats.getMapa(),stats.getResultadoPartido(),stats.getKills(),stats.getDeaths(),stats.getAssists(),stats.getAdr(),stats.getDamage(),stats.getHeadshots(),
                stats.getHeadshotsPercentage() != null ? stats.getHeadshotsPercentage() : "N/A",
                stats.getKdRatio() != null ? stats.getKdRatio() : "N/A",stats.getMVPs())
        );
        
        vista.getTextAreaClutch().setText(
            String.format("1v1: %s/%s\n"+"1v2: %s/%s\n"+"Clutch Kills: %s\n"+"Entry Count: %s\n"+"Entry Wins: %s\n"+"First Kills: %s",
                stats.getV1Wins() != null ? stats.getV1Wins() : "0",
                stats.getV1Count() != null ? stats.getV1Count() : "0",
                stats.getV2Wins() != null ? stats.getV2Wins() : "0",
                stats.getV2Count() != null ? stats.getV2Count() : "0",
                stats.getClutchKills() != null ? stats.getClutchKills() : "0",
                stats.getEntryCount() != null ? stats.getEntryCount() : "0",
                stats.getEntryWins() != null ? stats.getEntryWins() : "0",
                stats.getFirstKills() != null ? stats.getFirstKills() : "0")
        );
        
        vista.getTextAreaUtilidad().setText(
            String.format("Flash Count: %s\n"+"Enemies Flashed: %s\n"+"Flash Success: %s\n"+"Utility Damage: %s\n"+"Utility Count: %s\n"+"Flashes/Round: %s",
                stats.getFlashCount() != null ? stats.getFlashCount() : "0",
                stats.getEnemiesFlashed() != null ? stats.getEnemiesFlashed() : "0",
                stats.getFlashSuccesses() != null ? stats.getFlashSuccesses() : "0",
                stats.getUtilityDamage() != null ? stats.getUtilityDamage() : "0",
                stats.getUtilityCount() != null ? stats.getUtilityCount() : "0",
                stats.getFlashesPerRound() != null ? stats.getFlashesPerRound() : "0"
            )
        );
        
        vista.getTextAreaArmas().setText(
            String.format("Pistol Kills: %s\n"+"Sniper Kills: %s\n"+"Knife Kills: %s\n"+"Zeus Kills: %s\n"+"Double Kills: %s\n"+"Triple Kills: %s\n"+"Quadro Kills: %s\n"+"Penta Kills: %s",
                stats.getPistolKills() != null ? stats.getPistolKills() : "0",
                stats.getSniperKills() != null ? stats.getSniperKills() : "0",
                stats.getKnifeKills() != null ? stats.getKnifeKills() : "0",
                stats.getZeusKills() != null ? stats.getZeusKills() : "0",
                stats.getDoubleKills() != null ? stats.getDoubleKills() : "0",
                stats.getTripleKills() != null ? stats.getTripleKills() : "0",
                stats.getQuadroKills() != null ? stats.getQuadroKills() : "0",
                stats.getPentaKills() != null ? stats.getPentaKills() : "0"
            )
        );
    }
    
    // Limpiar un panel individual de jugador
    private void limpiarPanelJugador(JPanel panel, JLabel labelNombre, JLabel labelImagen, JLabel labelNivel) {
        labelNombre.setText("");
        labelImagen.setIcon(null);
        labelImagen.setText("");
        labelNivel.setIcon(null);
        panel.setVisible(true);
    }
    
    // Metodo para limpiar los JTextArea cuando no hay estadisticas disponibles
    private void limpiarEstadisticas() {
        vista.getTextAreaEstadisticasIndividuales().setText("Estadísticas individuales\nNo disponibles");
        vista.getTextAreaClutch().setText("Situaciones Clutch\nNo disponibles");
        vista.getTextAreaUtilidad().setText("Utilidad\nNo disponibles");
        vista.getTextAreaArmas().setText("Rendimiento con armas\nNo disponibles");
    }
    
    
    // Métodos para obtener los paneles del equipo del jugador (de esta forma para no hacer un metodo por cada panel y 3 labels por jugador)
    private JPanel getPanelJugadorEquipo(int numero) {
        switch(numero) {
            case 1: return vista.getPanelJugadorEquipo1();
            case 2: return vista.getPanelJugadorEquipo2();
            case 3: return vista.getPanelJugadorEquipo3();
            case 4: return vista.getPanelJugadorEquipo4();
            case 5: return vista.getPanelJugadorEquipo5();
            default: return null;
        }
    }
    
    private JLabel getLabelNombreJugadorEquipo(int numero) {
        switch(numero) {
            case 1: return vista.getLabelNombreJugadorEquipo1();
            case 2: return vista.getLabelNombreJugadorEquipo2();
            case 3: return vista.getLabelNombreJugadorEquipo3();
            case 4: return vista.getLabelNombreJugadorEquipo4();
            case 5: return vista.getLabelNombreJugadorEquipo5();
            default: return null;
        }
    }
    
    private JLabel getImagenJugadorEquipo(int numero) {
        switch(numero) {
            case 1: return vista.getImagenJugadorEquipo1();
            case 2: return vista.getImagenJugadorEquipo2();
            case 3: return vista.getImagenJugadorEquipo3();
            case 4: return vista.getImagenJugadorEquipo4();
            case 5: return vista.getImagenJugadorEquipo5();
            default: return null;
        }
    }
    
    private JLabel getImagenNivelJugadorEquipo(int numero) {
        switch(numero) {
            case 1: return vista.getImagenNivelJugadorEquipo1();
            case 2: return vista.getImagenNivelJugadorEquipo2();
            case 3: return vista.getImagenNivelJugadorEquipo3();
            case 4: return vista.getImagenNivelJugadorEquipo4();
            case 5: return vista.getImagenNivelJugadorEquipo5();
            default: return null;
        }
    }
    
    // Paneles del equipo contrario
    private JPanel getPanelJugadorContrario(int numero) {
        switch(numero) {
            case 1: return vista.getPanelJugadorContrario1();
            case 2: return vista.getPanelJugadorContrario2();
            case 3: return vista.getPanelJugadorContrario3();
            case 4: return vista.getPanelJugadorContrario4();
            case 5: return vista.getPanelJugadorContrario5();
            default: return null;
        }
    }
    
    private JLabel getLabelNombreJugadorContrario(int numero) {
        switch(numero) {
            case 1: return vista.getLabelNombreJugadorContrario1();
            case 2: return vista.getLabelNombreJugadorContrario2();
            case 3: return vista.getLabelNombreJugadorContrario3();
            case 4: return vista.getLabelNombreJugadorContrario4();
            case 5: return vista.getLabelNombreJugadorContrario5();
            default: return null;
        }
    }
    
    private JLabel getImagenJugadorContrario(int numero) {
        switch(numero) {
            case 1: return vista.getImagenJugadorContrario1();
            case 2: return vista.getImagenJugadorContrario2();
            case 3: return vista.getImagenJugadorContrario3();
            case 4: return vista.getImagenJugadorContrario4();
            case 5: return vista.getImagenJugadorContrario5();
            default: return null;
        }
    }
    
    private JLabel getImagenNivelJugadorContrario(int numero) {
        switch(numero) {
            case 1: return vista.getImagenNivelJugadorContrario1();
            case 2: return vista.getImagenNivelJugadorContrario2();
            case 3: return vista.getImagenNivelJugadorContrario3();
            case 4: return vista.getImagenNivelJugadorContrario4();
            case 5: return vista.getImagenNivelJugadorContrario5();
            default: return null;
        }
    }
}
