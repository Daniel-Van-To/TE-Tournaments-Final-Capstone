package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

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
        String sql = "INSERT INTO tournament (host_id, tournament_name, entry_fee, game_name) " +
                "VALUES (?,?,??) RETURNING tournament_id";



        if (gameDao.getGameByGameName(tournament.getGameName()) == null) {
            //TODO decide if we want this to just automatically add the game (how do we get max_players from here)
            throw new DaoException("Team cannot be added since the game is not in the system.");
        }

        //TODO Check if a Tournament with the same name and game exists

        try{
            int newTournamentId = jdbcTemplate.queryForObject(sql, int.class, tournament.getHostId(), tournament.getTournamentName(),
                    tournament.getEntry_fee(), tournament.getGameName());
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
        return null;
    }
    @Override
    public Tournament getTournamentById(int tournamentId) {
        Tournament tournament = null;
        String sql = "SELECT tournament_id, host_id, tournament_name, entry_fee, game_name " +
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
        return null;
    }
    @Override
    public List<Tournament> getTournamentsUserIsHost(UserDto user) {
        return null;
    }
    @Override
    public int linkTeamToTournament(int team_id, int tournament_id) {
        return 0;
    }
    @Override
    public int unlinkTeamFromTournament(int team_id, int tournament_id) {
        return 0;
    }
    @Override
    public Tournament updateTournament(TournamentDto tournament) {
        return null;
    }
    @Override
    public boolean checkIfUserIsTournamentHost(TournamentDto tournament) {
        return false;
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
