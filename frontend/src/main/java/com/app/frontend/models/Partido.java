package com.app.frontend.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

 /* 
  * Clase modelo para modelar un partido de un jugador
  * @author Javier
  */
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
    
    @SerializedName("Equipo del jugador")
    private Equipo equipoJugador;
    
    @SerializedName("Equipo contrario")
    private Equipo equipoContrario;
    
    // Clase para modelar un equipo
    public static class Equipo {
        private String nombre;
        private String avatar;
        @SerializedName("team_id")
        private String teamId;
        private List<JugadorPartido> jugadores;
        
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public List<JugadorPartido> getJugadores() {
            return jugadores;
        }

        public void setJugadores(List<JugadorPartido> jugadores) {
            this.jugadores = jugadores;
        }
        
        
    }
    
    // Clase para modelar un jugador en un partido
    public static class JugadorPartido {
        private String nickname;
        private String avatar;
        @SerializedName("player_id")
        private String playerId;
        @SerializedName("skill_level")
        private int skillLevel;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPlayerId() {
            return playerId;
        }

        public void setPlayerId(String playerId) {
            this.playerId = playerId;
        }

        public int getSkillLevel() {
            return skillLevel;
        }

        public void setSkillLevel(int skillLevel) {
            this.skillLevel = skillLevel;
        }
        
        
    }
    
    
    // Getters y setters generados automáticamente
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
    
    public Equipo getEquipoJugador() {
        return equipoJugador;
    }
    
    public Equipo getEquipoContrario() {
        return equipoContrario;
    }
}
