package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import com.techelevator.model.UserDto;

import java.util.List;

public interface TournamentDao {

    public Tournament createTournament(TournamentDto tournament);
    public List<Tournament> getTournaments();
    public Tournament getTournamentById(int tournamentId);
    public List<Tournament> getOpenTournaments();
    public List<Tournament> getTournamentsUserIsHost(UserDto user);
    public int linkTeamToTournament(int team_id, int tournament_id);
    public int unlinkTeamFromTournament(int team_id, int tournament_id);
    public Tournament updateTournament(TournamentDto tournament, int tournamentId);
    public boolean checkIfUserIsTournamentHost(int tournamentId, int userId);
    public List<Tournament> getTournamentsByGameName(String gameName);
    public TournamentDto getTournamentDetailById(int tournamentId);
    public List<Team> getParticipatingTeams(int tournamentId);
    public int getmaximumPartipatingTeamsByTournamentId(int tournamentId);

}
