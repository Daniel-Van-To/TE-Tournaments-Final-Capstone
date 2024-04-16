package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
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
public class JdbcScoreDao implements ScoreDao {

    private JdbcTemplate jdbcTemplate;
    private GameDao gameDao;
    private UserDao userDao;
    private RequestDao requestDao;

    public JdbcScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        gameDao = new JdbcGameDao(jdbcTemplate);
        userDao = new JdbcUserDao(jdbcTemplate);
        requestDao = new JdbcRequestDao(jdbcTemplate);
    }
    @Override
    public Score addScore(Score newScore) {
        Score addedScore = null;
        String sql = "INSERT INTO scores (tournament_id, team_id, bracket_position, score) " +
                "VALUES (?, ?, ?, ?) RETURNING score_id";
        try {
            int addedScoreId = jdbcTemplate.queryForObject(sql, int.class, newScore.getTournamentId(),
                    newScore.getTeamId(), newScore.getBracketPosition(), newScore.getScore());

            addedScore = getScoresByScoreId(addedScoreId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return addedScore;
    }
    @Override
    public Score getScoresByScoreId(int scoreId) {
        Score score = null;
        String sql = "SELECT score_id, tournament_id, team_id, bracket_position, score " +
                "FROM scores WHERE score_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, scoreId);
            if(results.next()){
                score = mapRowToScore(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return score;
    }
    @Override
    public Score getScoreByPosition(int tournamentId, int position) {
        Score score = null;
        String sql = "SELECT score_id, tournament_id, team_id, bracket_position, score " +
                "FROM scores WHERE tournament_id = ? AND bracket_position = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId, position);
            if(results.next()){
                score = mapRowToScore(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return score;
    }
    @Override
    public List<Score> getScoresByTournamentId(int tournamentId) {
        List<Score> score = new ArrayList<>();
        String sql = "SELECT score_id, tournament_id, team_id, bracket_position, score " +
                "FROM scores WHERE tournament_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
            while(results.next()) {
                score.add(mapRowToScore(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return score;
    }
    @Override
    public List<Score> getScoresByTeamId(int teamId) {
        List<Score> score = new ArrayList<>();
        String sql = "SELECT score_id, tournament_id, team_id, bracket_position, score " +
                "FROM scores WHERE team_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
            while(results.next()){
                score.add(mapRowToScore(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return score;
    }


    public Score mapRowToScore(SqlRowSet rowSet){
        Score score = new Score();

        score.setScoreId(rowSet.getInt("score_id"));
        score.setTournamentId(rowSet.getInt("tournament_id"));
        score.setTeamId(rowSet.getInt("team_id"));
        score.setBracketPosition(rowSet.getInt("bracket_position"));
        score.setScore(rowSet.getString("score"));

        return score;
    }

}
