package com.techelevator.model;

import java.util.List;

public class TournamentDto {

    private int tournamentId;
    private int hostId;
    private String tournamentName;
    private double entry_fee;
    private String gameName;
    //TODO Change acceptingTeams to a boolean
    private boolean acceptingTeams;
    //UserId is the current user; This variable is used to check if the current user is the host
    private int userId;
    private List<Team> participants;

    public TournamentDto() { }

    public TournamentDto(int tournamentId, int hostId, String tournamentName, double entry_fee, String gameName, boolean acceptingTeams) {
        this.tournamentId = tournamentId;
        this.hostId = hostId;
        this.tournamentName = tournamentName;
        this.entry_fee = entry_fee;
        this.gameName = gameName;
        this.acceptingTeams = acceptingTeams;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
