package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;

import java.util.List;


public interface TeamDao {

    public Team createTeam(TeamDto newTeam);

    public Team getTeamById(int id);

    public Team getTeamByTeamName(String teamName);

    public List<Team> getTeamsByAcceptingMembers();

}
