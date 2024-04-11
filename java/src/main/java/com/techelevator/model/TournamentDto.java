package com.techelevator.model;

public class TournamentDto {

    private int tournamentId;
    private int hostId;
    private String tournamentName;
    private double entry_fee;
    private String gameName;


    public TournamentDto(int tournamentId, int hostId, String tournamentName, double entry_fee, String gameName) {
        this.tournamentId = tournamentId;
        this.hostId = hostId;
        this.tournamentName = tournamentName;
        this.entry_fee = entry_fee;
        this.gameName = gameName;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public double getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(double entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
