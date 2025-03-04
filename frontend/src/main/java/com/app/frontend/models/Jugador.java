package com.app.frontend.models;

/**
 * Clase para modelar un Jugador como objeto
 * @author Javier
 */

public class Jugador {
    private String nickname; // nickname del jugador
    private int elo; // elo del jugador (puntuación)

    public Jugador(String nickname, int elo) {
        this.nickname = nickname;
        this.elo = elo;
    }

    public String getNickname() {
        return nickname;
    }

    public int getElo() {
        return elo;
    }

    @Override
    public String toString() { 
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", elo=" + elo +
                '}';
    }
}

