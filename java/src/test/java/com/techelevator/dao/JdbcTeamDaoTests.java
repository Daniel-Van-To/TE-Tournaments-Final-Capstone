package com.techelevator.dao;


import com.techelevator.model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTeamDaoTests extends BaseDaoTests {

    private static final Team TEAM_1 = new Team(1, "Team1", 1, "valorant", true);
    private static final Team TEAM_2 = new Team(2, "Team2", 2, "tennis", true);
    private static final Team TEAM_3 = new Team(3, "Team3", 3, "running", false);

    private JdbcTeamDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTeamDao(jdbcTemplate);
    }

    @Test
    public void getTeamById_returns_null_when_team_DNE() {
        Assert.assertNull(sut.getTeamById(69420));
    }

    @Test
    public void getTeamByTeamName_returns_null_when_team_DNE() {
        Assert.assertNull(sut.getTeamByTeamName("Pretty Awful Inc"));
    }
}
