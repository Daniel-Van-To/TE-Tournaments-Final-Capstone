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
public class JdbcTeamDao implements TeamDao {

    private JdbcTemplate jdbcTemplate;
    private GameDao gameDao;
    private UserDao userDao;
    private RequestDao requestDao;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        gameDao = new JdbcGameDao(jdbcTemplate);
        userDao = new JdbcUserDao(jdbcTemplate);
        requestDao = new JdbcRequestDao(jdbcTemplate);
    }

    @Override
    public Team getTeamById(int id){
        Team team = null;
        String sql = "SELECT team_id, team_name, team_captain_id, game_name, accepting_members FROM team WHERE team_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()){
                team = mapRowToTeam(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return team;
    }

    @Override
    public Team getTeamByTeamName(String teamName) {
        Team team = null;
        String sql = "SELECT team_id, team_name, team_captain_id, game_name, accepting_members FROM team WHERE team_name = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamName);
            if(results.next()){
                team = mapRowToTeam(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return team;
    }

    @Override
    public Team createTeam(TeamDto newTeam) {

        Team createdTeam = null;
        String sql = "INSERT INTO team (team_name, team_captain_id, game_name, accepting_members)" +
                "VALUES (?, ?, ?, ?) RETURNING team_id;";

        if (gameDao.getGameByGameName(newTeam.getGameName()) == null) {
            //TODO decide if we want this to just automatically add the game (how do we get max_players from here)
            throw new DaoException("Team cannot be added since the game is not in the system.");
        }

        Team firstNameHolder = getTeamByTeamName(newTeam.getTeamName());
        boolean teamNameAlreadyExists = firstNameHolder != null;
        boolean sameNameAndSameGame = false;
        if (teamNameAlreadyExists) {
            sameNameAndSameGame = firstNameHolder.getGameName().equalsIgnoreCase(newTeam.getGameName());
        }
        if (teamNameAlreadyExists && sameNameAndSameGame) {
            throw new DaoException("Team already exists.");
        }

        try{
            User teamCaptain = userDao.getUserByUsername(newTeam.getUsername());
            int newTeamId = jdbcTemplate.queryForObject(sql, int.class, newTeam.getTeamName(), teamCaptain.getId(),
                    newTeam.getGameName(), newTeam.isAcceptingMembers());

            createdTeam = getTeamById(newTeamId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return createdTeam;
    }

    @Override
    public List<Team> getTeamsByAcceptingMembers() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT team_id, team_name, team_captain_id, game_name, accepting_members FROM team WHERE accepting_members = true ORDER BY team_id ASC;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                teams.add(mapRowToTeam(results));
            }

        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return teams;
    }

    @Override
    public Request addTeamJoinRequest(RequestDto request) {
        Request joinTeam = null;
        User requester = userDao.getUserByUsername(request.getUserName());
        String sql = "INSERT INTO request (team_id, request_status, requester_id)" +
                "VALUES (?, ?, ?) RETURNING request_id;";
        try{
            int request_id = jdbcTemplate.queryForObject(sql, int.class, request.getTeamId(), 'p', requester.getId());
            joinTeam = requestDao.getRequestByRequestId(request_id);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return joinTeam;
    }
    @Override
    public List<User> getUsersOnTeam(int teamId) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT users.user_id, users.username, users.role" +
                "FROM team" +
                "JOIN team_user ON team.team_id = team_user.team_id" +
                "JOIN users ON team_user.user_id = users.user_id" +
                "WHERE team.team_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
            while(results.next()){
                users.add(userDao.mapRowToUser(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return users;
    }

    public boolean unlinkUserFromTeam(int userId, int teamId) {

      return false;
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
