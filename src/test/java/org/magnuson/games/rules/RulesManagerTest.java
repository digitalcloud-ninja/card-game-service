package org.magnuson.games.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.magnuson.games.CardGamesException;
import org.magnuson.games.Game;

import static org.junit.jupiter.api.Assertions.*;

class RulesManagerTest {
    private final static Logger logger = LogManager.getLogger(RulesManagerTest.class.getName());

    @Test
    @DisplayName("Returns StandardPokerGame class correctly")
    void getRulesManager() throws CardGamesException {
        Game testGame = new Game(1, "test", "test", "org.magnuson.games.rules.StandardPokerGame");
        RulesManager manager = RulesManager.getInstance(testGame);
        assertEquals(testGame.getName(), manager.getGameName());
        assertEquals(StandardPokerGame.class, manager.getClass());
        logger.info("PASSED: Returns StandardPokerGame class correctly");
    }
}