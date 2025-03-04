package com.app.frontend.utils;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.net.URL;

/**
 * Clase para cargar una imagen desde una URL:
 * Esta clase se crea para poder pasar las imagenes en formato URL desde la API a una imagen como tal en un label
 * Ejemplo de enlace: "https://distribution.faceit-cdn.net/images/9deffe6f-bbe3-4747-a225-a2e067a23fc1.jpeg"
 * @author Javier
 */
public class CargarImagenDesdeURL {

    public static void cargarImagen(JLabel label, String imageUrl) {
        try {
            URL url = new URL(imageUrl); // Se crea una URL a partir de la cadena de texto
            ImageIcon imageIcon = new ImageIcon(url); // Se carga la imagen desde la URL

            // Es necesario escalar la imagen, porque si no queda para nada escalado en el label , para ello utilizo la libería awt.Image
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajusta el tamaño
            imageIcon = new ImageIcon(scaledImage); // Se crea el nuevo objeto

            label.setIcon(imageIcon); // Se asigna la imagen al label en cuestión, ya escalada
            
        } catch (Exception e) {
            // Manejar errores (por ejemplo, si la URL no es válida o no se puede cargar la imagen)
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            label.setText("Imagen no disponible");
        }
    }
}
