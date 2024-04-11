package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private UserDao userDao;
    private TeamDao teamDao;
    private RequestDao requestDao;


    public UserController(UserDao userDao, TeamDao teamDao, RequestDao requestDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.requestDao = requestDao;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{teamId}", method = RequestMethod.GET)
    public List<User> getUsersForTeam(@PathVariable int teamId) {

        try {
            //TODO with the new TeamDto, send just the team as the team will have a list of team members (users)
            return teamDao.getUsersOnTeam(teamId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable int userId) {

        try {
            //TODO Requires Unit Tests
            return userDao.getUserById(userId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }
}
