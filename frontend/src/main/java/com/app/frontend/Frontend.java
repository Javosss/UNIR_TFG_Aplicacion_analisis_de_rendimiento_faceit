/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.app.frontend;

import com.app.frontend.controllers.BuscadorController;
import com.app.frontend.services.ApiService;
import com.app.frontend.views.Buscador;
import javax.swing.*;

/**
 *
 * @author Javier
 */
public class Frontend {

    public static void main(String[] args) {
        
        // Primera interfaz, test.
        java.awt.EventQueue.invokeLater(() -> {
            Buscador vista = new Buscador();
            vista.setSize(600,400);
            vista.setVisible(true);
        });
        
        String posicion = ApiService.getPosicionJugadorRegion("EU", "d1a1aa41-f4ea-4035-97f7-cd522733c6d9", "");
        System.out.println(posicion);
    }
}
