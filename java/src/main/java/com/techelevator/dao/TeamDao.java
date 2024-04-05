package com.techelevator.dao;

import com.techelevator.model.Team;

public interface TeamDao {

    public Team createTeam(Team newTeam);

    public Team getTeamById(int id);



}
