package org.magnuson.games.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.magnuson.games.Game;
import org.magnuson.games.cards.Card;
import org.magnuson.games.cards.Hand;
import org.magnuson.games.cards.PlayersHand;
import org.magnuson.games.cards.PlayingCard;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class StandardPokerGameTest {
    private static final Logger LOGGER = LogManager.getLogger(StandardPokerGameTest.class);
    Game testGame = new Game(1, "test", "test", "org.magnuson.games.rules.StandardPokerGame");
    StandardPokerGame pokerGame = new StandardPokerGame(testGame);

    @Test
    @DisplayName("Royal Flush")
    void testRoyalFlush() {
        for (Card.Suit suit : Card.Suit.values()) {
            LOGGER.debug("TEST: ROYAL FLUSH");
            assertTrue(pokerGame.isWinningHand(RoyalFlush(suit)));
            assertEquals("ROYAL FLUSH", pokerGame.getWinningHand().getHandName());
            LOGGER.debug("TEST: STRAIGHT FLUSH ACE-HIGH (aka) ROYAL-FLUSH: " + suit.getText());
            assertTrue(pokerGame.isWinningHand(StraightFlush(suit, Card.Rank.TEN)));
            assertEquals("ROYAL FLUSH", pokerGame.getWinningHand().getHandName());
            LOGGER.info("PASSED: ROYAL FLUSH");
        }
    }

    @Test
    @DisplayName("Four Of Kind")
    void testFourKind() {
        for (Card.Rank rank : Card.Rank.values()) {
            LOGGER.debug("TEST: FOUR OF A KIND");
            assertTrue(pokerGame.isWinningHand(FourOfKind(rank)));
            assertEquals("FOUR OF A KIND", pokerGame.getWinningHand().getHandName());
            LOGGER.info("PASSED: FOUR OF A KIND");
        }
    }

    @Test
    @DisplayName("Straight Flush")
    void testStraightFlush() {
        String testName;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                testName = "STRAIGHT FLUSH";
                LOGGER.debug("TEST: " + testName);
                assertTrue(pokerGame.isWinningHand(StraightFlush(suit, rank)));
                if (rank.getValue() > Card.Rank.NINE.getValue()) {
                    testName = "ROYAL FLUSH";
                }
                assertEquals(testName, pokerGame.getWinningHand().getHandName());
                LOGGER.info("PASSED: " + testName);
            }
        }
    }

    @Test
    @DisplayName("Full-House")
    void testFullHouse() {
        Card.Rank[] allRanks = Card.Rank.values();
        int twoCards = 12;
        for (int threeCards = 0; threeCards < 12; threeCards++) {

            assertTrue(pokerGame.isWinningHand(FullHouse(allRanks[threeCards], allRanks[twoCards])));
            assertEquals("FULL HOUSE", pokerGame.getWinningHand().getHandName());
            LOGGER.info("PASSED: FULL HOUSE");
            twoCards--;
        }
    }

    @Test
    @DisplayName("Flush")
    void testFlush() {
        LOGGER.debug("TEST: FLUSH");
        for (Card.Suit suit : Card.Suit.values()){
            assertTrue(pokerGame.isWinningHand(Flush(suit)));
            assertEquals("FLUSH", pokerGame.getWinningHand().getHandName());
        }
        LOGGER.info("PASSED: FLUSH");
    }

    @Test
    @DisplayName("Straight")
    void testStraight() {
        LOGGER.debug("TEST: STRAIGHT");
        for (Card.Rank rank : Card.Rank.values()) {
            assertTrue(pokerGame.isWinningHand(Straight(rank)));
            assertEquals("STRAIGHT", pokerGame.getWinningHand().getHandName());
        }
        LOGGER.info("PASSED: STRAIGHT");
    }

    @Test
    @DisplayName("Three of a Kind")
    void testThreeOfKind() {
        LOGGER.debug("TEST: THREE OF A KIND");
        for (Card.Rank rank : Card.Rank.values()) {
            assertTrue(pokerGame.isWinningHand(ThreeOfKind(rank)));
            assertEquals("THREE OF A KIND", pokerGame.getWinningHand().getHandName());
        }
        LOGGER.info("PASSED: THREE OF A KIND");
    }

    @Test
    @DisplayName("Two Pairs")
    void test2Pair() {
        LOGGER.debug("TEST: TWO PAIR");
        for (Card.Rank rank : Card.Rank.values()) {
            assertTrue(pokerGame.isWinningHand(TwoPair(rank)));
            assertEquals("TWO PAIR", pokerGame.getWinningHand().getHandName());
        }
        LOGGER.info("PASSED: TWO PAIR");
    }

    @Test
    @DisplayName("Pair")
    void testPair() {
        LOGGER.debug("TEST: PAIR");
        for (Card.Rank rank : Card.Rank.values()) {
            assertTrue(pokerGame.isWinningHand(Pair(rank)));
            assertEquals("PAIR", pokerGame.getWinningHand().getHandName());
        }
        LOGGER.info("PASSED: PAIR");
    }

    @Test
    @DisplayName("Non-Winning")
    void testNO_WIN(){
        //Losing Hands
        assertFalse(pokerGame.isWinningHand(createPartialHand()));
        assertEquals("NOT A WINNER", pokerGame.getWinningHand().getHandName());
        assertFalse(pokerGame.isWinningHand(createTestHand()));
        assertEquals("NOT A WINNER", pokerGame.getWinningHand().getHandName());
    }


    // Test Hands
    static Hand createTestHand(){
        PlayersHand hand = new PlayersHand("test-hand");
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
        hand.add(new PlayingCard(Card.Suit.CLUBS, Card.Rank.SEVEN));
        hand.add(new PlayingCard(Card.Suit.HEARTS, Card.Rank.FIVE));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.JACK));
        hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.NINE));
        return hand;
    }

    static Hand createPartialHand() {
        PlayersHand hand = new PlayersHand("test-partial-hand");
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.JACK));
        hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.NINE));
        return hand;
    }


    static PlayersHand RoyalFlush(Card.Suit suit) {
        PlayersHand hand = new PlayersHand("test-royal-flush");
        hand.add(new PlayingCard(suit, Card.Rank.ACE));
        hand.add(new PlayingCard(suit, Card.Rank.KING));
        hand.add(new PlayingCard(suit, Card.Rank.QUEEN));
        hand.add(new PlayingCard(suit, Card.Rank.JACK));
        hand.add(new PlayingCard(suit, Card.Rank.TEN));
        return hand;
    }

    static PlayersHand StraightFlush(Card.Suit suit, Card.Rank start) {
        PlayersHand hand = new PlayersHand("test-straight-flush");
        Card.Rank[] ranks = Card.Rank.values();
        int startValue = start.getValue()-1;
        if (start.getValue() > Card.Rank.NINE.getValue()) {
            hand = RoyalFlush(suit);
        } else {
            hand.add(new PlayingCard(suit, start));
            hand.add(new PlayingCard(suit, ranks[startValue + 1]));
            hand.add(new PlayingCard(suit, ranks[startValue + 2]));
            hand.add(new PlayingCard(suit, ranks[startValue + 3]));
            hand.add(new PlayingCard(suit, ranks[startValue + 4]));
        }
        return hand;
    }

    static PlayersHand FourOfKind(Card.Rank rank) {
        PlayersHand hand = new PlayersHand("test-four-kind");
        hand.add(new PlayingCard(Card.Suit.CLUBS, rank));
        hand.add(new PlayingCard(Card.Suit.HEARTS, rank));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, rank));
        hand.add(new PlayingCard(Card.Suit.SPADES, rank));
        if (rank == Card.Rank.ACE) {
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.KING));
        } else {
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
        }
        return hand;
    }

    static PlayersHand FullHouse(Card.Rank pair, Card.Rank three_kind) {
        PlayersHand hand = new PlayersHand("test-full-house");
        hand.add(new PlayingCard(Card.Suit.CLUBS, three_kind));
        hand.add(new PlayingCard(Card.Suit.HEARTS, three_kind));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, three_kind));
        hand.add(new PlayingCard(Card.Suit.CLUBS, pair));
        hand.add(new PlayingCard(Card.Suit.HEARTS, pair));
        return hand;
    }

    static PlayersHand Flush(Card.Suit suit) {
        PlayersHand hand = new PlayersHand("test-flush-" + suit.getText());
        hand.add(new PlayingCard(suit, Card.Rank.ACE));
        hand.add(new PlayingCard(suit, Card.Rank.SEVEN));
        hand.add(new PlayingCard(suit, Card.Rank.EIGHT));
        hand.add(new PlayingCard(suit, Card.Rank.JACK));
        hand.add(new PlayingCard(suit, Card.Rank.TEN));
        return hand;
    }

    static PlayersHand ThreeOfKind(Card.Rank three_kind) {
        PlayersHand hand = new PlayersHand("test-three-kind");
        hand.add(new PlayingCard(Card.Suit.CLUBS, three_kind));
        hand.add(new PlayingCard(Card.Suit.HEARTS, three_kind));
        hand.add(new PlayingCard(Card.Suit.DIAMONDS, three_kind));
        if (three_kind.getValue() < 7) {
            hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.KING));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.JACK));
        } else {
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
            hand.add(new PlayingCard(Card.Suit.CLUBS, Card.Rank.TWO));
        }
        return hand;
    }

    static PlayersHand TwoPair(Card.Rank pair) {
        PlayersHand hand = new PlayersHand("test-two-pair");
        Card.Rank[] ranks = Card.Rank.values();
        hand.add(new PlayingCard(Card.Suit.CLUBS, pair));
        hand.add(new PlayingCard(Card.Suit.HEARTS, pair));
        if (pair.getValue() < 7) {
            hand.add(new PlayingCard(Card.Suit.CLUBS, ranks[(pair.getValue()+2)]));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, ranks[(pair.getValue()+2)]));
            hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.KING));
        }  else {
            hand.add(new PlayingCard(Card.Suit.CLUBS, ranks[(pair.getValue()-2)]));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, ranks[(pair.getValue()-2)]));
            hand.add(new PlayingCard(Card.Suit.CLUBS, Card.Rank.ACE));
        }
        return hand;
    }

    static PlayersHand Pair(Card.Rank pair) {
        PlayersHand hand = new PlayersHand("test-one-pair");
        Card.Rank[] ranks = Card.Rank.values();
        hand.add(new PlayingCard(Card.Suit.CLUBS, pair));
        hand.add(new PlayingCard(Card.Suit.HEARTS, pair));
        if (pair.getValue() < 7) {
            hand.add(new PlayingCard(Card.Suit.CLUBS, ranks[(pair.getValue()+2)]));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, ranks[(pair.getValue()+3)]));
            hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.KING));
        }  else {
            hand.add(new PlayingCard(Card.Suit.CLUBS, ranks[(pair.getValue()-2)]));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, ranks[(pair.getValue()-3)]));
            hand.add(new PlayingCard(Card.Suit.CLUBS, Card.Rank.ACE));
        }
        return hand;
    }

    static PlayersHand Straight(Card.Rank startAt) {
        Card.Rank[] allRanks = Card.Rank.values();
        int startValue = startAt.getValue() - 1;
        PlayersHand hand = new PlayersHand("STRAIGHT");
        if (startAt.getValue() < Card.Rank.TEN.getValue()) {
            // Others
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, startAt));
            hand.add(new PlayingCard(Card.Suit.CLUBS, allRanks[startValue + 1]));
            hand.add(new PlayingCard(Card.Suit.HEARTS, allRanks[startValue + 2]));
            hand.add(new PlayingCard(Card.Suit.SPADES, allRanks[startValue + 3]));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, allRanks[startValue + 4]));
        } else {
            //Ace High
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.ACE));
            hand.add(new PlayingCard(Card.Suit.CLUBS, Card.Rank.TEN));
            hand.add(new PlayingCard(Card.Suit.HEARTS, Card.Rank.JACK));
            hand.add(new PlayingCard(Card.Suit.SPADES, Card.Rank.QUEEN));
            hand.add(new PlayingCard(Card.Suit.DIAMONDS, Card.Rank.KING));
        }
        return hand;
    }
}