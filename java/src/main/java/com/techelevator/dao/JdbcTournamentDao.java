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
        String sql = "INSERT INTO tournament (host_id, tournament_name, entry_fee, game_name, accepting_teams) " +
                "VALUES (?,?,?,?,?) RETURNING tournament_id";



        if (gameDao.getGameByGameName(tournament.getGameName()) == null) {
            //TODO decide if we want this to just automatically add the game (how do we get max_players from here)
            throw new DaoException("Team cannot be added since the game is not in the system.");
        }

        //TODO Check if a Tournament with the same name and game exists

        try{
            int newTournamentId = jdbcTemplate.queryForObject(sql, int.class, tournament.getHostId(), tournament.getTournamentName(),
                    tournament.getEntry_fee(), tournament.getGameName(), tournament.getAcceptingTeams());
            //TODO Remember to link team to tournament

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
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams " +
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
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams " +
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
    @Override
    public List<Tournament> getOpenTournaments() {
        List<Tournament> openTournaments = new ArrayList<>();
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams " +
                "FROM tournament WHERE accepting_teams = 'o';";

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
    public List<Tournament> getTournamentsUserIsHost(UserDto user) {
        List<Tournament> tournamentsUserIsHost = new ArrayList<>();
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name, accepting_teams " +
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
        String sql = "UPDATE tournament SET tournament_name = ?, entry_fee = ?, accepting_teams = ? " +
                "WHERE tournament_id = ?;";
//        try{
//            int numberOfRows = jdbcTemplate.update(sql, team.getTeamName(), team.getTeamCaptainId(), team.isAcceptingMembers(), teamId);
//            if(numberOfRows == 0) {
//                throw new DaoException("Zero rows effected, expected atleast one");
//            }
//            else {
//                updatedTeam = getTeamById(teamId);
//            }
//        }
//        catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
        return null;
    }
    @Override
    public boolean checkIfUserIsTournamentHost(TournamentDto tournament) {
        return tournament.getUserId() == tournament.getHostId();
    }

    public Tournament mapRowToTournament(SqlRowSet rowSet){
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rowSet.getInt("tournament_id"));
        tournament.setHostId(rowSet.getInt("host_id"));
        tournament.setTournamentName(rowSet.getString("tournament_name"));
        tournament.setEntry_fee(rowSet.getDouble("entry_fee"));
        tournament.setGameName(rowSet.getString("game_name"));

        return tournament;
    }
}
