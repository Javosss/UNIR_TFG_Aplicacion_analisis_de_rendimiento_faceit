package com.app.frontend.controllers;

import com.app.frontend.views.Dashboard;
import com.app.frontend.models.Jugador;
import com.app.frontend.models.JugadorClasificacion;
import com.app.frontend.models.Partido;
import com.app.frontend.models.ResumenEstadisticas;
import com.app.frontend.services.ApiService;
import javax.swing.JLabel;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.utils.ConvertirCodigoEnPais;
import com.app.frontend.utils.GestionIdiomas;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase de la parte controladora para la vista de la interfaz Dashboard
 * @author Javier
 */
public class DashboardController {
    private Dashboard vista;
    
    public DashboardController(Dashboard vista) {
        this.vista = vista;
    }
    
    // Funcion para cargar los datos del Jugador en el Panel de Resumen de estadísticas
    public void cargarDatosPanelResumenStats(Jugador jugador, JLabel labelAvatar, JLabel labelNickname, JLabel labelFechaCreacionCuenta, JLabel labelEloCs2, JLabel labelEloCsgo) {    
        int elo_cs2 = jugador.getElo_cs2();
        int elo_csgo = jugador.getElo_csgo();
        String elo_cs2_str = String.valueOf(elo_cs2);
        String elo_csgo_str = String.valueOf(elo_csgo);
        
        // Fuentes de los Labels
        Font fuenteTexto = new Font("Segoe UI", Font.PLAIN, 12);
        Font fuenteNickname = new Font("Segoe UI", Font.BOLD, 14);
        labelNickname.setFont(fuenteNickname);
        labelFechaCreacionCuenta.setFont(fuenteTexto);
        labelEloCs2.setFont(fuenteTexto);
        labelEloCsgo.setFont(fuenteTexto);
     
        // Se cargan los datos a los labels del panel
        labelNickname.setText(jugador.getNickname());
        labelFechaCreacionCuenta.setText(GestionIdiomas.getMensaje("label_fecha_creacion_2") + jugador.getFecha_creacion_cuenta());
        labelEloCs2.setText("Elo CS2: " + elo_cs2_str);
        labelEloCsgo.setText("Elo CSGO: " + elo_csgo_str);     
        CargarImagenDesdeURL.cargarImagen(labelAvatar, jugador.getAvatar(),80,70);
    }
    
    // Cargar los datos de la clasificación en la tabla
    public void cargarTablasDeClasificacioin(String region, String juego, JTable tabla) {
        String respuestaAPI = ApiService.getClasificacionRegion(region); // Llamada a la API para extraer los datos de la clasificación
        // Convertir el JSON a un objeto para poder añadir los datos a la tabla
        Gson gson = new Gson();
        JugadorClasificacion[] jugadores = gson.fromJson(respuestaAPI, JugadorClasificacion[].class); // Se convierte el JSON en un array de objetos JugadorClasificacion

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // No editable
                return false;
            }
        };
        model.setColumnIdentifiers(new String[]{"Posición", "Nickname", "País", "Elo"});
        
        //Construir las filas de la tabla de clasificación    
        for (JugadorClasificacion jugador : jugadores) {
            // Sacar el nombre completo del país mediante su código de país (La documentación de FACEIT indica que se utiliza ISO 3166-1, códigos de 2 dígitos)
            String codigoPais = jugador.getCountry();
            String pais = ConvertirCodigoEnPais.getPaisDesdeCodigoIngles(codigoPais); // Por defecto se utilizará el inglés           
            Object[] fila = {jugador.getPosicion(),jugador.getNickname(),pais,jugador.getFaceit_elo()
            };
            model.addRow(fila);
        }
        tabla.setModel(model);
        
        // Ajustes estéticos para la tabla
        tabla.setRowHeight(25); // Fijar altura de filas
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Fuente Segoe UI
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); // Header de las columnas en negrita
        tabla.setGridColor(new Color(220, 220, 220)); // Color de grid más suave
    }
    
    // Cargar los datos de la clasificacion del pais del jugador. Funcion muy parecida a la anterior, lo que cambia es la peticion a la API
    public void cargarTablaClasificacionPais(JTable tabla) {
        Jugador jugadorInterfaz = vista.getJugador();
        String respuestaAPI = ApiService.getClasificacionPais(jugadorInterfaz.getRegion_cs2(), jugadorInterfaz.getPais()); // Se le pasa la region y el pais para hacer la petición
        
        // Mismo procedimiento que para CargarTablasDeClasificacion
        Gson gson = new Gson();
        JugadorClasificacion[] jugadores = gson.fromJson(respuestaAPI, JugadorClasificacion[].class);
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // No editable
                return false;
            }
        };
        
        modelo.setColumnIdentifiers(new String[] {"Posicion","Nickname","Pais","Elo"});
        
        for (JugadorClasificacion jugador : jugadores) {
            String codigoPais = jugador.getCountry();
            String paisJugador = ConvertirCodigoEnPais.getPaisDesdeCodigoIngles(codigoPais);
            
            Object[] fila = {jugador.getPosicion(),jugador.getNickname(),paisJugador,jugador.getFaceit_elo()};
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
        // Misma estética que la primera tabla
        tabla.setRowHeight(25);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.setGridColor(new Color(220, 220, 220));
    }
    
    // Cargar la posición del jugador en el Ranking de su región
    public void cargarPosicionJugadorRegion(JLabel labelPosicion) {
        Jugador jugador = vista.getJugador();     
        // Sacar la ruta del icono a mostrar en base a la región del jugador (icono de la región como EU (Europa), NA (Norte América), etc.)
        String rutaIcono;
        switch (jugador.getRegion_cs2()) {
            case "EU":
                rutaIcono = "/icons/eu.png";
                break;
            case "NA":
                rutaIcono = "/icons/na.png";
                break;
            case "SA":
                rutaIcono = "/icons/sa.png";
                break;
            case "SEA":
                rutaIcono = "/icons/sea.png";
                break;
            case "OCE":
                rutaIcono = "/icons/oce.png";
                break;
            default:
                rutaIcono = "/icons/error.png";
                break;
        }
        
        String posicion = ApiService.getPosicionJugadorRegion(jugador.getRegion_cs2(), jugador.getPlayer_id(), ""); // Respuesta de la API sobre la posición
       
        // Cargar el icono desde la ruta de iconos
        java.net.URL iconUrl = getClass().getResource(rutaIcono); // Se carga el icono desde la ruta
        if (iconUrl != null) {
            ImageIcon icono = new ImageIcon(iconUrl);
            // Escalar la imagen
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // 50x50 píxeles
            icono = new ImageIcon(imagenEscalada);
            labelPosicion.setIcon(icono); // Se asigna el icono al label
            
            } else {
            System.err.println("No se ha podido cargar el icono: " + rutaIcono);
            labelPosicion.setIcon(null);
            }
        
        labelPosicion.setText("#" + posicion);      
    }
    
    // Cargar la posición del jugador en el ranking de su país
    public void cargarPosicionJugadorPais(JLabel labelPosicionPais) {
        Jugador jugador = vista.getJugador();       
        String posicion = ApiService.getPosicionJugadorRegion(jugador.getRegion_cs2(), jugador.getPlayer_id(), jugador.getPais()); // Respuesta de la API sobre la posición
        // Cargar la bandera del jugador al label       
        String banderaUrl = "https://flagcdn.com/h20/" + jugador.getPais().toLowerCase() + ".png";
        CargarImagenDesdeURL.cargarImagen(labelPosicionPais, banderaUrl,20,10);   
        
        labelPosicionPais.setText("#"+posicion);       
    } 
    
    // Cargar la posición del jugador en el ranking de su país
    public void cargarHistorialPartidos(JTable tabla) {
        Jugador jugador = vista.getJugador();     
        List<Partido> partidos = ApiService.getHistorialPartidos(jugador.getPlayer_id(), "cs2",100); // Obtener el historial de partidos llamando a la API

        if (partidos == null || partidos.isEmpty()) {// Puede ser que el jugador esté registrado pero no haya jugado partidas en la plataforma
            System.err.println("No se encontraron partidos o hubo un error al cargarlos");
            return;
        }

        // Crear el modelo de tabla
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        model.setColumnIdentifiers(new String[]{"Competición","Tipo","Región","Modo","Resultado","Inicio","Fin","ID Partido"});

        // Se construye la tabla con los datos del partido
        for (Partido partido : partidos) {
            Object[] fila = {partido.getNombreCompeticion(),partido.getTipoCompeticion(),partido.getRegion(),partido.getModo(),partido.getResultadoPartido(),
                partido.getHoraComienzo(),partido.getHoraFinalizacion(),partido.getIdPartido()
            };
            model.addRow(fila);
        }
        tabla.setModel(model);

        // Se colorea cada fila en función de si el resultado es Ganada o perdida
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String resultado = table.getModel().getValueAt(row, 4).toString(); // Se obtiene el valor de la columna resultado

                if ("Ganada".equals(resultado)) {
                    c.setBackground(new Color(200, 255, 200)); // En caso de que sea ganada, se cambia a color verde
                } else if ("Perdida".equals(resultado)) {
                    c.setBackground(new Color(255, 200, 200)); // Si es perdida, se cambia a color rojo
                } else {
                    c.setBackground(Color.WHITE);
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    c.setForeground(table.getForeground());
                }

                return c;
            }
        });

        // Se ajusta el ancho de las columnas manualmente porque si no queda un poco descompensado
        tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(200);
    }       
    
    // Cargar el resumen de estadistciacs 
    public void cargarEstadisticasDetalladas() {
        Jugador jugador = vista.getJugador();
        String json = ApiService.getResumenEstadisticasJugador(jugador.getPlayer_id(), "cs2", 20); // Llamada a la API para extraer las estadísticas del jugador para el número de partidos

        if (json != null) {
            ResumenEstadisticas resumen = new Gson().fromJson(json, ResumenEstadisticas.class);
            mostrarEstadisticas(resumen);
            cargarEstadisticasMapas(resumen);
        }
    }
    
    
    // Método para mostrar las estadísticas en los paneles 
    private void mostrarEstadisticas(ResumenEstadisticas resumen) {
        Map<String, Double> stats = resumen.getEstadisticasPromedio();
        
        // Fuente y wrap de texto para el text area
        Font fuenteTextArea = new Font("Segoe UI", Font.PLAIN, 12);
        
        vista.textAreaEstadisticasIndividuales.setFont(fuenteTextArea);
        vista.textAreaEntriesClutches.setFont(fuenteTextArea);
        vista.textAreaEstadisticasArmas.setFont(fuenteTextArea);
        vista.textAreaUtilidad.setFont(fuenteTextArea);
        vista.textAreaMejorMapa.setFont(fuenteTextArea);
        
        vista.textAreaEstadisticasIndividuales.setLineWrap(true);
        vista.textAreaEntriesClutches.setLineWrap(true);
        vista.textAreaEstadisticasArmas.setLineWrap(true);
        vista.textAreaUtilidad.setLineWrap(true);
        vista.textAreaMejorMapa.setLineWrap(true);
        
        // Panel de estadísticas individuales
        String textoIndividuales = String.format( // Se crea el string de las estadísticas y se muestra en el text area correspondiente
            "Partidos analizados: %d\nVictorias: %d\nWinrate: %.2f%%\n\n" + "Kills: %.2f\nDeaths: %.2f\nAssists: %.2f\nADR: %.2f\n" + "K/D Ratio: %.2f\nHS%%: %.2f\nMVPs: %.2f",
            resumen.getPartidosAnalizados(), resumen.getVictorias(), resumen.getPorcentajeVictorias(), stats.get("Kills"), stats.get("Deaths"), 
            stats.get("Assists"), stats.get("ADR"), stats.get("K/D Ratio"), stats.get("Headshots %"), stats.get("MVPs")
        );
        vista.textAreaEstadisticasIndividuales.setText(textoIndividuales);

        // Panel de entries y clutches
        String textoEntriesClutches = String.format(
            "1v1: %.2f/%.2f (%.1f%%)\n1v2: %.2f/%.2f (%.1f%%)\n" + "Clutch Kills: %.2f\nEntry Count: %.2f\nEntry Wins: %.2f\n" + "First Kills: %.2f",
            stats.get("1v1Wins"), stats.get("1v1Count"), stats.get("Match 1v1 Win Rate") * 100, stats.get("1v2Wins"), stats.get("1v2Count"), 
            stats.get("Match 1v2 Win Rate") * 100, stats.get("Clutch Kills"), stats.get("Entry Count"), stats.get("Entry Wins"), stats.get("First_Kills")
        );
        vista.textAreaEntriesClutches.setText(textoEntriesClutches);

        // Panel de estadísticas de rendimiento con armas
        String textoArmas = String.format(
            "Pistol Kills: %.2f\nSniper Kills: %.2f\n" + "Double Kills: %.2f\nTriple Kills: %.2f\n" + "Quadro Kills: %.2f\nPenta Kills: %.2f",
            stats.get("Pistol Kills"), stats.get("Sniper Kills"), stats.get("Double_Kills"), stats.get("Triple Kills"), stats.get("Quadro Kills"), 
            stats.get("Penta Kills")
        );
        vista.textAreaEstadisticasArmas.setText(textoArmas);

        // Panel de estadísticas de utilidad
        String textoUtilidad = String.format(
            "Flash Count: %.2f\nFlash Successes: %.2f\n" + "Enemies Flashed: %.2f\nUtility Damage: %.2f\n" + "Utility Count: %.2f\nUtility Successes: %.2f",
            stats.get("Flash Count"), stats.get("Flash Successes"), stats.get("Enemies Flashed"), stats.get("Utility Damage"), 
            stats.get("Utility Count"), stats.get("Utility Successes")
        );
        vista.textAreaUtilidad.setText(textoUtilidad);
    }

    // Método para cargar las estadísticas de mapas
    private void cargarEstadisticasMapas(ResumenEstadisticas resumen) {
        Map<String, Map<String, Double>> statsMapas = resumen.getEstadisticasPorMapa();
        StringBuilder sb = new StringBuilder();

        // Se recorren todos los mapas y se muestra el winrate de cada uno
        for (Map.Entry<String, Map<String, Double>> entry : statsMapas.entrySet()) {
            String mapa = entry.getKey().substring(3); // Se quita "de_" del nombre
            
            Map<String, Double> stats = entry.getValue();
            double winrate = stats.get("Winrate (%)");
            int partidas = stats.get("Partidas jugadas").intValue();

            sb.append(String.format("%s: %.1f%% (%d partidas)\n", mapa, winrate, partidas));
        }
      
        vista.textAreaMejorMapa.setText(sb.toString());
        vista.imagenMejorMapa.setVisible(false);
    }   
}
