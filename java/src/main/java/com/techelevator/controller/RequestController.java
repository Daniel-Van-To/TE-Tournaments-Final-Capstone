package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import com.techelevator.model.RequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.techelevator.model.User;

import java.util.List;

@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;
    private UserDao userDao;
    private TeamDao teamDao;

    public RequestController(RequestDao requestDao, UserDao userDao, TeamDao teamDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.teamDao = teamDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}/requests", method = RequestMethod.GET)
    public List<Request> getPendingRequests(@PathVariable int teamId) {

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


        if(updatedRequest.getRequestStatus() == 'a') {
            teamDao.linkUserToTeam(updatedRequest.getRequesterId(), updatedRequest.getTeamId());
        }
        return updatedRequest;
    }


}
