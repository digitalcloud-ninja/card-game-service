package org.magnuson.games.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



@DisplayName("Test Harness: Card::PlayingCard")
class CardTest {
    private final static Logger logger = LogManager.getLogger(CardTest.class.getName());
    private final Card ACE_OF_DIAMONDS = new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE);
    private final Card FOUR_OF_SPADES = new PlayingCard(Card.Suit.SPADES, Card.Rank.FOUR);
    private final Card KING_OF_SPADES = new PlayingCard(Card.Suit.SPADES, Card.Rank.KING);
    private final Card TEN_OF_HEARTS = new PlayingCard(Card.Suit.HEARTS, Card.Rank.TEN);
    private final Card THREE_OF_CLUBS = new PlayingCard(Card.Suit.CLUBS, Card.Rank.THREE);

    @Test
    @DisplayName("Returns the correct value for .getRank()")
    void getRank() {
        assertEquals(Card.Rank.ACE, ACE_OF_DIAMONDS.getRank());
        assertNotEquals(Card.Rank.TEN, ACE_OF_DIAMONDS.getRank());
        logger.info("PASSED: Returns the correct value for .getRank()");
    }

    @Test
    @DisplayName("Returns the correct value for .getSuit()")
    void getSuit() {
        assertEquals(Card.Suit.DIAMONDS, ACE_OF_DIAMONDS.getSuit());
        assertNotEquals(Card.Suit.CLUBS, ACE_OF_DIAMONDS.getSuit());
        logger.info("PASSED: Returns the correct value for .getSuit()");
    }

    @Test
    @DisplayName("Returns the correct value for .hashcode()")
    void testHashCode() {
        assertEquals(1, ACE_OF_DIAMONDS.hashCode());
        assertEquals(ACE_OF_DIAMONDS.hashCode(), ACE_OF_DIAMONDS.hashCode());
        assertEquals(ACE_OF_DIAMONDS.hashCode(), new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE).hashCode());
        assertEquals(43, FOUR_OF_SPADES.hashCode());
        assertNotEquals(FOUR_OF_SPADES.hashCode(), ACE_OF_DIAMONDS.hashCode());
        assertEquals(52, KING_OF_SPADES.hashCode());
        assertEquals(36, TEN_OF_HEARTS.hashCode());
        assertEquals(16, THREE_OF_CLUBS.hashCode());
        logger.info("PASSED: Returns the correct value for .hashcode()");
    }

    @Test
    @DisplayName("Returns the correct value for .equals(Object o)")
    void testEquals() {
        assertEquals(ACE_OF_DIAMONDS, ACE_OF_DIAMONDS);
        assertEquals(ACE_OF_DIAMONDS, new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
        assertNotEquals(FOUR_OF_SPADES, null);
        assertNotEquals(FOUR_OF_SPADES, new Object());
        assertNotEquals(FOUR_OF_SPADES, ACE_OF_DIAMONDS);
        logger.info("PASSED: Returns the correct value for .equals(Object o)");
    }

    @Test
    @DisplayName("Returns the correct value for .toString()")
    void testToString() {
        assertEquals("ACE_OF_DIAMONDS", ACE_OF_DIAMONDS.toString());
        // Done!
        logger.info("PASSED: Returns the correct value for .toString()");
    }

    @Test
    @DisplayName("Returns the correct value for .compareTo(Card c)")
    void compareTo() {
        assertEquals(0, ACE_OF_DIAMONDS.compareTo(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE)));
        assertEquals(-42, ACE_OF_DIAMONDS.compareTo(FOUR_OF_SPADES));
        assertEquals(42, FOUR_OF_SPADES.compareTo(ACE_OF_DIAMONDS));
        assertEquals(-9, FOUR_OF_SPADES.compareTo(KING_OF_SPADES));
        assertEquals(16, KING_OF_SPADES.compareTo(TEN_OF_HEARTS));
        assertTrue(KING_OF_SPADES.compareTo(TEN_OF_HEARTS) >= 0);
        assertTrue(TEN_OF_HEARTS.compareTo(FOUR_OF_SPADES) <= 0);
        logger.info("PASSED: Returns the correct value for .compareTo(Card c)");
    }
}