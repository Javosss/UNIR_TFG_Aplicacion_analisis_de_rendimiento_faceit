package com.app.frontend.controllers;

import com.app.frontend.models.EstadisticasPartido;
import com.app.frontend.views.Dashboard;
import com.app.frontend.models.Jugador;
import com.app.frontend.models.JugadorClasificacion;
import com.app.frontend.models.Partido;
import com.app.frontend.services.ApiService;
import javax.swing.JLabel;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.utils.ConvertirCodigoEnPais;
import com.app.frontend.utils.GestionIdiomas;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.List;

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
    public void CargarDatosPanelResumenStats(Jugador jugador, JLabel labelAvatar, JLabel labelNickname, JLabel labelFechaCreacionCuenta, JLabel labelEloCs2, JLabel labelEloCsgo) {
        // Datos a cargar
        String avatar = jugador.getAvatar();
        String nickname = jugador.getNickname();
        String fechaCreacion = jugador.getFecha_creacion_cuenta();
        
        int elo_cs2 = jugador.getElo_cs2();
        int elo_csgo = jugador.getElo_csgo();
        String elo_cs2_str = String.valueOf(elo_cs2);
        String elo_csgo_str = String.valueOf(elo_csgo);
        
        // Se cargan los datos a los labels del panel
        labelNickname.setText(nickname);
        labelFechaCreacionCuenta.setText(GestionIdiomas.getMensaje("label_fecha_creacion_2") +fechaCreacion);
        labelEloCs2.setText("Elo CS2: " + elo_cs2_str);
        labelEloCsgo.setText("Elo CSGO: " + elo_csgo_str);
        
        CargarImagenDesdeURL.cargarImagen(labelAvatar, avatar,80,70);
    }
    
    // Funcion para cargar los datos de la clasificación en la tabla
    public void CargarTablasDeClasificacioin(String region, String juego, JTable tabla) {
        String respuestaAPI = ApiService.getClasificacionRegion(region); // JSON de la respuesta de la API
        
        // Convertir el JSON a un objeto para poder añadir los datos a la tabla
        Gson gson = new Gson();
        JugadorClasificacion[] jugadores = gson.fromJson(respuestaAPI, JugadorClasificacion[].class); // Se convierte el JSON en un array de objetos JugadorClasificacion

        DefaultTableModel model = new DefaultTableModel(); // Se crea el modelo de tabla
        model.setColumnIdentifiers(new String[]{"Posición", "Nickname", "País", "Elo"}); // Se añaden las columnas de la tabla (Se hace desde el controller porque desde la vista no carga la tabla)
        
        
        for (JugadorClasificacion jugador : jugadores) { // Crea un array de obbjetos que representa una fila de la tabla a partir de los datos del jugador
            // Nombre del país a partir del código del país
            String codigoPais = jugador.getCountry();
            String pais = ConvertirCodigoEnPais.getPaisDesdeCodigoIngles(codigoPais); // Por defecto se utilizará el inglés
            
            Object[] fila = {
                jugador.getPosicion(),
                jugador.getNickname(),
                pais,
                jugador.getFaceit_elo()
            };
            model.addRow(fila); // Se añade la fila a la tabla
        }
        tabla.setModel(model); // Se asigna el modelo a la tabla para mostrar los datos
    }
    
    // Funcion para cargar los datos de la clasificacion del pais del jugador. Funcion muy parecida a la anterior, lo que cambia es la peticion a la API
    public void CargarTablaClasificacionPais(JTable tabla) {
        // Sacar la region y el pais del jugador para la peticion a FLask
        Jugador jugadorInterfaz = vista.getJugador();
        String region = jugadorInterfaz.getRegion_cs2();
        String pais = jugadorInterfaz.getPais();
        String respuestaAPI = ApiService.getClasificacionPais(region, pais); // Se le pasa la region y el pais
        
        // Mismo procedimiento que para CargarTablasDeClasificacion
        Gson gson = new Gson();
        JugadorClasificacion[] jugadores = gson.fromJson(respuestaAPI, JugadorClasificacion[].class);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"Posicion","Nickname","Pais","Elo"});
        
        for (JugadorClasificacion jugador : jugadores) {
            String codigoPais = jugador.getCountry();
            String paisJugador = ConvertirCodigoEnPais.getPaisDesdeCodigoIngles(codigoPais);
            
            Object[] fila = {
                jugador.getPosicion(),
                jugador.getNickname(),
                paisJugador,
                jugador.getFaceit_elo()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
    
    // Cargar la posición del jugador en el Ranking de su región
    public void CargarPosicionJugadorRegion(JLabel labelPosicion) {
        Jugador jugador = vista.getJugador();
        String region = jugador.getRegion_cs2();
        
        // Sacar la ruta del icono a mostrar en base a la región del jugador
        String rutaIcono;
        switch (region) {
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
        // Respuesta de la API
        String posicion = ApiService.getPosicionJugadorRegion(jugador.getRegion_cs2(), jugador.getPlayer_id(), "");
       
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
            labelPosicion.setIcon(null); // Limpiar el icono
            }
        
        // Mostrar la posición y el nivel en el JLabel
        labelPosicion.setText("#" + posicion);      
    }
    
    // Función para cargar la posición del jugador en el ranking de su país
    public void CargarPosicionJugadorPais(JLabel labelPosicionPais) {
        Jugador jugador = vista.getJugador();       
        String posicion = ApiService.getPosicionJugadorRegion(jugador.getRegion_cs2(), jugador.getPlayer_id(), jugador.getPais());
        // Cargar la bandera del jugador al label       
        String banderaUrl = "https://flagcdn.com/h20/" + jugador.getPais().toLowerCase() + ".png";
        CargarImagenDesdeURL.cargarImagen(labelPosicionPais, banderaUrl,20,10);       
        labelPosicionPais.setText("#"+posicion);       
    } 
    
    // Función para cargar la posición del jugador en el ranking de su país
    public void CargarHistorialPartidos(JTable tabla) {
        Jugador jugador = vista.getJugador(); // Obtener el jugador de la vista     
        List<Partido> partidos = ApiService.getHistorialPartidos(jugador.getPlayer_id(), "cs2",100); // Obtener el historial de partidos llamando a la API

        if (partidos == null || partidos.isEmpty()) {
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
        // Configurar las columnas de la tabla
        model.setColumnIdentifiers(new String[]{"Competición","Tipo","Región","Modo","Resultado","Inicio","Fin","ID Partido"});

        // Se llena la tabla con los datos de los partidos
        for (Partido partido : partidos) {
            Object[] fila = {
                partido.getNombreCompeticion(),
                partido.getTipoCompeticion(),
                partido.getRegion(),
                partido.getModo(),
                partido.getResultadoPartido(),
                partido.getHoraComienzo(),
                partido.getHoraFinalizacion(),
                partido.getIdPartido()
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
          
}
