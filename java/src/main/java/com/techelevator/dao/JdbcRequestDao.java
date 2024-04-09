package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import com.techelevator.model.Team;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcRequestDao implements RequestDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Request getRequestByRequestId(int requestId) {
        Request request = null;
        String sql = "SELECT request_id, tournament_id, team_id, game_name, request_status, requester_id FROM request" +
                "WHERE request_id = ?;";
        try{
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql, requestId );
           if(results.next()){
               request = mapRowToRequest(results);
           }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return request;
    }
    public Request mapRowToRequest(SqlRowSet rowSet){
        Request request = new Request();
        if(rowSet.getString("team_id") != null && rowSet.getString("team_id") != "") {
            request.setTeamId(rowSet.getInt("team_id"));
        }
        if(rowSet.getString("tournament_id") != null && rowSet.getString("tournament_id") != "") {
            request.setTournamentId(rowSet.getInt("tournament_id"));
        }
        request.setRequestId(rowSet.getInt("request_id"));
        if(rowSet.getString("game_name") != null && rowSet.getString("game_name") != "") {
            request.setGameName(rowSet.getString("game_name"));
        }
        request.setRequestStatus(rowSet.getString("request_status").charAt(0));
        request.setRequesterId(rowSet.getInt("requester_id"));

        return request;
    }
}
