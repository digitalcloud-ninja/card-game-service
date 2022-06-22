package org.magnuson.games;

/**
 *
 */
public class CardGamesException extends Exception {

    /**
     *
     * @param exceptionMessage String
     */
    public CardGamesException(String exceptionMessage) {
        super(String.format("APP [CardGames] ERROR: %s", exceptionMessage));
    }
}
