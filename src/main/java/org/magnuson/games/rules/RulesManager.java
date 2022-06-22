package org.magnuson.games.rules;

import org.magnuson.games.CardGamesException;
import org.magnuson.games.Game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.magnuson.games.cards.Hand;

/**
 * RulesManager Interface
 *
 */
public interface RulesManager {
    /** Logger for the RulesManager.getInstance() */
    Logger logger = LogManager.getLogger(RulesManager.class.getName());

    /**
     * Returns an instance of the RulesManager Interface
     * Loaded from the classname specified with the Game.getClassName();
     * @param game the {@code Game Object} used to create the instance.
     * @return {@code RulesManager Object} containing the rules of the game.
     * @throws CardGamesException when and application error occurs.
     *
     * @see Game
     */
    static RulesManager getInstance(Game game) throws CardGamesException {
        try {
            logger.debug(String.format("Classpath: %1$s", System.getProperty("java.class.path")));
            logger.debug(String.format("Game.getClassName()==%1$s", game.getClassName()));
            Class<?> classFromString = Class.forName(game.getClassName());
            Constructor<?> constructor = classFromString.getConstructor(Game.class);
            return (RulesManager) constructor.newInstance(game);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new CardGamesException(e.getMessage());
        }
    }

    /**
     * Returns the Game Object used to create the instance.
     * @return the {@code Game Object} used to create the instance.
     */
    String getGameName();
    boolean isWinningHand(Hand hand);
}
