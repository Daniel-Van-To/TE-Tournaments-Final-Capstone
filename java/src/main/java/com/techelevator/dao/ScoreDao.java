package com.techelevator.dao;

import com.techelevator.model.Score;

import java.util.List;

public interface ScoreDao {


    public Score addScore(Score newScore);
    public Score getScoresByScoreId(int scoreId);
    public Score getScoreByPosition(int tournamentId, int position);
    public List<Score> getScoresByTournamentId(int tournamentId);
    public List<Score> getScoresByTeamId(int teamId);

}