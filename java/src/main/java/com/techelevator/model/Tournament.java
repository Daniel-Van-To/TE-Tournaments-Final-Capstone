package com.techelevator.model;

public class Tournament {

    private int tournamentId;
    private int hostId;
    private String tournamentName;
    private double entry_fee;
    private String gameName;
    //TODO Change acceptingTeams to a boolean
    private boolean acceptingTeams;
    private char tournamentStatus;

    public Tournament() { }

    public Tournament(int tournamentId, int hostId, String tournamentName, double entry_fee, String gameName, boolean acceptingTeams, char tournamentStatus) {
        this.tournamentId = tournamentId;
        this.hostId = hostId;
        this.tournamentName = tournamentName;
        this.entry_fee = entry_fee;
        this.gameName = gameName;
        this.acceptingTeams = acceptingTeams;
        this.tournamentStatus = tournamentStatus;
    }

    public boolean isAcceptingTeams() {
        return acceptingTeams;
    }

    public char getTournamentStatus() {
        return tournamentStatus;
    }

    public void setTournamentStatus(char tournamentStatus) {
        this.tournamentStatus = tournamentStatus;
    }

    public boolean getAcceptingTeams() {
        return acceptingTeams;
    }

    public void setAcceptingTeams(boolean acceptingTeams) {
        this.acceptingTeams = acceptingTeams;
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
