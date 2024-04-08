package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class TeamDto {

    @NotEmpty
    private String username;
    private int teamId;
    @NotEmpty
    private String teamName;
    private int teamCaptainId;
    @NotEmpty
    private String gameName;
    private boolean acceptingMembers;

    public TeamDto() { }

    public TeamDto(int teamId, String teamName, int teamCaptainId, String gameName, boolean acceptingMembers, String username) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCaptainId = teamCaptainId;
        this.gameName = gameName;
        this.acceptingMembers = acceptingMembers;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isAcceptingMembers() {
        return acceptingMembers;
    }

    public void setAcceptingMembers(boolean acceptingMembers) {
        this.acceptingMembers = acceptingMembers;
    }


    public int getTeamCaptainId() {
        return teamCaptainId;
    }

    public void setTeamCaptainId(int teamCaptainId) {
        this.teamCaptainId = teamCaptainId;
    }



}
