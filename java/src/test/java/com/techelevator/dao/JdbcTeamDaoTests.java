package com.techelevator.dao;


import com.techelevator.model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTeamDaoTests extends BaseDaoTests {

    //these three teams have been added to test-data.sql
    private static final Team TEAM_1 = new Team(1, "Team1", 1, "valorant", true);
    private static final Team TEAM_2 = new Team(2, "Team2", 2, "tennis", true);
    private static final Team TEAM_3 = new Team(3, "Team3", 3, "running", false);

    //this team is not in test-data.sql
    private static Team TEAM_4; //when added:   teamId = 4, teamName = "Team4", teamCaptainId = 3,
                                //              gameName = "valorant", acceptingMembers = true;

    private JdbcTeamDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTeamDao(jdbcTemplate);
        TEAM_4.setGameName("valorant");
        TEAM_4.setAcceptingMembers(true);
        TEAM_4.setTeamCaptainId(3);
        TEAM_4.setTeamName("Team4");
    }

    @Test
    public void getTeamById_returns_null_when_team_DNE() {
        Assert.assertNull(sut.getTeamById(69420));
    }

    @Test
    public void getTeamByTeamName_returns_null_when_team_DNE() {
        Assert.assertNull(sut.getTeamByTeamName("Pretty Awful Inc"));
    }

    @Test
    public void createTeam_creates_team_when_happy_path() {



    }
}
