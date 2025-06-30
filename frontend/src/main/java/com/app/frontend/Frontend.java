package com.app.frontend;

import com.app.frontend.controllers.BuscadorController;
import com.app.frontend.services.ApiService;
import com.app.frontend.views.Buscador;
import javax.swing.*;

import com.app.frontend.models.Partido;
import com.app.frontend.services.ApiService;
/**
 *
 * @author Javier
 */
public class Frontend {

    public static void main(String[] args) {
         ApiService prueba = new ApiService();
         prueba.getEquiposJugador("d682a62e-43f5-49be-b8c9-de923adcf564");
         
        // Primera interfaz, test.
        java.awt.EventQueue.invokeLater(() -> {
            Buscador vista = new Buscador();
            vista.setSize(600,400);
            vista.setVisible(true);
        });
    }
}
