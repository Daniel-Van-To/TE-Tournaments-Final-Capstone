package com.techelevator.dao;

import com.techelevator.model.Request;
import com.techelevator.model.RequestDto;

import java.util.List;

public interface RequestDao {

    public Request getRequestByRequestId(int requestId);
    public List<Request> getPendingRequestsByTeamId(int teamId);
    public Request updateRequestByRequestId(RequestDto request, int requestId);
    public List<Request> getPendingRequestsByTournamentId(int tournamentId);
    public Request addTournamentJoinRequest(RequestDto request);

}
