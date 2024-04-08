package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Game;
import com.techelevator.model.Team;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


@Service
public class JdbcGameDao implements GameDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public Game getGameByGameName(String gameName) {
        Game game = null;
        String sql = "SELECT game_name, max_players FROM game WHERE game_name = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameName);
            if(results.next()){
                game = mapRowToGame(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return game;
    }

    @Override
    public Game createGame(Game newGame) {

        return null;
    }


    public Game mapRowToGame(SqlRowSet results) {
        Game mappedGame = new Game();
        mappedGame.setName(results.getString("game_name"));
        mappedGame.setMaxPlayers(results.getInt("max_players"));
        return mappedGame;
    }




}
