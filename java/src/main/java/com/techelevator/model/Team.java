package com.techelevator.model;
import java.util.*;

public class Team {

    private int id;
    private String name;
    private String teamCaptainName;
    private String gameName;
    private List<User> members;
    private boolean acceptingMembers;
    private List<Tournament> enrolledTournaments;

    public Team(int id, String name, String teamCaptainName, String gameName, List<User> members, boolean acceptingMembers, List<Tournament> enrolledTournaments) {
        this.id = id;
        this.name = name;
        this.teamCaptainName = teamCaptainName;
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

    public String getTeamCaptainName() {
        return teamCaptainName;
    }

    public void setTeamCaptainName(String teamCaptainName) {
        this.teamCaptainName = teamCaptainName;
    }
}
