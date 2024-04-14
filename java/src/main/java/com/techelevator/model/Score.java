package com.techelevator.model;

public class Score {
    private int scoreId;
    private int tournamentId;
    private int teamId;
    private int bracketPosition;
    private String score;

    public Score() {}

    public Score(int scoreId, int tournamentId, int teamId, int bracket_position, String score) {
        this.scoreId = scoreId;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
        this.bracketPosition = bracket_position;
        this.score = score;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getBracketPosition() {
        return bracketPosition;
    }

    public void setBracketPosition(int bracket_position) {
        this.bracketPosition = bracket_position;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
