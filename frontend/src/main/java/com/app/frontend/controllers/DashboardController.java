package com.app.frontend.controllers;

import com.app.frontend.views.Dashboard;
import javax.swing.JPanel;
import com.app.frontend.models.Jugador;
import javax.swing.JLabel;
import com.app.frontend.utils.CargarImagenDesdeURL;

/**
 * Clase de la parte controladora para la vista de la interfaz Dashboard
 * @author Javier
 */
public class DashboardController {
    private Dashboard vista;
    
    public DashboardController(Dashboard vista) {
        this.vista = vista;
    }
    
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
        labelEloCsgo.setText("ELO CSGO: " + elo_csgo_str);
        
        CargarImagenDesdeURL.cargarImagen(labelAvatar, avatar);
    }
}
