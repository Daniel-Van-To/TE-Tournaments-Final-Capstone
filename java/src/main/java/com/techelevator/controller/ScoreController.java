package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ScoreController {

    private UserDao userDao;
    private TeamDao teamDao;
    private TournamentDao tournamentDao;
    private RequestDao requestDao;
    private ScoreDao scoreDao;

    public ScoreController(UserDao userDao, TeamDao teamDao, RequestDao requestDao, ScoreDao scoreDao, TournamentDao tournamentDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.requestDao = requestDao;
        this.scoreDao = scoreDao;
        this.tournamentDao = tournamentDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/tournaments/{tournamentsId}/team/{teamId}/scores", method = RequestMethod.POST)
    public Score addScore(@RequestBody Score newScore) {
        try {
            return scoreDao.addScore(newScore);

        } catch(DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path = "/tournaments/{tournamentsId}/team/{teamId}/scores/{scoreId}", method = RequestMethod.GET)
//    public Score getScoreByScoreId(@PathVariable int tournamentsId, @PathVariable int teamId, @PathVariable int scoreId) {
//        try {
//            return scoreDao.getScoresByScoreId(scoreId);
//
//        } catch(DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentId}/team/{teamId}/scores/{position}", method = RequestMethod.GET)
    public Score getScoreByBracketPosition(@PathVariable int tournamentId, @PathVariable int teamId, @PathVariable int position) {
        try {
            return scoreDao.getScoreByPosition(tournamentId, position);

        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentId}/scores", method = RequestMethod.GET)
    public List<Score> getScoreByTournamentId(@PathVariable int tournamentId) {
        try {
            return scoreDao.getScoresByTournamentId(tournamentId);

        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentsId}/team/{teamId}/scores", method = RequestMethod.GET)
    public List<Score> getScoreByTeamId(@PathVariable int tournamentsId, @PathVariable int teamId, @PathVariable int scoreId) {
        try {
            return scoreDao.getScoresByTeamId(teamId);

        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/tournaments/{tournamentId}/initializeScores", method = RequestMethod.POST)
    public List<Score> initializeTournamentScores(@RequestBody List<Score> scores, @PathVariable int tournamentId) {

        List<Score> newScores = new ArrayList<>();

        try {
            for (int i = 0; i < scores.size(); i++) {
                Score toCheck = scoreDao.getScoreByPosition(tournamentId, scores.get(i).getBracketPosition());
                if (toCheck != null) {
                    newScores.add(scoreDao.updateScore(scores.get(i), scores.get(i).getScoreId()));
                }
                else {
                    newScores.add(scoreDao.addScore(scores.get(i)));
                }
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newScores;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/updateScore/{scoreId}", method = RequestMethod.PUT)
    public Score updateScoreById(@RequestBody Score scoreToUpdate, @PathVariable int scoreId) {
        try {
            return scoreDao.updateScore(scoreToUpdate,scoreId);

        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentsId}/team/{round}/update", method = RequestMethod.GET)
    public List<Score> getUpdatedPositionsIfRoundIsFinished(@PathVariable int tournamentsId, @PathVariable int round, @PathVariable int scoreId) {
        try {
            Tournament tournament = tournamentDao.getTournamentById(tournamentsId);
            int maxTeams = tournament.getMaximumParticipants();
            List<Score> scorePositions = scoreDao.getScoresByTournamentId(tournamentsId);
            int startPosition = 0;
            int startIndex = 0;


            //Finds the first position with an empty score value
            for(int i = 0; i < scorePositions.size(); i ++) {
                String positionScore = scorePositions.get(i).getScore();
                if(positionScore == null || positionScore.equals("")) {
                    startPosition = i;
                    break;
                }
            }

            int nextRoundEndPosition = maxTeams;
            int nextRoundStartPosition = maxTeams;
            int roundModifier = maxTeams;

//            //Finds the End Position of the next Round
//            for(int i = 0; i < round; i ++) {
//                nextRoundEndPosition = nextRoundEndPosition + (roundModifier/2);
//                roundModifier /= 2;
//            }
//            roundModifier = maxTeams;

            //Finds the Start Position of the next Round
            for(int i = 1; i < round; i ++) {
                nextRoundStartPosition = nextRoundStartPosition + (roundModifier/2);
                roundModifier /= 2;
            }

            //Checks to see if the first position with an empty score occurs before the Start Position of the next Round
            if(startPosition < nextRoundStartPosition) {
                return scoreDao.getScoresByTournamentId(tournamentsId);
            }

            roundModifier = maxTeams;
            //Find the start index
            for(int i = 1; i < round; i ++) {
                startIndex += roundModifier;
                roundModifier /= 2;
            }


            if(startPosition == nextRoundStartPosition) {
                //Call method to move the winners of the previous round to the next Round
                return scoreDao.moveWinnersToNextRound(scorePositions, startIndex, startPosition, tournament.getTournamentId());
            }

            return scoreDao.getScoresByTournamentId(tournamentsId);

        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
