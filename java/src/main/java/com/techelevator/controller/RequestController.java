package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class RequestController {

    private RequestDao requestDao;
    private UserDao userDao;
    private TeamDao teamDao;
    private TournamentDao tournamentDao;
    private GameDao gameDao;

    public RequestController(RequestDao requestDao, UserDao userDao, TeamDao teamDao, TournamentDao tournamentDao, GameDao gameDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
        this.tournamentDao = tournamentDao;
        this.gameDao = gameDao;
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
            if(updatedRequest.getRequestStatus() == 'a') {
                teamDao.linkUserToTeam(updatedRequest.getRequesterId(), updatedRequest.getTeamId());

                Team checkMaxMembers = teamDao.getTeamById(request.getTeamId());
                Game teamsGame = gameDao.getGameByGameName(checkMaxMembers.getGameName());
                List<User> teamMembers = teamDao.getUsersOnTeam(request.getTeamId());
                if (teamMembers.size() == teamsGame.getMaxPlayers()) {
                    TeamDto useToUpdateAcceptingPlayers = new TeamDto();
                    useToUpdateAcceptingPlayers.setAcceptingMembers(false);
                    useToUpdateAcceptingPlayers.setTeamName(checkMaxMembers.getTeamName());
                    useToUpdateAcceptingPlayers.setTeamCaptainId(checkMaxMembers.getTeamCaptainId());
                    teamDao.updateTeam(useToUpdateAcceptingPlayers, request.getTeamId());
                }
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

            int teamId = requestDto.getTeamId();
            List<Team> requestTournamentTeams = tournamentDao.getParticipatingTeams(tournamentId);
            if(requestTournamentTeams.contains(teamDao.getTeamById(teamId))) {
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                        "You are already a member of this team.");
            }

            List<Request> tournamentPendingRequests = requestDao.getPendingRequestsByTournamentId(tournamentId);
            for(Request request: tournamentPendingRequests) {
                if(request.getTeamId() == teamId) {
                    throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,
                            "You already have a pending request to join this team");
                }
            }
            return requestDao.addTournamentJoinRequest(requestDto);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

}
