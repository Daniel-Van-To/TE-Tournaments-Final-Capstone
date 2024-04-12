package com.techelevator.dao;


import com.techelevator.model.Team;
import com.techelevator.model.TeamDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcTeamDaoTests extends BaseDaoTests {

    //these three teams have been added to test-data.sql
    private static final Team TEAM_1 = new Team(1, "Team1", 1, "valorant", true);
    private static final Team TEAM_2 = new Team(2, "Team2", 2, "tennis", true);
    private static final Team TEAM_3 = new Team(3, "Team3", 3, "running", false);

    //this teamdto and this team is not in test-data.sql
    private static TeamDto TEAM_4_DTO = new TeamDto();
    private static final Team TEAM_4 = new Team(4, "Team4", 3, "valorant", true);

    private JdbcTeamDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTeamDao(jdbcTemplate);
        TEAM_4_DTO.setGameName("valorant");
        TEAM_4_DTO.setAcceptingMembers(true);
        TEAM_4_DTO.setTeamName("Team4");
        TEAM_4_DTO.setUsername("user3");
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
        sut.createTeam(TEAM_4_DTO);
        Team actual = sut.getTeamById(4);
        assertTeamEquals(TEAM_4, actual);
    }

    @Test
    public void getTeamsByAcceptingMembers_returns_accurate_list_of_teams() {
        List<Team> actual = sut.getTeamsByAcceptingMembers();
        Assert.assertEquals("List of teams was not the correct size.",2, actual.size());

        List<Team> expected = new ArrayList<>();
        expected.add(TEAM_1);
        expected.add(TEAM_2);

        for (int i = 0; i < actual.size(); i++) {
            assertTeamEquals(expected.get(i), actual.get(i));
        }

    }


    public void assertTeamEquals(Team expected, Team actual) {
        Assert.assertEquals("Team Id's did not match.", expected.getTeamId(), actual.getTeamId());
        Assert.assertEquals("Team names did not match.", expected.getTeamName(), actual.getTeamName());
        Assert.assertEquals("Team captain Id's did not match.", expected.getTeamCaptainId(), actual.getTeamCaptainId());
        Assert.assertEquals("Game names did not match.", expected.getGameName(), actual.getGameName());
        Assert.assertEquals("acceptingMembers different across test teams", expected.isAcceptingMembers(), actual.isAcceptingMembers());
    }
}
