package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcTournamentDao implements TournamentDao{
    private JdbcTemplate jdbcTemplate;
    private GameDao gameDao;
    private UserDao userDao;
    private RequestDao requestDao;

    public JdbcTournamentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        gameDao = new JdbcGameDao(jdbcTemplate);
        userDao = new JdbcUserDao(jdbcTemplate);
        requestDao = new JdbcRequestDao(jdbcTemplate);
    }
    @Override
    public Tournament createTournament(TournamentDto tournament) {
        Tournament createdTournament = null;
        String sql = "INSERT INTO tournament (host_id, tournament_name, entry_fee, game_name, accepting_teams, tournament_status, maximum_participants) " +
                "VALUES (?,?,?,?,?,?,?) RETURNING tournament_id";



        if (gameDao.getGameByGameName(tournament.getGameName()) == null) {
            //TODO decide if we want this to just automatically add the game (how do we get max_players from here)
            throw new DaoException("Team cannot be added since the game is not in the system.");
        }

        //TODO Check if a Tournament with the same name and game exists

        try{
            int newTournamentId = jdbcTemplate.queryForObject(sql, int.class, tournament.getHostId(), tournament.getTournamentName(),
                    tournament.getEntry_fee(), tournament.getGameName(), tournament.getAcceptingTeams(), tournament.getTournamentStatus(), tournament.getMaximumParticipants());

            createdTournament = getTournamentById(newTournamentId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return createdTournament;
    }
    @Override
    public List<Tournament> getTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams, tournament_status, maximum_participants " +
                "FROM tournament;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                tournaments.add(mapRowToTournament(results));
            }

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return tournaments;
    }
    @Override
    public Tournament getTournamentById(int tournamentId) {
        Tournament tournament = null;
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams, tournament_status, maximum_participants " +
                "FROM tournament WHERE tournament_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
            if(results.next()){
                tournament = mapRowToTournament(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return tournament;
    }
    public List<Team> getParticipatingTeams(int tournamentId) {
        Tournament info = getTournamentById(tournamentId);
        List<Team> participatingTeams = new ArrayList<>();
        String sql = "SELECT team.team_id, team.team_name, team.team_captain_id, team.game_name, team.accepting_members " +
                "FROM tournament " +
                "JOIN team_tournament ON tournament.tournament_id = team_tournament.tournament_id " +
                "JOIN team ON team_tournament.team_id = team.team_id " +
                "WHERE tournament.tournament_id = ? ";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
            while(results.next()){
                participatingTeams.add(mapRowToTeam(results));
            }
            return participatingTeams;
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    @Override
    public TournamentDto getTournamentDetailById(int tournamentId) {
        Tournament info = getTournamentById(tournamentId);
        List<Team> participatingTeams = new ArrayList<>();
        String sql = "SELECT team.team_id, team.team_name, team.team_captain_id, team.game_name, team.accepting_members " +
                "FROM tournament " +
                "JOIN team_tournament ON tournament.tournament_id = team_tournament.tournament_id " +
                "JOIN team ON team_tournament.team_id = team.team_id " +
                "WHERE tournament.tournament_id = ? ";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
            while(results.next()){
                participatingTeams.add(mapRowToTeam(results));
            }
            TournamentDto detailedTournament = new TournamentDto(info.getTournamentId(), info.getHostId(), info.getTournamentName(),
                    info.getEntry_fee(), info.getGameName(), info.getAcceptingTeams(), participatingTeams, info.getTournamentStatus(),
                    info.getMaximumParticipants());
            return detailedTournament;

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    @Override
    public List<Tournament> getOpenTournaments() {
        List<Tournament> openTournaments = new ArrayList<>();
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams, tournament_status " +
                "FROM tournament WHERE accepting_teams = true;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                openTournaments.add(mapRowToTournament(results));
            }

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return openTournaments;
    }
    @Override
    public List<Tournament> getTournamentsByGameName(String gameName) {
        List<Tournament> tournamentsWithGameName = new ArrayList<>();
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams, tournament_status " +
                "FROM tournament WHERE game_name = ?;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameName);
            while (results.next()) {
                tournamentsWithGameName.add(mapRowToTournament(results));
            }

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return tournamentsWithGameName;
    }
    @Override
    public List<Tournament> getTournamentsUserIsHost(UserDto user) {
        List<Tournament> tournamentsUserIsHost = new ArrayList<>();
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams, tournament_status " +
                "FROM tournament WHERE host_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user.getId());
            while (results.next()) {
                tournamentsUserIsHost.add(mapRowToTournament(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return tournamentsUserIsHost;
    }
    @Override
    public int linkTeamToTournament(int teamId, int tournamentId) {
        int numRows = 0;
        String sql = "Insert INTO team_tournament(team_id, tournament_id" +
                "VALUES (?,?)";
        try{
            numRows = jdbcTemplate.update(sql, teamId, tournamentId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numRows;
    }
    @Override
    public int unlinkTeamFromTournament(int teamId, int tournamentId) {
        int numRows = 0;
        String sql = "DELETE FROM team_tournament WHERE team_id = ? AND tournament_id = ?;";
        try{
            numRows = jdbcTemplate.update(sql, teamId, tournamentId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numRows;
    }
    @Override
    public Tournament updateTournament(TournamentDto tournament, int tournamentId) {
        Tournament updatedTournament = null;
        String sql = "UPDATE tournament SET tournament_name = ?, entry_fee = ?, accepting_teams = ?, tournament_status = ?, maximum_participants = ? " +
                "WHERE tournament_id = ?;";
        try{
            int numberOfRows = jdbcTemplate.update(sql, tournament.getTournamentName(), tournament.getEntry_fee(),
                    tournament.getAcceptingTeams(), tournament.getTournamentStatus(),
                    tournament.getMaximumParticipants(), tournamentId);
            if(numberOfRows == 0) {
                throw new DaoException("Zero rows effected, expected atleast one");
            }
            else {
                updatedTournament = getTournamentById(tournamentId);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedTournament;
    }
    @Override
    public boolean checkIfUserIsTournamentHost(int tournamentId, int userId) {
        Tournament currentTournament = getTournamentById(tournamentId);
        return currentTournament.getHostId() == userId;
    }
    @Override
    public int getmaximumPartipatingTeamsByTournamentId(int tournamentId) {
        Tournament info = null;
        int maximumParticiapnts = 0;

        try {
            info = getTournamentById(tournamentId);
            maximumParticiapnts = info.getMaximumParticipants();

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return maximumParticiapnts;
    }

    public Tournament mapRowToTournament(SqlRowSet rowSet){
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rowSet.getInt("tournament_id"));
        tournament.setHostId(rowSet.getInt("host_id"));
        tournament.setTournamentName(rowSet.getString("tournament_name"));
        tournament.setEntry_fee(rowSet.getDouble("entry_fee"));
        tournament.setGameName(rowSet.getString("game_name"));
        tournament.setAcceptingTeams(rowSet.getBoolean("accepting_teams"));
        tournament.setTournamentStatus(rowSet.getString("tournament_status").charAt(0));
        tournament.setMaximumParticipants(rowSet.getInt("maximum_participants"));

        return tournament;
    }
    public Team mapRowToTeam(SqlRowSet rowSet){
        Team team = new Team();
        team.setTeamId(rowSet.getInt("team_id"));
        team.setTeamName(rowSet.getString("team_name"));
        team.setTeamCaptainId(rowSet.getInt("team_captain_id"));
        team.setGameName(rowSet.getString("game_name"));
        team.setAcceptingMembers(rowSet.getBoolean("accepting_members"));

        return team;
    }
}
