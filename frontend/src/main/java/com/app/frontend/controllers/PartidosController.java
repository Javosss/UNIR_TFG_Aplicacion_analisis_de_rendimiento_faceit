package com.app.frontend.controllers;

import com.app.frontend.models.EstadisticasPartido;
import com.app.frontend.models.Jugador;
import com.app.frontend.models.Partido;
import com.app.frontend.services.ApiService;
import com.app.frontend.views.Partidos;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
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
    
    // Cargar el historial de partidos del jugador
    private void cargarHistorialPartidos() {
        // Se utiliza SwingWorker para cargar los partidos sin que se bloquee la interfaz
        new SwingWorker<List<Partido>, Void>() {
            @Override
            protected List<Partido> doInBackground() throws Exception { // Se ejecuta en segundo plano para obtener los partidos utilizando la llamada a la API de ApiService.java
                return ApiService.getHistorialPartidos( jugador.getPlayer_id(), "cs2", 100);
            }
            
            @Override
            protected void done() { // Este metodo se ejecuta cuando termina la carga en segunda plano, para actualizar la interfaz
                try {
                    List<Partido> partidos = get();
                    if (partidos != null && !partidos.isEmpty()) {
                        configurarTablaPartidos(partidos);
                    } else {
                        JOptionPane.showMessageDialog( vista,  "No se encontraron partidos recientes", "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog( vista, "Error al cargar partidos: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    // Configuración de la tabla partidos con los datos recibidos de los partidos
    private void configurarTablaPartidos(List<Partido> partidos) {
        DefaultTableModel model = new DefaultTableModel() { // Model para la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };       
        // Configuración de columnas para la tabla
        model.setColumnIdentifiers(new String[]{"ID Partido", "Competición", "Resultado", "Modo", "Región","Fecha"});
        
        for (Partido partido : partidos) { // Se llena con los datos
            model.addRow(new Object[]{partido.getIdPartido(),partido.getNombreCompeticion(),partido.getResultadoPartido(),partido.getModo(),partido.getRegion(),partido.getHoraComienzo()});
        }
        
        vista.getTablaPartidos().setModel(model);
        
        // Configurar el renderizado para las celdas de la tabla
        vista.getTablaPartidos().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String resultado = table.getModel().getValueAt(row, 2).toString(); // Se coge el resultado del partido de la columna 2 (para después colorear según el resultado)               
                // Coloreado según resultado (Si es ganada, color verde, si es perdida, color rojo)
                if ("Ganada".equalsIgnoreCase(resultado)) {
                    c.setBackground(new Color(200, 255, 200));
                } else if ("Perdida".equalsIgnoreCase(resultado)) {
                    c.setBackground(new Color(255, 200, 200));
                } else {
                    c.setBackground(Color.WHITE); // SI por lo que sea, la API no devuelve Ganada o Perdida, se pone en blacno
                }               
                // Resaltar una fila de color azul cuando se selecciona (click en ella)
                if (isSelected) {
                    c.setBackground(new Color(150, 200, 255));
                }
               
                return c;
            }
        });
        
        // Al igual que en la interfaz Dashboard, se ajustan los tamaño del ancho de las columnas
        TableColumnModel columnModel = vista.getTablaPartidos().getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(120);
    }
    
    // Configuración de los listeners para cuando el usuario interactue
    private void configurarListeners() {
        vista.getTablaPartidos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarEstadisticasPartido();
            }
        });
    }
    
    // Método para cargar las estadísticas del partido que se ha seleccionado (de nuevo, utilizando SwingWorker para que no se bloquee la interfaz)
    private void cargarEstadisticasPartido() {
        int filaSeleccionada = vista.getTablaPartidos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            String idPartido = vista.getTablaPartidos().getModel().getValueAt(filaSeleccionada, 0).toString();
            
            new SwingWorker<EstadisticasPartido, Void>() {
                @Override
                protected EstadisticasPartido doInBackground() throws Exception {
                    return ApiService.getEstadisticasPartido(idPartido, jugador.getPlayer_id());
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
                        JOptionPane.showMessageDialog(vista, "Error al cargar estadísticas: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }.execute();
        }
    }
    
    // Actualizar los JTextArea de la interfaz con las estadísticas del partido seleccionado
    private void actualizarEstadisticas(EstadisticasPartido stats) {
        
        // Estadísticas individuales
        vista.getTextAreaEstadisticasIndividuales().setText( // Se formatea el string y se gestionan los posibles nulos en algunos campos que devuelve el JSON
            String.format("Mapa: %s\n"+"Resultado: %s\n"+"Kills: %s\n"+"Deaths: %s\n"+"Assists: %s\n"+"ADR: %s\n"+"Damage: %s\n"+"Headshots: %s\n"+"HS%%: %s\n"+"K/D Ratio: %s\n"+"MVPs: %s",                   
                stats.getMapa(),stats.getResultadoPartido(),stats.getKills(),stats.getDeaths(),stats.getAssists(),stats.getAdr(),stats.getDamage(),stats.getHeadshots(),
                stats.getHeadshotsPercentage() != null ? stats.getHeadshotsPercentage() : "N/A",
                stats.getKdRatio() != null ? stats.getKdRatio() : "N/A",stats.getMVPs() )
        );
        
        // Situaciones Clutch
        vista.getTextAreaClutch().setText(
            String.format("1v1: %s/%s\n"+"1v2: %s/%s\n"+"Clutch Kills: %s\n"+"Entry Count: %s\n"+"Entry Wins: %s\n"+"First Kills: %s",
                stats.getV1Wins() != null ? stats.getV1Wins() : "0",
                stats.getV1Count() != null ? stats.getV1Count() : "0",
                stats.getV2Wins() != null ? stats.getV2Wins() : "0",
                stats.getV2Count() != null ? stats.getV2Count() : "0",
                stats.getClutchKills() != null ? stats.getClutchKills() : "0",
                stats.getEntryCount() != null ? stats.getEntryCount() : "0",
                stats.getEntryWins() != null ? stats.getEntryWins() : "0",
                stats.getFirstKills() != null ? stats.getFirstKills() : "0" )
        );
        
        // Utilidad
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
        
        // Rendimiento con armas
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
    
    // Metodo para limpiar los JTextArea cuando no hay estadisticas disponibles
    private void limpiarEstadisticas() {
        vista.getTextAreaEstadisticasIndividuales().setText("Estadísticas individuales\nNo disponibles");
        vista.getTextAreaClutch().setText("Situaciones Clutch\nNo disponibles");
        vista.getTextAreaUtilidad().setText("Utilidad\nNo disponibles");
        vista.getTextAreaArmas().setText("Rendimiento con armas\nNo disponibles");
        
    }
}
