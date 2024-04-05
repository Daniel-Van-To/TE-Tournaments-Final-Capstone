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
    private List<User> members;

    @NotEmpty
    private boolean acceptingMembers;
    private List<Tournament> enrolledTournaments;

    public Team() { }

    public Team(int id, String name, int teamCaptainId, String gameName, List<User> members, boolean acceptingMembers, List<Tournament> enrolledTournaments) {
        this.id = id;
        this.name = name;
        this.teamCaptainId = teamCaptainId;
        this.gameName = gameName;
        this.members = members;
        this.acceptingMembers = acceptingMembers;
        this.enrolledTournaments = enrolledTournaments;
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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public boolean isAcceptingMembers() {
        return acceptingMembers;
    }

    public void setAcceptingMembers(boolean acceptingMembers) {
        this.acceptingMembers = acceptingMembers;
    }

    public List<Tournament> getEnrolledTournaments() {
        return enrolledTournaments;
    }

    public void setEnrolledTournaments(List<Tournament> enrolledTournaments) {
        this.enrolledTournaments = enrolledTournaments;
    }

    public int getTeamCaptainId() {
        return teamCaptainId;
    }

    public void setTeamCaptainName(int teamCaptainId) {
        this.teamCaptainId = teamCaptainId;
    }
}
