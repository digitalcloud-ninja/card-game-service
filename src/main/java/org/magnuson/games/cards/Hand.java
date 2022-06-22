package org.magnuson.games.cards;

import java.util.Comparator;
import java.util.List;

/**
 * Hand Interface
 */
public interface Hand extends List<Card>, Comparable<Hand> {

    /**
     * Returns the name associated with the Hand
     * @return {@code String} containing the name.
     */
    String getHandName();

    /**
     *
     * @param handName Set the name of the hand.
     */
    void setHandName(String handName);

    /**
     * Returns a new list of Cards from the hand that "match" the Card.Rank
     * @param rank Rank to match.
     * @return List
     */
    List<Card> getAllRank(Card.Rank rank);

    /**
     * Returns a new list of Cards from the hand that "match" the Card.Suit
     * @param suit Suit to match.
     * @return List
     */
    List<Card> getAllSuit(Card.Suit suit);

    class SortByRankThenSuit implements Comparator<Card> {
        public int compare(Card firstCard, Card secondCard) {
            int compVal = firstCard.getRank().getValue() - secondCard.getRank().getValue();
            if (compVal != 0) {
                return compVal;
            } else {
                return firstCard.getSuit().getValue() - secondCard.getSuit().getValue();
            }
        }
    }
}
