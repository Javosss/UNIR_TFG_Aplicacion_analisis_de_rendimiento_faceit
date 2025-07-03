package com.app.frontend.controllers;

import com.app.frontend.models.Jugador;
import com.app.frontend.models.ResumenEstadisticas;
import com.app.frontend.services.ApiService;
import com.app.frontend.utils.CargarImagenDesdeURL;
import com.app.frontend.views.Estadisticas;
import com.google.gson.Gson;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 * Classe controladora para la interfaz Estadisticas
 * @author Javier
 */
public class EstadisticasController {
    private final Estadisticas vista;
    private final Jugador jugador;
    
    public EstadisticasController(Estadisticas vista, Jugador jugador) {
        this.vista = vista;
        this.jugador = jugador;
        configurarTextAreasNoEditables();
        cargarImagenesMapas(); // Primero se tienen que cargar las imágenes
        cargarEstadisticas();
    }
       
    // Llamada a la API para extraer el resumen de estadisticas
    private void cargarEstadisticas() {
        String json = ApiService.getResumenEstadisticasJugador(jugador.getPlayer_id(), "cs2", 20);
        ResumenEstadisticas resumen = new Gson().fromJson(json, ResumenEstadisticas.class);
        mostrarEstadisticas(resumen);
        cargarEstadisticasMapas(resumen);
    }
    
    // Método para mostrar los datos cargados en los JTextArea de la interfaz
    private void mostrarEstadisticas(ResumenEstadisticas resumen) {
        
        Map<String, Double> stats = resumen.getEstadisticasPromedio();
        
        // Estadísticas individuales (textAreaEstadisticasIndividuales)
        String textoIndividuales = String.format(
            "Partidos analizados: %d\n" + "Victorias: %d\n" + "Winrate: %.2f%%\n\n" + "Kills: %.2f\n" + "Deaths: %.2f\n" + "Assists: %.2f\n" + "ADR: %.2f\n" +
            "K/D Ratio: %.2f\n" + "HS%%: %.2f\n" + "MVPs: %.2f\n" + "Damage: %.2f\n" + "K/R Ratio: %.2f\n" + "Headshots: %.2f",
                
            resumen.getPartidosAnalizados(),resumen.getVictorias(),resumen.getPorcentajeVictorias(),stats.get("Kills"),stats.get("Deaths"),
            stats.get("Assists"),stats.get("ADR"),stats.get("K/D Ratio"),stats.get("Headshots %"),stats.get("MVPs"),stats.get("Damage"),
            stats.get("K/R Ratio"),stats.get("Headshots")
        );
        vista.getTextAreaEstadisticasIndividuales().setText(textoIndividuales);
        
        // Estadísticas de clutches (textAreaClutching)
        String textoClutch = String.format("1v1: %.2f/%.2f (%.1f%%)\n" + "1v2: %.2f/%.2f (%.1f%%)\n" + "Clutch Kills: %.2f\n" + "Entry Count: %.2f\n" + "Entry Wins: %.2f\n" +
            "First Kills: %.2f\n" + "Match Entry Rate: %.2f\n" + "Match Entry Success Rate: %.2f",
                
            stats.get("1v1Wins"),stats.get("1v1Count"),stats.get("Match 1v1 Win Rate") * 100,stats.get("1v2Wins"),stats.get("1v2Count"),
            stats.get("Match 1v2 Win Rate") * 100,stats.get("Clutch Kills"),stats.get("Entry Count"),stats.get("Entry Wins"),stats.get("First_Kills"),
            stats.get("Match Entry Rate"),stats.get("Match Entry Success Rate")
        );
        vista.getTextAreaClutching().setText(textoClutch);
        
        // Estadísticas de utilidad (textAreaUtilidad)
        String textoUtilidad = String.format("Flash Count: %.2f\n" + "Flash Successes: %.2f\n" + "Enemies Flashed: %.2f\n" + "Utility Damage: %.2f\n" + "Utility Count: %.2f\n" +
            "Flashes per Round: %.2f\n" + "Flash Success Rate: %.2f\n" + "Enemies Flashed per Round: %.2f\n" + "Utility Enemies: %.2f\n" + "Utility Successes: %.2f\n" +
            "Utility Success Rate: %.2f\n" + "Utility Usage per Round: %.2f",
                
            stats.get("Flash Count"),stats.get("Flash Successes"),stats.get("Enemies Flashed"),stats.get("Utility Damage"),stats.get("Utility Count"),
            stats.get("Flashes per Round in a Match"),stats.get("Flash Success Rate per Match"),stats.get("Enemies Flashed per Round in a Match"),stats.get("Utility Enemies"),
            stats.get("Utility Successes"),stats.get("Utility Success Rate per Match"),stats.get("Utility Usage per Round")
        );
        vista.getTextAreaUtilidad().setText(textoUtilidad);
        
        // Estadísticas de armas (textAreaEstadisticasArmas)
        String textoArmas = String.format(
            "Pistol Kills: %.2f\n" + "Sniper Kills: %.2f\n" + "Double Kills: %.2f\n" + "Triple Kills: %.2f\n" + "Quadro Kills: %.2f\n" + "Penta Kills: %.2f\n" +
            "Knife Kills: %.2f\n" + "Zeus Kills: %.2f\n" + "Sniper Kill Rate (Match): %.2f\n" + "Sniper Kill Rate (Round): %.2f",
                
            stats.get("Pistol Kills"),stats.get("Sniper Kills"),stats.get("Double_Kills"),stats.get("Triple Kills"),stats.get("Quadro Kills"),
            stats.get("Penta Kills"),stats.get("Knife Kills"),stats.get("Zeus Kills"),stats.get("Sniper Kill Rate per Match"),
            stats.get("Sniper Kill Rate per Round")
        );
        vista.getTextAreaEstadisticasArmas().setText(textoArmas);
    }
    
    // Método para cargar las estadísticas de cada mapa individualmente
    private void cargarEstadisticasMapa(Map<String, Map<String, Double>> statsMapas, String nombreMapa, JTextArea textArea) {
        Map<String, Double> stats = statsMapas.get(nombreMapa);
        
        // Se utiliza un if porque hay mapas que no tienen por qué haber sido jugados para ese número de partidos
        if (stats != null) {
            String textoMapa = String.format(
                "Partidas: %d\n" + "Victorias: %d\n" + "Winrate: %.2f%%\n" + "Kills promedio: %.2f\n" + "ADR: %.2f\n" + "MVPs: %.2f",
                stats.get("Partidas jugadas").intValue(),stats.get("Victorias").intValue(),stats.get("Winrate (%)"),stats.get("Kills promedio"),stats.get("ADR promedio"),
                stats.get("MVPs promedio")
            );
            textArea.setText(textoMapa);
        } else {
            textArea.setText("No se ha jugado este mapa");
        }
    }
    // Se cargan al TextArea de cada mapa
    private void cargarEstadisticasMapas(ResumenEstadisticas resumen) {
        Map<String, Map<String, Double>> statsMapas = resumen.getEstadisticasPorMapa();

        cargarEstadisticasMapa(statsMapas, "de_dust2", vista.getTextAreaDust2());
        cargarEstadisticasMapa(statsMapas, "de_mirage", vista.getTextAreaMirage());
        cargarEstadisticasMapa(statsMapas, "de_nuke", vista.getTextAreaNuke());
        cargarEstadisticasMapa(statsMapas, "de_ancient", vista.getTextAreaAncient());
        cargarEstadisticasMapa(statsMapas, "de_inferno", vista.getTextAreaInferno());
        cargarEstadisticasMapa(statsMapas, "de_anubis", vista.getTextAreaAnubis());
        cargarEstadisticasMapa(statsMapas, "de_train", vista.getTextAreaTrain());
    }
    
    // Método para cargar las imágenes del mapa (para ello utilizo las imágenes de una Wiki de Counter Strike y las cargo con la función auxiliar desarrollada)
    private void cargarImagenesMapas() {
        
        // Tamaños
        int ancho = 160;
        int alto = 100;

        // Dust2
        CargarImagenDesdeURL.cargarImagen(vista.getImagenDust2(), 
            "https://static.wikia.nocookie.net/cswikia/images/1/16/Cs2_dust2.png/revision/latest/scale-to-width-down/1000?cb=20230913150804", 
            ancho, alto);
        // Mirage
        CargarImagenDesdeURL.cargarImagen(vista.getImagenMirage(), 
            "https://static.wikia.nocookie.net/cswikia/images/f/f5/De_mirage_cs2.png/revision/latest?cb=20230807124319", 
            ancho, alto);
        // Anubis
        CargarImagenDesdeURL.cargarImagen(vista.getImagenAnubis(), 
            "https://static.wikia.nocookie.net/cswikia/images/a/a0/CS2_Anubis_B_site.png/revision/latest?cb=20240731183709", 
            ancho, alto);
        // Nuke
        CargarImagenDesdeURL.cargarImagen(vista.getImagenNuke(), 
            "https://static.wikia.nocookie.net/cswikia/images/d/d6/De_nuke_cs2.png/revision/latest?cb=20240426010253", 
            ancho, alto);
        // Inferno
        CargarImagenDesdeURL.cargarImagen(vista.getImagenInferno(), 
            "https://static.wikia.nocookie.net/cswikia/images/1/17/Cs2_inferno_remake.png/revision/latest?cb=20240426005717", 
            ancho, alto);
        // Train
        CargarImagenDesdeURL.cargarImagen(vista.getImagenTrain(), 
            "https://static.wikia.nocookie.net/cswikia/images/2/2c/De_train_cs2_new.png/revision/latest?cb=20241114093902", 
            ancho, alto);
        // Ancient
        CargarImagenDesdeURL.cargarImagen(vista.getImagenAncient(), 
            "https://static.wikia.nocookie.net/cswikia/images/5/5c/De_ancient_cs2.png/revision/latest?cb=20240228213746", 
            ancho, alto);
    }
    
    // Hacer que el JTextArea de las estadísticas no sean esditables
    private void configurarTextAreasNoEditables() {
        vista.getTextAreaEstadisticasIndividuales().setEditable(false);
        vista.getTextAreaClutching().setEditable(false);
        vista.getTextAreaUtilidad().setEditable(false);
        vista.getTextAreaEstadisticasArmas().setEditable(false);
        vista.getTextAreaDust2().setEditable(false);
        vista.getTextAreaMirage().setEditable(false);
        vista.getTextAreaNuke().setEditable(false);
        vista.getTextAreaAncient().setEditable(false);
        vista.getTextAreaInferno().setEditable(false);
        vista.getTextAreaAnubis().setEditable(false);
        vista.getTextAreaTrain().setEditable(false);
    }
}
