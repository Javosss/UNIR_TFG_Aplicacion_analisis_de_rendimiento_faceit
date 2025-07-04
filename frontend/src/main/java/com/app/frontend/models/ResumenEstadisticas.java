package com.app.frontend.models;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/**
 * Clase para modelar el resumen de estadisticas de un jugador para una serie de Partidos.
 * Además de estas estadísticas, se puede reutilizar la clase EstadisticasPartido para llenarla con los datos del resumen
 * del partido, porque es una agrupación de las EstadisticasPartido para muchos partidos, por ello se realiza de forma externa
 * @author Javier
 */
public class ResumenEstadisticas {
    @SerializedName("Partidos analizados")
    private int partidosAnalizados;
    
    @SerializedName("Victorias")
    private int victorias;
    
    @SerializedName("Porcentaje de victorias")
    private double porcentajeVictorias;
    
    @SerializedName("Estadísticas promedio por partido")
    private Map<String, Double> estadisticasPromedio;
    
    @SerializedName("Estadísticas por mapa")
    private Map<String, Map<String, Double>> estadisticasPorMapa;
    
    // Getters y setters generados con NetBeans
    public int getPartidosAnalizados() {
        return partidosAnalizados;
    }

    public void setPartidosAnalizados(int partidosAnalizados) {
        this.partidosAnalizados = partidosAnalizados;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public double getPorcentajeVictorias() {
        return porcentajeVictorias;
    }

    public void setPorcentajeVictorias(double porcentajeVictorias) {
        this.porcentajeVictorias = porcentajeVictorias;
    }

    public Map<String, Double> getEstadisticasPromedio() {
        return estadisticasPromedio;
    }

    public void setEstadisticasPromedio(Map<String, Double> estadisticasPromedio) {
        this.estadisticasPromedio = estadisticasPromedio;
    }

    public Map<String, Map<String, Double>> getEstadisticasPorMapa() {
        return estadisticasPorMapa;
    }

    public void setEstadisticasPorMapa(Map<String, Map<String, Double>> estadisticasPorMapa) {
        this.estadisticasPorMapa = estadisticasPorMapa;
    }   
}
