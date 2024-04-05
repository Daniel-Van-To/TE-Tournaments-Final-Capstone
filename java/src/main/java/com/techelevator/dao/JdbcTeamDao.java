package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Team;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;

@Service
public class JdbcTeamDao implements TeamDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public Team createTeam(Team newTeam) {
        //TODO we need to decide how to handle this
        //this method needs to check for :
        //the team does not already exists
        //the game the team is playing exists
        Team createdTeam = null;
        String sql = "INSERT INTO team (team_name, team_captain_id, game_name, accepting_members)" +
                "VALUES (?, ?, ?, ?) RETURNING team_id;" ;

        try{
            int newTeamId = jdbcTemplate.queryForObject(sql, int.class, (newTeam.getName()), newTeam.getTeamCaptainId(),
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

    public Team mapRowToTeam(SqlRowSet rowSet){
        Team team = new Team();
        team.setId(rowSet.getInt("team_id"));
        team.setName(rowSet.getString("team_name"));
        team.setTeamCaptainId(rowSet.getInt("team_captain_id"));
        team.setGameName(rowSet.getString("game_name"));
        team.setAcceptingMembers(rowSet.getBoolean("accepting_members"));

        return team;
    }




}
