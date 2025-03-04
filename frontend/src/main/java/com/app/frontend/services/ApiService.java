package com.app.frontend.services;

import com.app.frontend.models.Jugador;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
/**
 *
 * @author Javier
 */


public class ApiService {
    private static final String API_URL = "http://127.0.0.1:5000/api/"; // URL base, mas adelante se añade el endpoint para cada caso

    public static Jugador getPlayerStats(String nickname) {
        try {
            
            String encodedNickname = URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString()); // Es necesario codificar el nickname en la URL porque si no da problemas al hacer la petición
            String url = API_URL + "player?nickname=" + encodedNickname; // URL completa con el nickname

            HttpClient client = HttpClient.newHttpClient(); // Objeto HTTP para hacer la petición
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build(); // Se manda la petición (en este caso GET)
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Respuesta que devuelve la petición

            // Al igual que en el Backend, se comprueba el estado de la respuesta
            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), Jugador.class); // Se convierte la respuesta a un objeto Jugador (utilizo GSON para ello)
            } else { // Si hay error, se muestra el codigo del estado
                System.err.println("Error en la respuesta: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            // Manejar excepciones (por ejemplo, problemas de conexión)
            e.printStackTrace();
            return null;
        }
    }
}
