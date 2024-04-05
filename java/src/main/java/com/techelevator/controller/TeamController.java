package com.techelevator.controller;


import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    private UserDao userDao;
    private TeamDao teamDao;

    public TeamController(UserDao userDao, TeamDao teamDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
    }


}
