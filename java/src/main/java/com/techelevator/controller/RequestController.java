package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.TournamentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import com.techelevator.model.RequestDto;
import com.techelevator.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.model.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;
    private UserDao userDao;
    private TeamDao teamDao;
    private TournamentDao tournamentDao;

    public RequestController(RequestDao requestDao, UserDao userDao, TeamDao teamDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}/requests", method = RequestMethod.GET)
    public List<Request> getPendingTeamRequests(@PathVariable int teamId) {
        try {
            //TODO method requires tests
            return requestDao.getPendingRequestsByTeamId(teamId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}/requests/{requestId}", method = RequestMethod.PUT)
    public Request updateRequestById(@RequestBody RequestDto request, @PathVariable int requestId) {
        Request updatedRequest = requestDao.updateRequestByRequestId(request, requestId);
        try {
            //TODO method requires tests
            if(updatedRequest.getRequestStatus() == 'a') {
                teamDao.linkUserToTeam(updatedRequest.getRequesterId(), updatedRequest.getTeamId());
            }
            return updatedRequest;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentId}/requests/{requestId}", method = RequestMethod.PUT)
    public Request updateTournamentRequestById(@RequestBody RequestDto request,@PathVariable int tournamentId, @PathVariable int requestId) {
        Request updatedRequest = requestDao.updateRequestByRequestId(request, requestId);
        try {
            //TODO method requires tests
            if(updatedRequest.getRequestStatus() == 'a') {
                tournamentDao.linkTeamToTournament(updatedRequest.getTeamId(), updatedRequest.getTournamentId());
            }
            return updatedRequest;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/tournaments/{tournamentId}/requests", method = RequestMethod.GET)
    public List<Request> getPendingTournamentRequests(@PathVariable int tournamentId) {
        try {
            //TODO method requires tests
            return requestDao.getPendingRequestsByTournamentId(tournamentId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/tournaments/{tournamentId}", method = RequestMethod.POST)
    public Request handleTournamentJoinRequest(@Valid @RequestBody RequestDto requestDto, @PathVariable int tournamentId) {
        try {
            List<Team> requestTournamentTeams = tournamentDao.getParticipatingTeams(tournamentId);
            List<Request> tournamentPendingRequests = requestDao.getPendingRequestsByTournamentId(tournamentId);
            int teamId = requestDto.getTeamId();

            if(requestTournamentTeams.contains(teamDao.getTeamById(teamId))) {
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                        "You are already a member of this team.");
            }
            for(Request request: tournamentPendingRequests) {
                if(request.getTeamId() == teamId) {
                    throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                            "You already have a pending request to join this team");
                }
            }
            return requestDao.addTournamentJoinRequest(requestDto);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
