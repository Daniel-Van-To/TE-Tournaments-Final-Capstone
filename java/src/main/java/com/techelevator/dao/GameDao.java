package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.List;

public interface GameDao {

    public Game getGameByGameName(String gameName);

    public Game createGame(Game newGame);
    public List<Game> getAllGames();
}
