package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class Team {

    @NotEmpty
    private int teamId;

    @NotEmpty
    private String teamName;
    private int teamCaptainId;

    @NotEmpty
    private String gameName;

    @NotEmpty
    private boolean acceptingMembers;

    public Team() { }

    public Team(int teamId, String teamName, int teamCaptainId, String gameName, boolean acceptingMembers) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCaptainId = teamCaptainId;
        this.gameName = gameName;
        this.acceptingMembers = acceptingMembers;

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
