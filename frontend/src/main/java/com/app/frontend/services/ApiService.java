package com.app.frontend.services;

import com.app.frontend.models.EquipoJugador;
import com.app.frontend.models.EstadisticasPartido;
import com.app.frontend.models.Jugador;
import com.app.frontend.models.Partido;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
/**
 * Clase donde se gestionan las conexiones con las APIs propias de Flask
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
                return new Gson().fromJson(response.body(), Jugador.class); // Se convierte la respuesta a un objeto Jugador (utilizo GSON para ello) y se devuelve
            } else { // Si hay error, se muestra el codigo del estado
                System.err.println("Error en la respuesta a la API de Flask: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Conexión con Flask para sacar la Clasificación de una región
    public static String getClasificacionRegion(String region) {
        
        try {
            String url = API_URL + "clasificacion?region=" +region+"&juego=cs2&limite=100"; // URL con el endpoint para hacer la petición a Flask
            // Petición a la API y respuesta
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
            
            // Comprobar el estado de la respuesta y seguimiento de los errores (en caso de que los haya)
            if (respuesta.statusCode() == 200) {
                return respuesta.body(); // Si la API devuelve un código 200, se devuelve la respuesta de la API
            } else {
                System.err.println("Error en la respuesta a la API de Flask: " + respuesta.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }     
    }
    
    // Conexión con Flask para sacar la Clasificación de un país
    public static String getClasificacionPais(String region, String codigoPais) {       
        try {
            String url = API_URL + "clasificacion?region="+ region +"&juego=cs2&pais=" + codigoPais+"&limite=100";
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
            
            if (respuesta.statusCode() == 200) {
                return respuesta.body();
            } else {
                System.err.println("Error en la respuesta a la API de Flask: " + respuesta.statusCode());
                return null;
            }                        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }       
    }
    
    public static String getPosicionJugadorRegion(String region ,String playerID, String codigoPais) {
        try{
            String url = "";
            
            if (codigoPais.equals("")) { 
                url = API_URL + "posicion?region=" +region+"&juego=cs2&player_id=" +playerID;
            } else {
                url = API_URL + "posicion?region=" +region+"&juego=cs2&player_id=" +playerID+"&pais="+codigoPais;
            }
            
            
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
            
            if (respuesta.statusCode() == 200) {
                return respuesta.body();
            } else {
                System.err.println("Error en la respuesta con la API de Flask: " + respuesta.statusCode());
                return null;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Método para obtener el historial de partidos de un jugador
    public static List<Partido> getHistorialPartidos(String player_id, String juego, int limite) {
        try {
            String url = API_URL + "historial_partidos_jugador?player_id=" + player_id + "&juego=" + juego + "&limite=" + limite;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                Type partidoListType = new TypeToken<List<Partido>>(){}.getType();
                return gson.fromJson(response.body(), partidoListType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Método para extraer estadísticas de una partida
    public static EstadisticasPartido getEstadisticasPartido(String id_partido, String player_id) {
        try {
            String url = API_URL + "estadisticas_partido_jugador?id_partido=" + id_partido + "&player_id=" + player_id;


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Respuesta JSON completa: " + response.body());
                Gson gson = new Gson();
                return gson.fromJson(response.body(), EstadisticasPartido.class);
            } else {
                System.err.println("Error en la respuesta a la API de Flask: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Metodo para extraer los equipos que tiene asociados un jugador, junto con sus miembros y torneos jugados
    public static List<EquipoJugador> getEquiposJugador(String playerId) {
        try {

            String url = API_URL + "equipos_jugador?player_id=" + playerId;

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

            if (respuesta.statusCode() == 200) {
                //System.out.println(respuesta.body());
                Gson gson = new Gson();
                Type tipoListaEquipos = new TypeToken<List<EquipoJugador>>(){}.getType();
                return gson.fromJson(respuesta.body(), tipoListaEquipos);
            } else {
                System.err.println("Error en la respuesta a la API de Flask: " + respuesta.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
       
}
