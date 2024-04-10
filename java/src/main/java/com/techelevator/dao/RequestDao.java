package com.techelevator.dao;

import com.techelevator.model.Request;

import java.util.List;

public interface RequestDao {

    public Request getRequestByRequestId(int requestId);
    public List<Request> getPendingRequestsByTeamId(int teamId);

}
