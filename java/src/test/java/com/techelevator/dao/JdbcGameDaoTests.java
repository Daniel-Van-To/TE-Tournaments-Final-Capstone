package com.techelevator.dao;

import com.techelevator.model.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcGameDaoTests extends BaseDaoTests {

    private final Game tennis = new Game("Tennis", 2);
    private final Game running = new Game("running", 1);
    private final Game valorant = new Game("valorant", 5);

    private JdbcGameDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    public void getGameByGameName_returns_null_when_game_does_not_exist() {
        Assert.assertNull(sut.getGameByGameName("stupid"));
    }
}
