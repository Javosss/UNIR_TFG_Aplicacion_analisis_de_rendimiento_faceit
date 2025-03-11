package com.app.frontend.controllers;

import com.app.frontend.views.Dashboard;
import com.app.frontend.models.Jugador;
import com.app.frontend.models.JugadorClasificacion;
import com.app.frontend.services.ApiService;
import javax.swing.JLabel;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.utils.ConvertirCodigoEnPais;
import com.google.gson.Gson;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



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
        labelFechaCreacionCuenta.setText(fechaCreacion);
        labelEloCs2.setText("Elo CS2: " + elo_cs2_str);
        labelEloCsgo.setText("Elo CSGO: " + elo_csgo_str);
        
        CargarImagenDesdeURL.cargarImagen(labelAvatar, avatar);
    }
    
    // Funcion para cargar los datos de la clasificación en la lista
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
}
