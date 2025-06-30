package com.app.frontend.controllers;

import com.app.frontend.views.Buscador;
import com.app.frontend.views.Dashboard;
import com.app.frontend.views.Equipos;
import com.app.frontend.views.MenuLateral;
import com.app.frontend.views.MainView;
import com.app.frontend.views.Partidos;
//import java.awt.event.ActionListener;

/**
 * Controlador para el Menú lateral reutilizable
 * @author Javier
 */
public class MenuLateralController {
    private final MenuLateral vista;
    private final MainView parentView;

    public MenuLateralController(MenuLateral vista, MainView parentView) {
        this.vista = vista;
        this.parentView = parentView;
        configurarListeners();
    }
    
    // En este metodo se configuran las acciones de los botones del menu
    private void configurarListeners() { 
        
        // Listener para el botón de Dashboard
        vista.setDashboardListener(e -> {
            if (!(parentView instanceof Dashboard)) {
                parentView.dispose();
                new Dashboard(parentView.getJugador()).setVisible(true);
            }
        });

        // Listener para el botón de Estadísticas
        vista.setEstadisticasListener(e -> {
            
        });
        
        // Listener para el botón Partidos
        vista.setPartidosListener(e -> {
            if (!(parentView instanceof Partidos)) {
                parentView.dispose();
                Partidos vistaPartidos = new Partidos(parentView.getJugador());
                vistaPartidos.setVisible(true);
            }
        });
        
        // Listener para el botón Clasificaciones
        vista.setClasificacionesListener(e -> {
            
        });
        
        // Listener para el botón Equipos
        vista.setEquiposListener(e -> {
            if (!(parentView instanceof Equipos)) {
                parentView.dispose();
                Equipos vistaEquipos = new Equipos(parentView.getJugador());
                vistaEquipos.setVisible(true);
            }
        });
        
        // Listener para el botón Buscador
        vista.setLigasListener(e -> {
            
        });
        
        // Listener para el botón Buscador
        vista.setBuscadorListener(e -> {
            if (!(parentView instanceof Buscador)) {
                parentView.dispose();
                Buscador vistaBuscador = new Buscador();
                vistaBuscador.setVisible(true);
            }
        });
        

        // Listener para el botón de Cerrar
        vista.setCerrarListener(e -> System.exit(0));
        
        
        // Se habilitan los botones
        vista.btnEstadisticas.setEnabled(true);
        vista.btnPartidos.setEnabled(true);
        vista.btnClasificaciones.setEnabled(true);
        vista.btnEquipos.setEnabled(true);
        vista.btnLigas.setEnabled(true);
    }
}
