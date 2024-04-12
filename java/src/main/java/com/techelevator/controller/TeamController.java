package com.techelevator.controller;


import com.techelevator.dao.RequestDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
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
public class TeamController {

    private UserDao userDao;
    private TeamDao teamDao;
    private RequestDao requestDao;


    public TeamController(UserDao userDao, TeamDao teamDao, RequestDao requestDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.requestDao = requestDao;
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.GET)
    public Team getTeamByTeamId(@PathVariable int teamId) {
        try {
            return teamDao.getTeamById(teamId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/teams/{teamId}", method = RequestMethod.POST)
    public Request handleTeamJoinRequest(@Valid @RequestBody RequestDto requestDto, @PathVariable int teamId) {
        try {
            List<User> requestTeamMembers = teamDao.getUsersOnTeam(requestDto.getTeamId());
            List<Request> teamPendingRequests = requestDao.getPendingRequestsByTeamId(requestDto.getTeamId());
            User requester = userDao.getUserById(requestDto.getRequesterId());

            if(requestTeamMembers.contains(requester)) {
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                        "You are already a member of this team.");
            }

            for(Request request: teamPendingRequests) {
                if(request.getRequesterId() == requestDto.getRequesterId()) {
                    throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                            "You already have a pending request to join this team");
                }
            }

                return teamDao.addTeamJoinRequest(requestDto);

        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
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
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path = "/teams/{userId}/captain", method = RequestMethod.GET)
//    public List<Team> getListOfTeamsUserIsCaptainOf(@PathVariable int userId) {
//
//        try {
//            //TODO requires unit tests
//            return teamDao.getTeamsUserIsCaptain(userId);
//        }
//        catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//        catch (NullPointerException e) {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
//        }
//    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}/captain", method = RequestMethod.GET)
    public boolean checkIfUserIsCaptain(@PathVariable int teamId, @RequestBody User currentUser) {

        try {
            //TODO requires unit tests
            return teamDao.checkIfUserIsTeamCaptain(teamId, currentUser);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }



}
