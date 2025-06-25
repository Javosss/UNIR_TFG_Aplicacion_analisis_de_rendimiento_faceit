package com.app.frontend.models;

import com.google.gson.annotations.SerializedName;

public class Partido {
    
    /* 
    Se utiliza @SerializedName ya que para la respuesta del JSON por la API de Flask, se utilizan nombres con espacios, entonces con esta
    anotación se pueden mapear atributos de la clase actual con los campos del JSON.
    */
    @SerializedName("Hora de comienzo")
    private String horaComienzo;
    
    @SerializedName("Hora de finalizacion")
    private String horaFinalizacion;
    
    @SerializedName("ID del partido")
    private String idPartido;
    
    private String Juego;
    private String Modo;
    
    @SerializedName("Nombre competicion")
    private String nombreCompeticion;
    
    private String Region;
    
    @SerializedName("Resultado del partido")
    private String resultadoPartido;
    
    @SerializedName("Tipo competicion")
    private String tipoCompeticion;
    
    private String Score;

    public String getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public String getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(String horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getJuego() {
        return Juego;
    }

    public void setJuego(String Juego) {
        this.Juego = Juego;
    }

    public String getModo() {
        return Modo;
    }

    public void setModo(String Modo) {
        this.Modo = Modo;
    }

    public String getNombreCompeticion() {
        return nombreCompeticion;
    }

    public void setNombreCompeticion(String nombreCompeticion) {
        this.nombreCompeticion = nombreCompeticion;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getResultadoPartido() {
        return resultadoPartido;
    }

    public void setResultadoPartido(String resultadoPartido) {
        this.resultadoPartido = resultadoPartido;
    }

    public String getTipoCompeticion() {
        return tipoCompeticion;
    }

    public void setTipoCompeticion(String tipoCompeticion) {
        this.tipoCompeticion = tipoCompeticion;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String Score) {
        this.Score = Score;
    }
    
    
    @Override
    public String toString() {
        return "Partido{" +
               "horaComienzo='" + horaComienzo + '\'' +
               ", horaFinalizacion='" + horaFinalizacion + '\'' +
               ", idPartido='" + idPartido + '\'' +
               ", juego='" + Juego + '\'' +
               ", modo='" + Modo + '\'' +
               ", nombreCompeticion='" + nombreCompeticion + '\'' +
               ", region='" + Region + '\'' +
               ", resultadoPartido='" + resultadoPartido + '\'' +
               ", tipoCompeticion='" + tipoCompeticion + '\'' +
               '}';
    }
}
