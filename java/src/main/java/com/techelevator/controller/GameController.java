package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Game;
import com.techelevator.model.Request;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    private RequestDao requestDao;
    private UserDao userDao;
    private TeamDao teamDao;
    private TournamentDao tournamentDao;
    private GameDao gameDao;

    public GameController(RequestDao requestDao, UserDao userDao, TeamDao teamDao, TournamentDao tournamentDao, GameDao gameDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.tournamentDao = tournamentDao;
        this.gameDao = gameDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/games-list", method = RequestMethod.GET)
    public List<Game> getGamesList() {
        try {
            //TODO method requires tests
            return gameDao.getAllGames();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }
}
