package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {

    private UserDao userDao;
    private TeamDao teamDao;
    private RequestDao requestDao;
    private TournamentDao tournamentDao;
    private ScoreDao scoreDao;


    public TournamentController(UserDao userDao, TeamDao teamDao, RequestDao requestDao, TournamentDao tournamentDao, ScoreDao scoreDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.requestDao = requestDao;
        this.tournamentDao = tournamentDao;
        this.scoreDao = scoreDao;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create-tournament", method = RequestMethod.POST)
    public Tournament createTournament(@Valid @RequestBody TournamentDto newTournament) {
        Tournament createdTournament = null;
        try {
            createdTournament =  tournamentDao.createTournament(newTournament);
            int positions = createdTournament.getMaximumParticipants();
            if(positions > 0) {
                positions = (positions * 2);
                for(int i = 1; i < positions; i++) {
                    Score newScore = new Score();
                    newScore.setTournamentId(createdTournament.getTournamentId());
                    newScore.setBracketPosition(i);
                    newScore.setScore("");
//                    newScore.setTeamId();
                    scoreDao.addScore(newScore);
                }
            }
            return createdTournament;

        } catch(DaoException e) {

            //Precondition Required
            if (e.getMessage().equals("Tournament cannot be added since the game is not in the system.")) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_REQUIRED,
                        "That game is not in the system. Please add it and try again.");
            }
            else if (e.getMessage().equals("Tournament already exists.")) {
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                        "That tournament name is taken for this game.");
            }
            else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/browse-tournaments", method = RequestMethod.GET)
    public List<Tournament> browseTournaments() {
        try {
            //TODO requires unit tests
            return tournamentDao.getTournaments();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/browse-tournaments/{tournamentId}", method = RequestMethod.GET)
    public TournamentDto getTournamentById(@PathVariable int tournamentId) {
        try {
            //TODO requires unit tests
            return tournamentDao.getTournamentDetailById(tournamentId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/browse-tournaments/{tournamentId}/host/{userId}", method = RequestMethod.GET)
    public boolean checkIfUserIsTournamentHost(@PathVariable int tournamentId, @PathVariable int userId) {
        try {
            //TODO requires unit tests
            return tournamentDao.checkIfUserIsTournamentHost(tournamentId, userId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/send-request/{tournamentId}/captain/{userId}", method = RequestMethod.GET)
    public List<Team> getTeamsThatMatchTournamentGame(@PathVariable int tournamentId, @PathVariable int userId) {
        try {
            List<Team> teamsUserIsCaptain = teamDao.getTeamsUserIsCaptain(userId);
            List<Team> filteredTeams = new ArrayList<>();
            Tournament info = tournamentDao.getTournamentById(tournamentId);

            for(Team team: teamsUserIsCaptain) {
                if(team.getGameName().equalsIgnoreCase(info.getGameName())) {
                    filteredTeams.add(team);
                }
            }

            return filteredTeams;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }







/*  *******        Extra Code that was commented out         ******* */


    //    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path= "/browse-tournaments/{gameName}", method = RequestMethod.GET)
//    public List<Tournament> browseTournamentsByGameName(@PathVariable String gameName) {
//        try {
//            //TODO requires unit tests
//            return tournamentDao.getTournamentsByGameName(gameName);
//        }
//        catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//        catch (NullPointerException e) {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
//        }
//    }
}

