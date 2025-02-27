package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.*;

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
    //TODO add a list of members on the team
    private List<User> members;
    private int maximumMembers;

    public TeamDto() { }

    public TeamDto(int teamId, String teamName, int teamCaptainId, String gameName, boolean acceptingMembers, String username, List<User> members, int maximumMembers) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCaptainId = teamCaptainId;
        this.gameName = gameName;
        this.acceptingMembers = acceptingMembers;
        this.username = username;
        this.members = members;
        this.maximumMembers = maximumMembers;

    }

    public int getMaximumMembers() {
        return maximumMembers;
    }

    public void setMaximumMembers(int maximumMembers) {
        this.maximumMembers = maximumMembers;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
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
