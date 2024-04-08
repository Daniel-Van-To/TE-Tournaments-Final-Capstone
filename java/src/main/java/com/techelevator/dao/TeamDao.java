package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;

public interface TeamDao {

    public Team createTeam(TeamDto newTeam);

    public Team getTeamById(int id);

    public Team getTeamByTeamName(String teamName);

}
