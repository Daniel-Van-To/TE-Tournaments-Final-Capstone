package com.techelevator.model;

public class RequestDto {

    private int requestId;
    private int tournamentId;
    private int teamId;
    private String gameName;
    private char requestStatus;
    private int requesterId;
    private String userName;

    public RequestDto(){

    }
    public RequestDto(int requestId, int tournamentId, int teamId, String gameName, char requestStatus, int requesterId, String userName) {
        this.requestId = requestId;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
        this.gameName = gameName;
        this.requestStatus = requestStatus;
        this.requesterId = requesterId;
        this.userName = userName;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public char getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(char requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
