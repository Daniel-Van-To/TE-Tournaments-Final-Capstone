package com.techelevator.controller;


import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class TeamController {

    private UserDao userDao;
    private TeamDao teamDao;

    public TeamController(UserDao userDao, TeamDao teamDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
    }


    //Back End sends an exception with an HTTP STATUS in it, (200, 201, 400, 404, etc)
    //front end can access/see that HTTP status and use it to control output

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create-team", method = RequestMethod.POST)
    public Team createTeam(@Valid @RequestBody TeamDto newTeam) {

        try {

            return teamDao.createTeam(newTeam);

        } catch(DaoException e) {

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


}
