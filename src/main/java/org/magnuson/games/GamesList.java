package org.magnuson.games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Properties;

/**
 * GameList extends {@code ArrayList}
 * The list of games loaded from the application.properties file.
 */
public class GamesList extends ArrayList<Game> {
    private static final Logger logger = LogManager.getLogger(GamesList.class);
    /**
     * GameList Constructor
     *
     * @param application application.properties
     * @throws CardGamesException when something bad happens.
     */
    public GamesList(Properties application) throws CardGamesException {
        String errMessage = "Games are not properly configured in the application.properties.";
        if (!application.containsKey("games.count")) throw new CardGamesException(errMessage);
        int gamesCount = Integer.parseInt(application.getProperty("games.count"));
        logger.debug("game.count == " + gamesCount);
        for (int i = 1; i <= gamesCount; i++) {
            String name = String.format("games.%d.name", i);
            String description = String.format("games.%d.description", i);
            String className = String.format("games.%d.className", i);
            logger.debug(String.format("Generated properties: %1$s, %2$s, %3$s", name, description, className));
            if (!application.containsKey(name) || !application.containsKey(className))
                throw new CardGamesException(errMessage);
            Game game = new Game(i, application.getProperty(name), application.getProperty(description),
                    application.getProperty(className));
            super.add(game);
            logger.info(String.format("Game added: %1$d. %2$s - %3$s", game.getId(), game.getName(), game.getDescription()));
        }
    }

    @Override
    public String toString() {
        return "GamesList: {" + super.toString() + "}";
    }
}
