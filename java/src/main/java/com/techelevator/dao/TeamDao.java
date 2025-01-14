package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;


public interface TeamDao {

    public Team createTeam(TeamDto newTeam);
    public List<Team> getAllTeams();

    public Team getTeamById(int id);

    public Team getTeamByTeamName(String teamName);

    public List<Team> getTeamsByAcceptingMembers();
    public Request addTeamJoinRequest(RequestDto request);
    public List<User> getUsersOnTeam(int teamId);
    public int linkUserToTeam(int userId, int teamId);
    public int unlinkUserFromTeam(int userId, int teamId);
    public Team updateTeam(TeamDto team, int teamId);
    public boolean checkIfUserIsTeamCaptain(int teamId, int userId);
    public List<Team> getTeamsUserIsCaptain(int userId);
    public boolean checkIfTeamIsFull(int teamId);

}
