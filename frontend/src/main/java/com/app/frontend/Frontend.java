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
        java.awt.EventQueue.invokeLater(() -> {
            Buscador vista = new Buscador();
            vista.setSize(600,400);
            vista.setVisible(true);
        });
    }
}
