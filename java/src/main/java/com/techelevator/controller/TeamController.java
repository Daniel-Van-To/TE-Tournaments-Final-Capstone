package com.techelevator.controller;


import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

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
    @RequestMapping(path = "/teams", method = RequestMethod.GET)
    public List<Team> getJoinableTeams() {
        try {
            return teamDao.getTeamsByAcceptingMembers();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.POST)
    public Request handleTeamJoinRequest(@Valid @RequestBody RequestDto requestDto, @PathVariable int teamId) {
        try {
            //TODO Add Conditional checking if the user making the request has already sent a request or is already on the team
           return teamDao.addTeamJoinRequest(requestDto);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
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
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.DELETE)
    public void removeUserFromTeam(@RequestBody User user,@PathVariable int teamId) {
        teamDao.unlinkUserFromTeam(user.getId(), teamId);
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.PUT)
    public Team editTeam(TeamDto team, @PathVariable int teamId) {

        return teamDao.updateTeam(team, teamId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}/captain-view", method = RequestMethod.GET)
    public boolean checkIfUserIsCaptain(@RequestBody TeamDto team) {
        try {
            //TODO requires unit tests
            return teamDao.checkIfUserIsTeamCaptain(team);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }



}
