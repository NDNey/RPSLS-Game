package com.rpsls;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.internal.EnumSupplier;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() {
        player = new Player(1, "Player 1");
    }

    @Test
    public void randomChoice_whenMethodCalled_isNotEqualNull() {
        assertNotNull(player.randomChoice());
    }

    // We will test if random test is between 1-5
//    @Test
//    public void randomChoice_whenMethodCalled_needsToReturnOneOfTheEnumChoices() {
//        String[] strings =
//    }

    @Test
    public void toString_willReturnMessage_whenToStringMethodIsCalled() {
        assertEquals("Player: id=1, name=Player 1, wins=0", player.toString());
    }
}