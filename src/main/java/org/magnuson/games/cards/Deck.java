package org.magnuson.games.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Deck Interface
 * The Deck interface represents one or more deck(s) of cards.
 * A deck of cards contains a single set of 52 cards.
 * ACE_OF_DIAMONDS(1) through KING_OF_SPADES(52)
 *
 * @author William Magnuson
 * @version 1.0
 */
public interface Deck extends List<Card> {

    /**
     *
     * @return int
     */
    int getDeckCount();
    /**
     * Returns a single {@code Card} from the top of the {@code Deck}.
     * @return {@code Card} top card from the Deck.
     */
    Card dealSingleCard();

    /**
     * Returns a list containing the X number of cards from the top of the {@code Deck}.
     * @param numCards {@code int} number of cards to be returned.
     * @return {@code List<Card>}
     */
    List<Card> dealMultipleCards(int numCards);

    /**
     * Shuffles the cards in the {@code Deck}.
     */
    void shuffle();

    /**
     * Resets the deck to its natural state.
     */
    void reset();

    /**
     * Returns the single Deck of Cards
     * @return List
     */
    static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new PlayingCard(suit, rank));
            }
        }
        return deck;
    }
}
