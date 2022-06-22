package org.magnuson.games;

import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class GamesListTest {
    private static final Logger logger = LogManager.getLogger(GamesListTest.class);

    @Test
    void testDefaultConstructor() {
        Properties properties = new Properties();
        GamesList results;
        properties.setProperty("games.count", "3");
        properties.setProperty("games.1.id", "1");
        properties.setProperty("games.1.name", "Standard Poker");
        properties.setProperty("games.1.description", "5 Card Draw");
        properties.setProperty("games.1.className", "org.magnuson.games.rules.StandardPokerGame");
        properties.setProperty("games.2.id", "2");
        properties.setProperty("games.2.name", "Standard Poker");
        properties.setProperty("games.2.description", "Jack OR Better");
        properties.setProperty("games.2.className", "org.magnuson.games.rules.StandardPokerGame");
        properties.setProperty("games.3.id", "3");
        properties.setProperty("games.3.name", "Standard Poker");
        properties.setProperty("games.3.description", "BROKEN");
        properties.setProperty("games.3.className", "");
        logger.debug("properties = " + properties);
        try {
            results = new GamesList(properties);
            logger.debug("results == " + results);
        } catch (CardGamesException e) {
            logger.debug(e.getMessage(), e);
        }

    }
}