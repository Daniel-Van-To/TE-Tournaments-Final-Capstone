package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ScoreDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
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
    private RequestDao requestDao;
    private ScoreDao scoreDao;

    public ScoreController(UserDao userDao, TeamDao teamDao, RequestDao requestDao, ScoreDao scoreDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.requestDao = requestDao;
        this.scoreDao = scoreDao;
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
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newScores;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentsId}/team/{teamId}/scores", method = RequestMethod.PUT)
    public Score updateScoreById(@RequestBody Score scoreToUpdate, @PathVariable int scoreId) {
        try {
            return scoreDao.updateScore(scoreToUpdate,scoreId);

        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
