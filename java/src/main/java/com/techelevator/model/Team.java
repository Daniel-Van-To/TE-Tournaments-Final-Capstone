package com.techelevator.model;
import java.util.*;

import javax.validation.constraints.NotEmpty;

public class Team {

    @NotEmpty
    private int id;

    @NotEmpty
    private String name;
    private int teamCaptainId;

    @NotEmpty
    private String gameName;

    @NotEmpty
    private boolean acceptingMembers;

    public Team() { }

    public Team(int id, String name, int teamCaptainId, String gameName, boolean acceptingMembers) {
        this.id = id;
        this.name = name;
        this.teamCaptainId = teamCaptainId;
        this.gameName = gameName;
        this.acceptingMembers = acceptingMembers;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
