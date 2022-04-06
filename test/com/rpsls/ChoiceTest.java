package com.rpsls;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChoiceTest {
    @Test
    public void get_playerChoosesRPSLX_shouldReturnRPSLX() {
        assertEquals(Choice.ROCK, Choice.get("r"));
        assertEquals(Choice.PAPER, Choice.get("p"));
        assertEquals(Choice.SCISSORS, Choice.get("s"));
        assertEquals(Choice.SPOCK, Choice.get("x"));
        assertEquals(Choice.LIZARD, Choice.get("l"));
        assertNull(Choice.get("a"));
        assertNull(Choice.get("A"));


        assertEquals(Choice.ROCK, Choice.get("R"));
        assertEquals(Choice.PAPER, Choice.get("P"));
        assertEquals(Choice.SCISSORS, Choice.get("S"));
        assertEquals(Choice.SPOCK, Choice.get("X"));
        assertEquals(Choice.LIZARD, Choice.get("L"));

    }

    //Player chooses ROCK
    @Test
    public void compare_playerChoosesRock_botChoosesRock() {
        assertEquals(0, Choice.compare(Choice.ROCK, Choice.ROCK));
    }

    @Test
    public void compare_playerChoosesRock_botChoosesPaper() {
        assertEquals(2, Choice.compare(Choice.ROCK, Choice.PAPER));
    }

    @Test
    public void compare_playerChoosesRock_botChoosesScissors() {
        assertEquals(1, Choice.compare(Choice.ROCK, Choice.SCISSORS));
    }

    @Test
    public void compare_playerChoosesRock_botChoosesLizard() {
        assertEquals(1, Choice.compare(Choice.ROCK, Choice.LIZARD));
    }

    @Test
    public void compare_playerChoosesRock_botChoosesSpock() {
        assertEquals(2, Choice.compare(Choice.ROCK, Choice.SPOCK));
    }

    //Player chooses PAPER
    @Test
    public void compare_playerChoosesPaper_botChoosesRock() {
        assertEquals(1, Choice.compare(Choice.PAPER, Choice.ROCK));
    }

    @Test
    public void compare_playerChoosesPaper_botChoosesPaper() {
        assertEquals(0, Choice.compare(Choice.PAPER, Choice.PAPER));
    }

    @Test
    public void compare_playerChoosesPaper_botChoosesScissors() {
        assertEquals(2, Choice.compare(Choice.PAPER, Choice.SCISSORS));
    }

    @Test
    public void compare_playerChoosesPaper_botChoosesLizard() {
        assertEquals(2, Choice.compare(Choice.PAPER, Choice.LIZARD));
    }

    @Test
    public void compare_playerChoosesPaper_botChoosesSpock() {
        assertEquals(1, Choice.compare(Choice.PAPER, Choice.SPOCK));
    }

    //Player chooses SCISSORS
    @Test
    public void compare_playerChoosesScissors_botChoosesRock() {
        assertEquals(2, Choice.compare(Choice.SCISSORS, Choice.ROCK));
    }

    @Test
    public void compare_playerChoosesScissors_botChoosesPaper() {
        assertEquals(1, Choice.compare(Choice.SCISSORS, Choice.PAPER));
    }

    @Test
    public void compare_playerChoosesScissors_botChoosesScissors() {
        assertEquals(0, Choice.compare(Choice.SCISSORS, Choice.SCISSORS));
    }

    @Test
    public void compare_playerChoosesScissors_botChoosesLizard() {
        assertEquals(1, Choice.compare(Choice.SCISSORS, Choice.LIZARD));
    }

    @Test
    public void compare_playerChoosesScissors_botChoosesSpock() {
        assertEquals(2, Choice.compare(Choice.SCISSORS, Choice.SPOCK));
    }

    //Player chooses LIZARD
    @Test
    public void compare_playerChoosesLizard_botChoosesRock() {
        assertEquals(2, Choice.compare(Choice.LIZARD, Choice.ROCK));
    }

    @Test
    public void compare_playerChoosesLizard_botChoosesPaper() {
        assertEquals(1, Choice.compare(Choice.LIZARD, Choice.PAPER));
    }

    @Test
    public void compare_playerChoosesLizard_botChoosesScissors() {
        assertEquals(2, Choice.compare(Choice.LIZARD, Choice.SCISSORS));
    }

    @Test
    public void compare_playerChoosesLizard_botChoosesLizard() {
        assertEquals(0, Choice.compare(Choice.LIZARD, Choice.LIZARD));
    }

    @Test
    public void compare_playerChoosesLizard_botChoosesSpock() {
        assertEquals(1, Choice.compare(Choice.LIZARD, Choice.SPOCK));
    }

    //Player chooses SPOCK
    @Test
    public void compare_playerChoosesSpock_botChoosesRock() {
        assertEquals(1, Choice.compare(Choice.SPOCK, Choice.ROCK));
    }

    @Test
    public void compare_playerChoosesSpock_botChoosesPaper() {
        assertEquals(2, Choice.compare(Choice.SPOCK, Choice.PAPER));
    }

    @Test
    public void compare_playerChoosesSpock_botChoosesScissors() {
        assertEquals(1, Choice.compare(Choice.SPOCK, Choice.SCISSORS));
    }

    @Test
    public void compare_playerChoosesSpock_botChoosesLizard() {
        assertEquals(2, Choice.compare(Choice.SPOCK, Choice.LIZARD));
    }

    @Test
    public void compare_playerChoosesSpock_botChoosesSpock() {
        assertEquals(0, Choice.compare(Choice.SPOCK, Choice.SPOCK));
    }
}