package com.app.frontend.controllers;

import com.app.frontend.models.Jugador;
import com.app.frontend.services.ApiService;
import com.app.frontend.utils.GestionIdiomas;
import com.app.frontend.views.Buscador;
import com.app.frontend.views.Dashboard;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 * Clase de la parte controladora para la vista de la interfaz Buscador
 * @author Javier
 */
public class BuscadorController {
    private Buscador vista; // Interfaz buscador
    
    public BuscadorController(Buscador vista) {
        this.vista = vista;
    }
    
    public void buscarJugador(JLabel label) {
    String nickname = vista.getNickname();
    
    // Validar que el nickname no sea el texto predeterminado o esté vacío (Es necesario porque si no se envia "Introducir texto" a la API.
    if (nickname.isEmpty() || nickname.equals(GestionIdiomas.getMensaje("field_nickname_1"))) {
        System.out.println("Introducir un jugador válido en el buscador");
        return;
    }
    // Obtener datos del jugador desde Flask
    Jugador jugador = ApiService.getPlayerStats(nickname);  
    
    if (jugador != null) {
        mostrarInfoJugador(jugador);
    } else {
        System.out.println("Jugador no encontrado");
        }
    }
    
    // Funcion para quitar el texto del Text Field "Introducir nickname" cuando se haga click en él para escribir
    public void eliminarTextoField(JTextField field) { 
        field.setText("");
    }
    // Funcion para hacer lo contrario que la funcion anterior eliminarTextoField, esta funcion añade el mensaje de nuevo al Text Field
    public void anadirTextoDeNuevo(JTextField field) { 
        field.setText(GestionIdiomas.getMensaje("field_nickname_1"));
    }
    // Mostrar los datos de la petición a la API en el labelInfoJugador
    public void mostrarInfoJugador(Jugador jugador) { 
        vista.actualizarInfoJugador(jugador); // Se actualiza la vista con el jugador pasado como objeto
    }
    
}
