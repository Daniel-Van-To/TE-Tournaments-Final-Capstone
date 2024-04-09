package com.techelevator.dao;

import com.techelevator.model.Request;

public interface RequestDao {

    public Request getRequestByRequestId(int requestId);
}
