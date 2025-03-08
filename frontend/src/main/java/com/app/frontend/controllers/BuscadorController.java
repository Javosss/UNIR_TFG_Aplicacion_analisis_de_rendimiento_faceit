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
        setMensajeEstadoBusqueda(label, false); // Mostrar mensaje de error
        return;
    }

    // Obtener datos del jugador desde Flask
    Jugador jugador = ApiService.getPlayerStats(nickname);  
    
    if (jugador != null) {
        setMensajeEstadoBusqueda(label, true); // Mostrar mensaje de éxito
        mostrarInfoJugador(jugador);
    } else {
        setMensajeEstadoBusqueda(label, false); // Mostrar mensaje de error
        }
    }
    
    public void eliminarTextoField(JTextField field) { // Funcion para quitar el texto del Text Field "Introducir nickname" cuando se haga click en él para escribir
        field.setText("");
    }
    
    public void anadirTextoDeNuevo(JTextField field) { // Funcion para hacer lo contrario que la funcion anterior eliminarTextoField, esta funcion añade el mensaje de nuevo al Text Field
        field.setText(GestionIdiomas.getMensaje("field_nickname_1"));
    }
    
    public void setMensajeEstadoBusqueda(JLabel label, Boolean estado) { // Funcion para mostrar el mensaje del estado de la busqueda (Se utiliza booleano para estado positivo o fallido)
        if (estado == true) { // Si es postivo, la busqueda ha sido correcta
            label.setForeground(Color.GREEN); // Se pone el texto en verde
            label.setText(GestionIdiomas.getMensaje("mensaje_estado_busqueda_correcto_1")); // Se pone el mensaje correspondiente traducido
            label.setVisible(true);
        }
        if (estado == false) { // Si no es correcto, se pone de color rojo y mensaje de error.
            label.setForeground(Color.RED);
            label.setText(GestionIdiomas.getMensaje("mensaje_estado_busqueda_fallida_1"));
            label.setVisible(true);
        }
    }
    
    public void mostrarInfoJugador(Jugador jugador) { // Mostrar los datos de la petición a la API en el labelInfoJugador
        vista.actualizarInfoJugador(jugador); // Se actualiza la vista con el jugador pasado como objeto
    }
    
}
