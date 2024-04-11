package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.TournamentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {

    private UserDao userDao;
    private TeamDao teamDao;
    private RequestDao requestDao;
    private TournamentDao tournamentDao;


    public TournamentController(UserDao userDao, TeamDao teamDao, RequestDao requestDao, TournamentDao tournamentDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.requestDao = requestDao;
        this.tournamentDao = tournamentDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create-tournament", method = RequestMethod.POST)
    public Tournament createTeam(@Valid @RequestBody TournamentDto newTournament) {

        try {
            return tournamentDao.createTournament(newTournament);

        } catch(DaoException e) {

            //Precondition Required
            if (e.getMessage().equals("Team cannot be added since the game is not in the system.")) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_REQUIRED,
                        "That game is not in the system. Please add it and try again.");
            }
            else if (e.getMessage().equals("Team already exists.")) {
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                        "That team name is taken for this game.");
            }
            else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/browse-tournaments", method = RequestMethod.GET)
    public List<Tournament> browseTournaments() {
        return null;
    }
}

