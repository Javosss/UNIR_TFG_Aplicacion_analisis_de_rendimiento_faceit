package com.app.frontend.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase modelo para modelar un Equipo de un jugador
 * @author Javier
 */
public class EquipoJugador {
    /* 
    Se utiliza @SerializedName ya que para la respuesta del JSON por la API de Flask, se utilizan nombres con espacios, entonces con esta
    anotación se pueden mapear atributos de la clase actual con los campos del JSON.
    */
    
    @SerializedName("Chat room ID")
    private String idChatroom;
    
    @SerializedName("Faceit URL")
    private String faceitURL;
    
    @SerializedName("Foto del equipo")
    private String fotoEquipo;
    
    @SerializedName("ID Equipo")
    private String idEquipo;
    
    @SerializedName("Juego")
    private String juego;
    
    @SerializedName("Lider")
    private String lider;
    
    @SerializedName("Nickname equipo")
    private String nicknameEquipo;
    
    @SerializedName("Nombre equipo")
    private String nombreEquipo;
    
    @SerializedName("Tipo equipo")
    private String tipoEquipo;
    
    @SerializedName("Total miembros")
    private int totalMiembros;
    
    @SerializedName("Torneos")
    private List<Torneo> torneos;
    
    @SerializedName("Miembros")
    private List<Miembro> miembros;
    
    // Se define una clase interna para modelar los miembros del equipo (Se hace dentro de esta propia clase porque realmente debería pertenecer a esta y no como externa)
    public static class Miembro {
              
        @SerializedName("avatar")
        private String avatar;
        
        @SerializedName("country")
        private String pais;
        
        @SerializedName("faceit_url")
        private String faceitUrl;
        
        @SerializedName("memberships")
        private List<String> membresias = new ArrayList<>();
        
        @SerializedName("nickname")
        private String apodo;
        
        @SerializedName("user_id")
        private String idUsuario;
        
        public String getApodo() {
            return apodo;
        }

        public String getPais() {
            return pais;
        }

        public List<String> getMembresias() {
            return membresias != null ? membresias : Collections.emptyList();
        }

        public String getFaceitUrl() {
            return faceitUrl;
        }
        
        public String getIdUsuario() {
            return idUsuario;
        }
        public String getAvatar() {
            return avatar;
        }
    }
    
    // Se hace de la misma manera para los torneos que ha jugado un equipo
    public static class Torneo {
        @SerializedName("Nombre del torneo")
        private String nombre;

        @SerializedName("Juego")
        private String juego;

        @SerializedName("Tipo partido")
        private String tipoPartido;

        @SerializedName("Region")
        private String region;

        @SerializedName("Numero de jugadores")
        private int numeroJugadores;

        @SerializedName("Hora de inicio")
        private String horaInicio;

        @SerializedName("URL del torneo")
        private String urlTorneo;
        
        public String getNombre() {
            return nombre;
        }

        public String getRegion() {
            return region;
        }

        public String getHoraInicio() {
            return horaInicio;
        }

        public int getNumeroJugadores() {
            return numeroJugadores;
        }

        public String getUrlTorneo() {
            return urlTorneo;
        }
        
        public String getJuego() {
            return juego;
        }
    }
    
    
    public List<Miembro> getMiembros() {
        return miembros;
    }

    public List<Torneo> getTorneos() {
        return torneos;
    }
    // Getters y setters de la clase EquipoJugador generados automáticamente con NetBeans
    public String getIdChatroom() {
        return idChatroom;
    }

    public void setIdChatroom(String idChatroom) {
        this.idChatroom = idChatroom;
    }

    public String getFaceitURL() {
        return faceitURL;
    }

    public void setFaceitURL(String faceitURL) {
        this.faceitURL = faceitURL;
    }

    public String getFotoEquipo() {
        return fotoEquipo;
    }

    public void setFotoEquipo(String fotoEquipo) {
        this.fotoEquipo = fotoEquipo;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public String getNicknameEquipo() {
        return nicknameEquipo;
    }

    public void setNicknameEquipo(String nicknameEquipo) {
        this.nicknameEquipo = nicknameEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public int getTotalMiembros() {
        return totalMiembros;
    }

    public void setTotalMiembros(int totalMiembros) {
        this.totalMiembros = totalMiembros;
    }
    
    
    
}
