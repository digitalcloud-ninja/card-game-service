package org.magnuson.games.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Harness: Deck::DeckOfCards")
class DeckTest {
    private final static Logger logger = LogManager.getLogger(DeckTest.class.getName());

    private final DeckOfCards singleDeck = new DeckOfCards();
    private final DeckOfCards twoDecks = new DeckOfCards(2);
    private final DeckOfCards oddDeck = new DeckOfCards(createOddDeck());
    private final DeckOfCards shortDeck = new DeckOfCards(createShortDeck());


    Deck createOddDeck() {
        DeckOfCards deck = new DeckOfCards();
        for (Card.Suit suit : Arrays.asList(Card.Suit.DIAMONDS, Card.Suit.CLUBS, Card.Suit.HEARTS, Card.Suit.SPADES)) {
            deck.add(new PlayingCard(suit, Card.Rank.ACE));
        }
        return deck;
    }

    Deck createShortDeck() {
        DeckOfCards deck = new DeckOfCards();
        for (Card.Suit suit : Arrays.asList(Card.Suit.DIAMONDS, Card.Suit.CLUBS, Card.Suit.HEARTS, Card.Suit.SPADES)) {
            deck.remove(new PlayingCard(suit, Card.Rank.ACE));
        }
        return deck;
    }

    @Test
    @DisplayName("Does it calculate Deck count correctly")
    void testDeckCount() {
        assertEquals(1, singleDeck.getDeckCount());
        assertEquals(2, twoDecks.getDeckCount());
        assertEquals(1, oddDeck.getDeckCount());
        assertEquals(0, shortDeck.getDeckCount());
        logger.info("PASSED: Does it calculate Deck count correctly");
    }

    @Test
    @DisplayName("Does size() reflect the correct card count")
    void testSize() {
        assertEquals(52, singleDeck.size());
        assertEquals(104, twoDecks.size());
        assertEquals(56, oddDeck.size());
        assertEquals(48, shortDeck.size());
        logger.info("PASSED: Does size() reflect the correct card count");
    }

    @Test
    @DisplayName("Does the Deck update correctly when a Card is dealt.")
    void testDealSingleCardUpdatesCorrectly() {
        //runs through all the cards and checks hashcode and size
        int cardCounter = singleDeck.size(); //starts @ 52
        int cardHash = 1;
        while (!singleDeck.isEmpty()) {
            assertEquals(cardHash, singleDeck.dealSingleCard().hashCode());
            cardHash++;
            cardCounter--;
            assertEquals(cardCounter, singleDeck.size());
        }
        logger.info("PASSED: Does the Deck update correctly when a Card is dealt.");
    }

    @Test
    @DisplayName("Returns NULL when the Deck is empty")
    void testDealSingleCardReturnsNull() {
        singleDeck.clear();
        assertNull(singleDeck.dealSingleCard());
        logger.info("PASSED: Returns NULL when the Deck is empty");
    }

    @Test
    @DisplayName("Does the Deck update correctly when 5 Cards are dealt.")
    void testDealingMultipleCardsUpdatesCorrectly() {
        //runs through all the cards and checks hashcode and size
        int cardCounter = singleDeck.size();
        while (!singleDeck.isEmpty()) {
            assertNotNull(singleDeck.dealMultipleCards(13));
            cardCounter = cardCounter - 13;
            assertEquals(cardCounter, singleDeck.size());
        }
        // Now "deal" five more cards after deck is empty. Empty array.
        assertArrayEquals(new Card[0], singleDeck.dealMultipleCards(5).toArray());
        logger.info("PASSED: Does the Deck update correctly when 5 Cards are dealt.");
    }
    @Test
    @DisplayName("Returns Empty[] when the Deck is empty")
    void testDealingMultipleCardsReturnsNull() {
        singleDeck.clear();
        assertArrayEquals(new Card[0], singleDeck.dealMultipleCards(5).toArray());
        logger.info("PASSED: Returns Empty[] when the Deck is empty");
    }

    @Test
    @DisplayName("Does reset work correctly.")
    void reset() {
        singleDeck.shuffle();
        assertNotEquals(new DeckOfCards(), singleDeck);
        singleDeck.reset();
        assertEquals(new DeckOfCards(), singleDeck);
        singleDeck.clear();
        assertNotEquals(new DeckOfCards(), singleDeck);
        singleDeck.reset();
        assertEquals(new DeckOfCards(), singleDeck);
        logger.info("PASSED: Does reset work correctly.");
    }

    @Test
    @DisplayName("Does shuffle work correctly")
    void shuffle() {
        assertEquals(new DeckOfCards(), singleDeck);
        singleDeck.shuffle();
        assertNotEquals(new DeckOfCards(), singleDeck);
        assertEquals(new DeckOfCards(2), twoDecks);
        twoDecks.shuffle();
        assertNotEquals(new DeckOfCards(2), twoDecks);
        logger.info("PASSED: Does shuffle work correctly");
    }

    @Test
    @DisplayName("Does it print the Deck correctly")
    void toStringTest() {
        String expected = "Deck{ deckCount=1, cards=[ACE_OF_DIAMONDS, TWO_OF_DIAMONDS, THREE_OF_DIAMONDS, " +
                "FOUR_OF_DIAMONDS, FIVE_OF_DIAMONDS, SIX_OF_DIAMONDS, SEVEN_OF_DIAMONDS, EIGHT_OF_DIAMONDS, NINE_OF_DIAMONDS, " +
                "TEN_OF_DIAMONDS, JACK_OF_DIAMONDS, QUEEN_OF_DIAMONDS, KING_OF_DIAMONDS, ACE_OF_CLUBS, TWO_OF_CLUBS, " +
                "THREE_OF_CLUBS, FOUR_OF_CLUBS, FIVE_OF_CLUBS, SIX_OF_CLUBS, SEVEN_OF_CLUBS, EIGHT_OF_CLUBS, NINE_OF_CLUBS, " +
                "TEN_OF_CLUBS, JACK_OF_CLUBS, QUEEN_OF_CLUBS, KING_OF_CLUBS, ACE_OF_HEARTS, TWO_OF_HEARTS, THREE_OF_HEARTS, " +
                "FOUR_OF_HEARTS, FIVE_OF_HEARTS, SIX_OF_HEARTS, SEVEN_OF_HEARTS, EIGHT_OF_HEARTS, NINE_OF_HEARTS, " +
                "TEN_OF_HEARTS, JACK_OF_HEARTS, QUEEN_OF_HEARTS, KING_OF_HEARTS, ACE_OF_SPADES, TWO_OF_SPADES, THREE_OF_SPADES, " +
                "FOUR_OF_SPADES, FIVE_OF_SPADES, SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES, NINE_OF_SPADES, " +
                "TEN_OF_SPADES, JACK_OF_SPADES, QUEEN_OF_SPADES, KING_OF_SPADES]}";
        assertEquals(expected, singleDeck.toString());
        logger.info("PASSED: Does it print the Deck correctly");
    }
}