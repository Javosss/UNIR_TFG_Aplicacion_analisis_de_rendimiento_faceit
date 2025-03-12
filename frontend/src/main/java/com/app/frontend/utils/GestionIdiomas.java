package com.app.frontend.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase para la traducción de idiomas en la interfaz
 * @author Javier
 */
public class GestionIdiomas {
    /* Al utilizar un Locale estático, afectará a todas las instancias que dependan de él en la aplicación, entonces no hace
    falta estar cambiando el idioma todo el rato en las interfaces. Si se cambia el idioma en la primera interfaz, todas
    las demás estarán también traducidas, sin necesidad de tener que cambiar el idioma todo el rato*/
    private static Locale locale = new Locale("es"); // Se utiliza un objeto Locale que representa una región y se establece español por defecto ("es")
    
    // Metodo para cambiar el idioma, recibe el código del idioma y crea el objeto Locale con ese código
    public static void setIdioma(String codigo) { 
        locale = new Locale(codigo);
    }
    // Metodo para recibir un mensaje traducido en el idioma actual
    public static String getMensaje(String clave) { 
        ResourceBundle bundle = ResourceBundle.getBundle("com.app.frontend.locales.mensajes", locale); // Se busca dicha clave en los archivos de traducción
        return bundle.getString(clave);
    }
}
