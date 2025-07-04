package com.app.frontend.models;

import com.google.gson.annotations.SerializedName;

/*
* Clase para modelar las estadísticas de un partido de la clase modelo Partido.java
* @author Javier
*/
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

   

    public String getMapa() {
        return Mapa;
    }

    public void setMapa(String Mapa) {
        this.Mapa = Mapa;
    }

    public String getResultadoPartido() {
        return resultadoPartido;
    }

    public void setResultadoPartido(String resultadoPartido) {
        this.resultadoPartido = resultadoPartido;
    }

    public String getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public void setIdEquipoGanador(String idEquipoGanador) {
        this.idEquipoGanador = idEquipoGanador;
    }

    public String getNicknameJugador() {
        return nicknameJugador;
    }

    public void setNicknameJugador(String nicknameJugador) {
        this.nicknameJugador = nicknameJugador;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }
   
    public Integer getV1Count() {
        return safeParseInteger(v1Count);
    }
    
    public Double getAdr() {
        return safeParseDouble(ADR);
    }

    public void setV1Count(String v1Count) {
        this.v1Count = v1Count;
    }

    public String getV1Wins() {
        return v1Wins;
    }

    public void setV1Wins(String v1Wins) {
        this.v1Wins = v1Wins;
    }

    public String getV2Count() {
        return v2Count;
    }

    public void setV2Count(String v2Count) {
        this.v2Count = v2Count;
    }

    public String getV2Wins() {
        return v2Wins;
    }

    public void setV2Wins(String v2Wins) {
        this.v2Wins = v2Wins;
    }

    public String getADR() {
        return ADR;
    }

    public void setADR(String ADR) {
        this.ADR = ADR;
    }

    public String getAssists() {
        return Assists;
    }

    public void setAssists(String Assists) {
        this.Assists = Assists;
    }

    public String getClutchKills() {
        return clutchKills;
    }

    public void setClutchKills(String clutchKills) {
        this.clutchKills = clutchKills;
    }

    public String getDamage() {
        return Damage;
    }

    public void setDamage(String Damage) {
        this.Damage = Damage;
    }

    public String getDeaths() {
        return Deaths;
    }

    public void setDeaths(String Deaths) {
        this.Deaths = Deaths;
    }

    public String getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(String doubleKills) {
        this.doubleKills = doubleKills;
    }

    public String getEnemiesFlashed() {
        return enemiesFlashed;
    }

    public void setEnemiesFlashed(String enemiesFlashed) {
        this.enemiesFlashed = enemiesFlashed;
    }

    public String getEnemiesFlashedPerRound() {
        return enemiesFlashedPerRound;
    }

    public void setEnemiesFlashedPerRound(String enemiesFlashedPerRound) {
        this.enemiesFlashedPerRound = enemiesFlashedPerRound;
    }

    public String getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(String entryCount) {
        this.entryCount = entryCount;
    }

    public String getEntryWins() {
        return entryWins;
    }

    public void setEntryWins(String entryWins) {
        this.entryWins = entryWins;
    }

    public String getFirstKills() {
        return firstKills;
    }

    public void setFirstKills(String firstKills) {
        this.firstKills = firstKills;
    }

    public String getFlashCount() {
        return flashCount;
    }

    public void setFlashCount(String flashCount) {
        this.flashCount = flashCount;
    }

    public String getFlashSuccessRate() {
        return flashSuccessRate;
    }

    public void setFlashSuccessRate(String flashSuccessRate) {
        this.flashSuccessRate = flashSuccessRate;
    }

    public String getFlashSuccesses() {
        return flashSuccesses;
    }

    public void setFlashSuccesses(String flashSuccesses) {
        this.flashSuccesses = flashSuccesses;
    }

    public String getFlashesPerRound() {
        return flashesPerRound;
    }

    public void setFlashesPerRound(String flashesPerRound) {
        this.flashesPerRound = flashesPerRound;
    }

    public String getHeadshots() {
        return Headshots;
    }

    public void setHeadshots(String Headshots) {
        this.Headshots = Headshots;
    }

    public String getHeadshotsPercentage() {
        return headshotsPercentage;
    }

    public void setHeadshotsPercentage(String headshotsPercentage) {
        this.headshotsPercentage = headshotsPercentage;
    }

    public String getKdRatio() {
        return kdRatio;
    }

    public void setKdRatio(String kdRatio) {
        this.kdRatio = kdRatio;
    }

    public String getKrRatio() {
        return krRatio;
    }

    public void setKrRatio(String krRatio) {
        this.krRatio = krRatio;
    }

    public String getKills() {
        return Kills;
    }

    public void setKills(String Kills) {
        this.Kills = Kills;
    }

    public String getKnifeKills() {
        return knifeKills;
    }

    public void setKnifeKills(String knifeKills) {
        this.knifeKills = knifeKills;
    }

    public String getMVPs() {
        return MVPs;
    }

    public void setMVPs(String MVPs) {
        this.MVPs = MVPs;
    }

    public String getMatch1v1WinRate() {
        return match1v1WinRate;
    }

    public void setMatch1v1WinRate(String match1v1WinRate) {
        this.match1v1WinRate = match1v1WinRate;
    }

    public String getMatch1v2WinRate() {
        return match1v2WinRate;
    }

    public void setMatch1v2WinRate(String match1v2WinRate) {
        this.match1v2WinRate = match1v2WinRate;
    }

    public String getMatchEntryRate() {
        return matchEntryRate;
    }

    public void setMatchEntryRate(String matchEntryRate) {
        this.matchEntryRate = matchEntryRate;
    }

    public String getMatchEntrySuccessRate() {
        return matchEntrySuccessRate;
    }

    public void setMatchEntrySuccessRate(String matchEntrySuccessRate) {
        this.matchEntrySuccessRate = matchEntrySuccessRate;
    }

    public String getPentaKills() {
        return pentaKills;
    }

    public void setPentaKills(String pentaKills) {
        this.pentaKills = pentaKills;
    }

    public String getPistolKills() {
        return pistolKills;
    }

    public void setPistolKills(String pistolKills) {
        this.pistolKills = pistolKills;
    }

    public String getQuadroKills() {
        return quadroKills;
    }

    public void setQuadroKills(String quadroKills) {
        this.quadroKills = quadroKills;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public String getSniperKillRateMatch() {
        return sniperKillRateMatch;
    }

    public void setSniperKillRateMatch(String sniperKillRateMatch) {
        this.sniperKillRateMatch = sniperKillRateMatch;
    }

    public String getSniperKillRateRound() {
        return sniperKillRateRound;
    }

    public void setSniperKillRateRound(String sniperKillRateRound) {
        this.sniperKillRateRound = sniperKillRateRound;
    }

    public String getSniperKills() {
        return sniperKills;
    }

    public void setSniperKills(String sniperKills) {
        this.sniperKills = sniperKills;
    }

    public String getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(String tripleKills) {
        this.tripleKills = tripleKills;
    }

    public String getUtilityCount() {
        return utilityCount;
    }

    public void setUtilityCount(String utilityCount) {
        this.utilityCount = utilityCount;
    }

    public String getUtilityDamage() {
        return utilityDamage;
    }

    public void setUtilityDamage(String utilityDamage) {
        this.utilityDamage = utilityDamage;
    }

    public String getUtilityDamageSuccessRate() {
        return utilityDamageSuccessRate;
    }

    public void setUtilityDamageSuccessRate(String utilityDamageSuccessRate) {
        this.utilityDamageSuccessRate = utilityDamageSuccessRate;
    }

    public String getUtilityDamagePerRound() {
        return utilityDamagePerRound;
    }

    public void setUtilityDamagePerRound(String utilityDamagePerRound) {
        this.utilityDamagePerRound = utilityDamagePerRound;
    }

    public String getUtilityEnemies() {
        return utilityEnemies;
    }

    public void setUtilityEnemies(String utilityEnemies) {
        this.utilityEnemies = utilityEnemies;
    }

    public String getUtilitySuccessRate() {
        return utilitySuccessRate;
    }

    public void setUtilitySuccessRate(String utilitySuccessRate) {
        this.utilitySuccessRate = utilitySuccessRate;
    }

    public String getUtilitySuccesses() {
        return utilitySuccesses;
    }

    public void setUtilitySuccesses(String utilitySuccesses) {
        this.utilitySuccesses = utilitySuccesses;
    }

    public String getUtilityUsagePerRound() {
        return utilityUsagePerRound;
    }

    public void setUtilityUsagePerRound(String utilityUsagePerRound) {
        this.utilityUsagePerRound = utilityUsagePerRound;
    }

    public String getZeusKills() {
        return zeusKills;
    }

    public void setZeusKills(String zeusKills) {
        this.zeusKills = zeusKills;
    }

    public String getJugadorEquipo() {
        return jugadorEquipo;
    }

    public void setJugadorEquipo(String jugadorEquipo) {
        this.jugadorEquipo = jugadorEquipo;
    }
    

    
    
     
    // Métodos auxiliares para parsear
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
}
