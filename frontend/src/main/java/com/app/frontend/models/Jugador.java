package com.app.frontend.models;

/**
 * Clase para modelar un Jugador, se utilizará este modelo durante toda la aplicación.
 * Contiene datos relevantes del jugador que permitirán hacer peticiones a otras APIs para obtener más datos. (Por ejemplo player_id)
 * Se han recogido los datos que más interesan en esta clase para generar un modelo de Jugador con sus características
 * @author Javier
 */
public class Jugador {
    private String nickname;
    private String player_id; // Identificador
    private String pais;
    private String avatar;
    private String region_csgo;
    private int nivel_csgo;
    private int elo_csgo;
    private String region_cs2;
    private int nivel_cs2;
    private int elo_cs2;
    private String steam_id_64; // ID de la cuenta de Steam
    private String fecha_creacion_cuenta;
    
    public Jugador() {
    }

    public Jugador(String nickname, String player_id, String pais, String avatar, String region_csgo, int nivel_csgo, int elo_csgo, String region_cs2, int nivel_cs2, int elo_cs2, String steam_id_64, String fecha_creacion_cuenta) {
        this.nickname = nickname;
        this.player_id = player_id;
        this.pais = pais;
        this.avatar = avatar;
        this.region_csgo = region_csgo;
        this.nivel_csgo = nivel_csgo;
        this.elo_csgo = elo_csgo;
        this.region_cs2 = region_cs2;
        this.nivel_cs2 = nivel_cs2;
        this.elo_cs2 = elo_cs2;
        this.steam_id_64 = steam_id_64;
        this.fecha_creacion_cuenta = fecha_creacion_cuenta;
    }

    // Getters y Setters (Generados automáticamente con NetBeans)
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegion_csgo() {
        return region_csgo;
    }

    public void setRegion_csgo(String region_csgo) {
        this.region_csgo = region_csgo;
    }

    public int getNivel_csgo() {
        return nivel_csgo;
    }

    public void setNivel_csgo(int nivel_csgo) {
        this.nivel_csgo = nivel_csgo;
    }

    public int getElo_csgo() {
        return elo_csgo;
    }

    public void setElo_csgo(int elo_csgo) {
        this.elo_csgo = elo_csgo;
    }

    public String getRegion_cs2() {
        return region_cs2;
    }

    public void setRegion_cs2(String region_cs2) {
        this.region_cs2 = region_cs2;
    }

    public int getNivel_cs2() {
        return nivel_cs2;
    }

    public void setNivel_cs2(int nivel_cs2) {
        this.nivel_cs2 = nivel_cs2;
    }

    public int getElo_cs2() {
        return elo_cs2;
    }

    public void setElo_cs2(int elo_cs2) {
        this.elo_cs2 = elo_cs2;
    }
    
    public String getSteam_id_64() {
        return steam_id_64;
    }

    public void setSteam_id_64(String steam_id_64) {
        this.steam_id_64 = steam_id_64;
    }

    public String getFecha_creacion_cuenta() {
        return fecha_creacion_cuenta;
    }

    public void setFecha_creacion_cuenta(String fecha_creacion_cuenta) {
        this.fecha_creacion_cuenta = fecha_creacion_cuenta;
    }
}

