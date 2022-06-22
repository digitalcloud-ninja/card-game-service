package org.magnuson.games.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Harness: Hand::PlayersHand")
class HandTest {
    private final static Logger logger = LogManager.getLogger(HandTest.class.getName());
    private final Hand testHand = createTestHand();
    private final Hand royalFlush = createRoyalFlush();
    private final Hand randomHand = createRandomHand();

    // Test Hands
    Hand createTestHand(){
        PlayersHand hand = new PlayersHand("test-hand");
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
        hand.add(new PlayingCard(Card.Suit.CLUBS, Card.Rank.SEVEN));
        hand.add(new PlayingCard(Card.Suit.HEARTS, Card.Rank.FIVE));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.JACK));
        hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.NINE));
        return hand;
    }

    Hand createPartialHand() {
        PlayersHand hand = new PlayersHand("partial-test-hand");
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.JACK));
        hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.NINE));
        return hand;
    }

    Hand createRoyalFlush(){
        PlayersHand hand = new PlayersHand("royal-flush-diamonds");
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.KING));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.JACK));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.TEN));
        return hand;
    }

    Hand createRandomHand() {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        return new PlayersHand("random-hand", deck.dealMultipleCards(5));
    }

    @Test
    @DisplayName("Returns the correct value for .getHandName()")
    void getHandName() {
        // testHand
        assertEquals("test-hand", testHand.getHandName());

        // royalFlush
        assertEquals("royal-flush-diamonds", royalFlush.getHandName());

        // randomHand
        assertEquals("random-hand", randomHand.getHandName());

        // Done!
        logger.info("PASSED: Returns the correct value for .getHandName()");
    }

    @Test
    @DisplayName("Returns the correct value for .contains(Card c)")
    void testContains() {
        // testHand
        assertTrue(testHand.contains(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE)));
        assertFalse(testHand.contains(null), "Something wrong, should always be false. Null");
        assertFalse(testHand.contains(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.THREE)));

        // royalFlush
        assertTrue(royalFlush.contains(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE)));
        assertFalse(royalFlush.contains(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.THREE)));

        // randomHand
        assertTrue(randomHand.contains(randomHand.get(1)), "Something wrong, should always be true.");
        assertFalse(randomHand.contains(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.THREE)),
                "Hit because of odds, Yeah! its a game, try again");

        // Done!
        logger.info("PASSED: Returns the correct value for .contains(Card c)");
    }

    @Test
    @DisplayName("Returns the correct value for .containsAll(Hand h)")
    void testContainsAll() {
        // testHand
        assertTrue(testHand.containsAll(createTestHand()), "Something wrong, should always be true");
        assertTrue(testHand.containsAll(createPartialHand()), "Something wrong, should always be true");
        assertFalse(testHand.containsAll(royalFlush), "Something wrong, should always be false");
        assertFalse(testHand.containsAll(randomHand), "Hit because of odds, Yeah! its a game, try again");

        // royalFlush
        assertTrue(royalFlush.containsAll(createRoyalFlush()), "Something wrong, should always be true");
        assertFalse(royalFlush.containsAll(testHand), "Something wrong, should always be false");
        assertFalse(royalFlush.containsAll(createPartialHand()), "Something wrong, should always be false");
        assertFalse(royalFlush.containsAll(randomHand), "Hit because of odds, Yeah! its a game, try again");

        // randomHand
        assertFalse(randomHand.containsAll(createRandomHand()),
                "Something wrong, should always be false (well!! maybe your really lucky)");
        assertFalse(randomHand.containsAll(testHand));
        assertFalse(randomHand.containsAll(createPartialHand()),
                "Hit because of odds, Yeah! its a game, try again");
        assertFalse(randomHand.containsAll(royalFlush));

        // Done!
        logger.info("PASSED: Returns the correct value for .containsAll(Hand h)");
    }

    @Test
    @DisplayName("Returns the correct value for .compareTo(Hand h)")
    void testCompareTo() {
        // testHand
        assertEquals(0, testHand.compareTo(createTestHand()));
        assertEquals(-1318953174, testHand.compareTo(royalFlush));
        assertTrue(testHand.compareTo(randomHand) != 0);

        // royalFlush
        assertEquals(0, royalFlush.compareTo(createRoyalFlush()));
        assertEquals(1318953174, royalFlush.compareTo(testHand));
        assertTrue(royalFlush.compareTo(randomHand) != 0);

        // randomHand
        assertNotEquals(0, randomHand.compareTo(createRandomHand()));
        assertNotEquals(0, randomHand.compareTo(testHand));
        assertNotEquals(0, randomHand.compareTo(royalFlush));

        // Done!
        logger.info("PASSED: Returns the correct value for .compareTo(Hand h)");
    }

    @Test
    @DisplayName("Returns the correct value for .equal(Hand h)")
    void testEquals() {

        // testHand
        assertEquals(createTestHand(), testHand);
        assertNotEquals(royalFlush, testHand);
        assertNotEquals(randomHand, testHand);

        // royalFlush
        assertNotEquals(testHand, royalFlush);
        assertEquals(createRoyalFlush(), royalFlush);
        assertNotEquals(randomHand, royalFlush);

        // randomHand
        assertNotEquals(testHand, randomHand);
        assertNotEquals(royalFlush, randomHand);
        assertNotEquals(createRandomHand(), randomHand);

        // Done!
        logger.info("PASSED: Returns the correct value for .equal(Hand h)");
    }

    //Not really important test, but more for information on output.
    @Test
    @DisplayName("Returns the correct value for .toString()")
    void testToString() {
        String expected;
        //testHand
        expected= "Hand: {handName=\"test-hand\",[ACE_OF_DIAMONDS, SEVEN_OF_CLUBS, FIVE_OF_HEARTS, JACK_OF_DIAMONDS, " +
                "NINE_OF_SPADES]}";
        assertEquals(expected, testHand.toString());

        //royalFlush
        expected = "Hand: {handName=\"royal-flush-diamonds\",[ACE_OF_DIAMONDS, KING_OF_DIAMONDS, QUEEN_OF_DIAMONDS, " +
                "JACK_OF_DIAMONDS, TEN_OF_DIAMONDS]}";
        assertEquals(expected, royalFlush.toString());

        //randomHand --- odds of a royal flush being tested in theory LOL
        expected = "Hand: {handName=\"random-hand\",[ACE_OF_DIAMONDS, KING_OF_DIAMONDS, QUEEN_OF_DIAMONDS, " +
                "JACK_OF_DIAMONDS, TEN_OF_DIAMONDS]}";
        assertNotEquals(expected, randomHand.toString(), "Congratulations! You WON BIG!");
        System.out.println("No Such Luck! " + randomHand);

        // Done!
        logger.info("PASSED: Returns the correct value for .toString()");
    }
}