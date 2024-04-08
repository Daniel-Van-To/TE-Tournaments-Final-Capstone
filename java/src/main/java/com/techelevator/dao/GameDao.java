package com.techelevator.dao;

import com.techelevator.model.Game;

public interface GameDao {

    public Game getGameByGameName(String gameName);

    public Game createGame(Game newGame);
}
