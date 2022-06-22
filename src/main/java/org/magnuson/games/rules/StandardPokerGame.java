package org.magnuson.games.rules;

import org.magnuson.games.Game;
import org.magnuson.games.cards.Card;
import org.magnuson.games.cards.Hand;
import org.magnuson.games.cards.PlayersHand;

import java.util.List;

/**
 *
 */
public class StandardPokerGame implements RulesManager {
    private final static int handSize = 5;
    private final Game game;
    private final PlayersHand winningHand = new PlayersHand("NOT A WINNER");

    /**
     * Creates the StandardPokerGame and assigns the Game object to it
     * @param game the Game object used to create the instance
     */
    public StandardPokerGame(Game game) {
        this.game = game;
    }

    /**
     * Returns the name of the Game.
     * @return the name of the Game.
     */
    public String getGameName() {
        return game.getName();
    }

    public Hand getWinningHand() {
        return this.winningHand;
    }

    public boolean isWinningHand(Hand hand) {
        winningHand.clear();
        winningHand.setHandName("NOT A WINNER");
        if (isValidHand(hand)) {
            if (isFlushHand(hand)) return true;
            if (isFourOfKind(hand)) return true;
            if (isPairsBasedHand(hand)) return true;
            return isStraightHand(hand);
        }
        return false;
     }

    private boolean isValidHand(Hand hand) {
        if (hand == null) return false;
        logger.debug("Valid Hand:" + (hand.size() == handSize));
        return hand.size() == handSize;
    }

    private boolean isFourOfKind(Hand hand) {
        hand.sort(new Hand.SortByRankThenSuit());
        List<Card> results = hand.getAllRank(hand.get(2).getRank());
        if (results.size() == 4) {
            winningHand.setHandName("FOUR OF A KIND");
            winningHand.addAll(results);
            logger.debug("WIN:" + winningHand);
            return true;
        }
        return false;
    }

    private boolean isFlushHand(Hand hand) {
        ((PlayersHand) hand).sort();
        List<Card> results = hand.getAllSuit(hand.get(0).getSuit());
        if (results.size() == 5) {
            if ((results.get(4).getRank().getValue() - results.get(0).getRank().getValue()) == 12) {
                winningHand.setHandName("ROYAL FLUSH");
            } else if ((results.get(4).getRank().getValue() - results.get(0).getRank().getValue()) == 4) {
                winningHand.setHandName("STRAIGHT FLUSH");
            } else {
                winningHand.setHandName("FLUSH");
            }
            winningHand.addAll(results);
            logger.debug("WIN:" + winningHand);
            return true;
        }
        return false;
    }

    private boolean isPairsBasedHand(Hand hand){
        hand.sort(new Hand.SortByRankThenSuit());
        for (Card.Rank rank : Card.Rank.values()) {
            List<Card> results = hand.getAllRank(rank);
            if (results.size() > 1) winningHand.addAll(results);
        }
        switch (winningHand.size()) {
            case 5:
                winningHand.setHandName("FULL HOUSE");
                logger.debug("WIN:" + winningHand);
                return true;
            case 4:
                winningHand.setHandName("TWO PAIR");
                logger.debug("WIN:" + winningHand);
                return true;
            case 3:
                winningHand.setHandName("THREE OF A KIND");
                logger.debug("WIN:" + winningHand);
                return true;
            case 2:
                winningHand.setHandName("PAIR");
                logger.debug("WIN:" + winningHand);
                return true;
            default:
                return false;
        }
    }

    private boolean isStraightHand(Hand hand) {
        hand.sort(new Hand.SortByRankThenSuit());
        int diff = (hand.get(4).getRank().getValue() - hand.get(0).getRank().getValue());
        logger.trace("Difference in Rank " + diff);
        if (diff == 4 || diff == 12) {
            winningHand.addAll(hand);
            winningHand.setHandName("STRAIGHT");
            logger.debug("WIN:" + winningHand);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("StandardPokerGame: { winningHand=%s }", winningHand);
    }
}