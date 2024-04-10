package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;

    public RequestController(RequestDao requestDao){
        this.requestDao = requestDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/teams/{teamId}/requests", method = RequestMethod.GET)
    public List<Request> getPendingRequests(@PathVariable int teamId) {

        try {
            //TODO with the new TeamDto, send just the team as the team will have a list of team members (users)
            return requestDao.getPendingRequestsByTeamId(teamId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

}
