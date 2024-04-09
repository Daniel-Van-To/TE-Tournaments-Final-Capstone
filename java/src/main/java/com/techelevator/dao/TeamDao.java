package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;


public interface TeamDao {

    public Team createTeam(TeamDto newTeam);

    public Team getTeamById(int id);

    public Team getTeamByTeamName(String teamName);

    public List<Team> getTeamsByAcceptingMembers();
    public Request addTeamJoinRequest(RequestDto request);
    public List<User> getUsersOnTeam(int teamId);

}
