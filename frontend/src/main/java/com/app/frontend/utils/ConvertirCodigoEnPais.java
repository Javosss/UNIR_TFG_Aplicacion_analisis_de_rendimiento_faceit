package com.app.frontend.utils;

import java.util.Locale;

/**
 * Clase para transformar el código de país (por ejemplo "fr") en el nombre del país ("France")
 * De nuevo se utiliza Locale, que en este caso permite hacer exactamente el cambio de formato código a nombre.
 * Las APIs de FACEIT siguen el ISO 3166-1
 * @author Javier
 */
public class ConvertirCodigoEnPais {
    
    // Funcion para convertir el código a nombre del país en inglés
    public static String getPaisDesdeCodigoIngles(String codigoPais) { 
        if (codigoPais == null){ // Si el código no es correcto, devuelve error
            return "Invalid code";
        }
        // Si el código es correcto, se crea el objeto y se llama al metodo para convertir el código en el país
        Locale locale = new Locale("", codigoPais); // El primer parámetro es el idioma y el segundo el código del país
        String nombrePais = locale.getDisplayCountry(new Locale("en")); // Este método devuelve el nombre del país en el idioma pasado por parametro
        return nombrePais;
    }
    
    public static String getPaisDesdeCodigoEspanol(String codigoPais) { // Funcion para convertir el código al nombre del país en español
        if (codigoPais == null){ // Si el código no es correcto, devuelve error
            return "Codigo de país no válido";
        }

        Locale locale = new Locale("", codigoPais); // El primer parámetro es el idioma y el segundo el código del país
        String nombrePais = locale.getDisplayCountry(new Locale("es"));
        return nombrePais;
    }
    
    public static String getPaisDesdeCodigoFrances(String codigoPais) {// Funcion para convertir el código al nombre del país en francés
        if (codigoPais == null){ // Si el código no es correcto, devuelve error
            return "Code non valide";
        }
        
        Locale locale = new Locale("", codigoPais); // El primer parámetro es el idioma y el segundo el código del país
        String nombrePais = locale.getDisplayCountry(new Locale("fr"));
        return nombrePais;
    }  
}
