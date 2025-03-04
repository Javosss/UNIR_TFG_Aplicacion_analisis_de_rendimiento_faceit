package com.app.frontend.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase para la traducción de idiomas en la interfaz
 * @author Javier
 */
public class GestionIdiomas {
    private static Locale locale = new Locale("es"); // Se utiliza un objeto Locale que representa una región y se establece español por defecto ("es")
    
    public static void setIdioma(String codigo) { // Metodo para cambiar el idioma, recibe el código del idioma y crea el objeto Locale con ese código
        locale = new Locale(codigo);
    }
    
    public static String getMensaje(String clave) { // Metodo para recibir un mensaje traducido en el idioma actual
        ResourceBundle bundle = ResourceBundle.getBundle("com.app.frontend.locales.mensajes", locale); // Se busca dicha clave en los archivos de traducción
        return bundle.getString(clave);
    }
}
