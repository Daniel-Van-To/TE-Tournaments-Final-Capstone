package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import com.techelevator.model.RequestDto;
import com.techelevator.model.Team;
import com.techelevator.model.User;
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
    private RequestDao requestDao;

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
    public List<Request> getPendingRequestsByTournamentId(int tournamentId) {
        List<Request> requests = new ArrayList<>();

        String sql = "SELECT * FROM request WHERE tournament_id = ? AND request_status = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId, 'p');
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
        String sql = "UPDATE request SET request_status = ? " +
                     "WHERE request_id = ?; ";
        try{
            int numberOfRows = jdbcTemplate.update(sql, request.getRequestStatus(),  requestId);
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

    //TODO Remember to come back to addTournamentJoinRequest
    @Override
    public Request addTournamentJoinRequest(RequestDto request)
    {
        Request joinTournament = null;
        String sql = "INSERT INTO request (tournament_id, team_id, request_status) " +
                "VALUES (?, ?, ?) RETURNING request_id;";
        try{
            int request_id = jdbcTemplate.queryForObject(sql, int.class,request.getTournamentId(), request.getTeamId(), 'p');
            joinTournament = requestDao.getRequestByRequestId(request_id);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return joinTournament;
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
