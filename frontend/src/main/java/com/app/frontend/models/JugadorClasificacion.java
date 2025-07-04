package com.app.frontend.models;

/**
 * Clase para modelar los Jugadores de un ranking.
 * Se utiliza para añadir los Jugadores a las tablas de clasificación y solo contiene los datos que se van a añadir a la tabla.
 * @author Javier
 */
public class JugadorClasificacion {
    // Es importante que los nombres sean los mismos que los que devuelve el JSON de la API de Flask, si no da errores
    private String country;
    private int faceit_elo;
    private String nickname;
    private String player_id; // Identificador
    private int posicion;

    public JugadorClasificacion(String country, int faceit_elo, String nickname, String player_id, int posicion) {
        this.country = country;
        this.faceit_elo = faceit_elo;
        this.nickname = nickname;
        this.player_id = player_id;
        this.posicion = posicion;
    }
    
    // Getters y Setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFaceit_elo() {
        return faceit_elo;
    }

    public void setFaceit_elo(int faceit_elo) {
        this.faceit_elo = faceit_elo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }    
}
