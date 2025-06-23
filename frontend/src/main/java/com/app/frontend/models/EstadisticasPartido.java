package com.app.frontend.models;

import com.google.gson.annotations.SerializedName;

public class EstadisticasPartido {
    /* 
    Al igual que para la clase Partido, se utiliza @SerializedName ya que para la respuesta del JSON por la API de Flask, se utilizan nombres con espacios, entonces con esta
    anotación se pueden mapear atributos de la clase actual con los campos del JSON.
    */
    private String Mapa;
    
    @SerializedName("Resultado del partido")
    private String resultadoPartido;
    
    @SerializedName("ID del equipo ganardor")
    private String idEquipoGanador;
    
    @SerializedName("Nickname del jugador")
    private String nicknameJugador;
    
    @SerializedName("ID del equipo")
    private String idEquipo;
    
    @SerializedName("1v1Count")
    private String v1Count;
    
    @SerializedName("1v1Wins")
    private String v1Wins;
    
    @SerializedName("1v2Count")
    private String v2Count;
    
    @SerializedName("1v2Wins")
    private String v2Wins;
    
    private String ADR;
    private String Assists;
    
    @SerializedName("Clutch Kills")
    private String clutchKills;
    
    private String Damage;
    private String Deaths;
    
    @SerializedName("Double_Kills")
    private String doubleKills;
    
    @SerializedName("Enemies Flashed")
    private String enemiesFlashed;
    
    @SerializedName("Enemies Flashed per Round in a Match")
    private String enemiesFlashedPerRound;
    
    @SerializedName("Entry Count")
    private String entryCount;
    
    @SerializedName("Entry Wins")
    private String entryWins;
    
    @SerializedName("First_Kills")
    private String firstKills;
    
    @SerializedName("Flash Count")
    private String flashCount;
    
    @SerializedName("Flash Success Rate per Match")
    private String flashSuccessRate;
    
    @SerializedName("Flash Successes")
    private String flashSuccesses;
    
    @SerializedName("Flashes per Round in a Match")
    private String flashesPerRound;
    
    private String Headshots;
    
    @SerializedName("Headshots %")
    private String headshotsPercentage;
    
    @SerializedName("K/D Ratio")
    private String kdRatio;
    
    @SerializedName("K/R Ratio")
    private String krRatio;
    
    private String Kills;
    
    @SerializedName("Knife Kills")
    private String knifeKills;
    
    private String MVPs;
    
    @SerializedName("Match 1v1 Win Rate")
    private String match1v1WinRate;
    
    @SerializedName("Match 1v2 Win Rate")
    private String match1v2WinRate;
    
    @SerializedName("Match Entry Rate")
    private String matchEntryRate;
    
    @SerializedName("Match Entry Success Rate")
    private String matchEntrySuccessRate;
    
    @SerializedName("Penta Kills")
    private String pentaKills;
    
    @SerializedName("Pistol Kills")
    private String pistolKills;
    
    @SerializedName("Quadro Kills")
    private String quadroKills;
    
    private String Result;
    
    @SerializedName("Sniper Kill Rate per Match")
    private String sniperKillRateMatch;
    
    @SerializedName("Sniper Kill Rate per Round")
    private String sniperKillRateRound;
    
    @SerializedName("Sniper Kills")
    private String sniperKills;
    
    @SerializedName("Triple Kills")
    private String tripleKills;
    
    @SerializedName("Utility Count")
    private String utilityCount;
    
    @SerializedName("Utility Damage")
    private String utilityDamage;
    
    @SerializedName("Utility Damage Success Rate per Match")
    private String utilityDamageSuccessRate;
    
    @SerializedName("Utility Damage per Round in a Match")
    private String utilityDamagePerRound;
    
    @SerializedName("Utility Enemies")
    private String utilityEnemies;
    
    @SerializedName("Utility Success Rate per Match")
    private String utilitySuccessRate;
    
    @SerializedName("Utility Successes")
    private String utilitySuccesses;
    
    @SerializedName("Utility Usage per Round")
    private String utilityUsagePerRound;
    
    @SerializedName("Zeus Kills")
    private String zeusKills;
    
    @SerializedName("jugador_equipo")
    private String jugadorEquipo;

    // Getters para campos numéricos con conversión segura
    public Integer getV1Count() {
        return safeParseInteger(v1Count);
    }
    
    public Double getAdr() {
        return safeParseDouble(ADR);
    }

    public String getAssists() {
        return Assists;
    }

    public void setAssists(String Assists) {
        this.Assists = Assists;
    }

    public String getDeaths() {
        return Deaths;
    }

    public void setDeaths(String Deaths) {
        this.Deaths = Deaths;
    }

    public String getKills() {
        return Kills;
    }

    public void setKills(String Kills) {
        this.Kills = Kills;
    }
    
   
    
    // Métodos auxiliares para parseo seguro
    private Integer safeParseInteger(String value) {
        if (value == null) return null;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private Double safeParseDouble(String value) {
        if (value == null) return null;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        return "EstadisticasPartido{" +
               "mapa='" + Mapa + '\'' +
               ", resultadoPartido='" + resultadoPartido + '\'' +
               ", kills=" + getKills() +
               ", deaths=" + getDeaths() +
               ", assists=" + getAssists() +
               ", adr=" + getAdr() +
               '}';
    }
    
    
}
