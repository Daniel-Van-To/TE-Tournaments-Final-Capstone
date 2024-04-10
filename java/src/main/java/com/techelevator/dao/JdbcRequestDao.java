package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import com.techelevator.model.RequestDto;
import com.techelevator.model.Team;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcRequestDao implements RequestDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Request getRequestByRequestId(int requestId) {
        Request request = null;
        String sql = "SELECT request_id, tournament_id, team_id, game_name, request_status, requester_id FROM request " +
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
    @Override
    public List<Request> getPendingRequestsByTeamId(int teamId) {
        List<Request> requests = new ArrayList<>();

        String sql = "SELECT * FROM request WHERE team_id = ? AND request_status = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId, 'p');
            while(results.next()){
                requests.add(mapRowToRequest(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return requests;
    }

    @Override
    public Request updateRequestByRequestId(RequestDto request, int requestId) {
        Request updatedRequest = null;
        String sql = "UPDATE request SET tournament_id = ?, team_id = ?, game_name = ?, request_status = ?, requester_id = ? " +
                     "WHERE request_id = ?; ";
        try{
            int numberOfRows = jdbcTemplate.update(sql, request.getTournamentId(), request.getTeamId(),
                                        request.getGameName(), requestId);
            if(numberOfRows == 0) {
                throw new DaoException("Zero rows effected, expected atleast one");
            }
            else {
                updatedRequest = getRequestByRequestId(requestId);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedRequest;
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
