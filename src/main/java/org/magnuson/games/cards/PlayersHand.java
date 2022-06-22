package org.magnuson.games.cards;

import java.util.*;

/**
 * PlayerHand
 * The implementation class of the Card interface and represents
 * a players hand in the card game.
 */
public class PlayersHand extends ArrayList<Card> implements Hand {
    /**
     *
     */
    private String handName;

    /**
     * Creates a new instance of the PlayersHand object, containing zero Cards.
     *
     * @param handName the name to be assigned to the Hand. i.e. "Dealer's Hand"
     */
    public PlayersHand(String handName) {
        this.handName = handName;
    }

    /**
     * Creates a new instance of the PlayersHand object, containing the supplied
     * Collection supplied.
     *
     * @param handName the name to be assigned to the Hand. i.e. "Dealer's Hand"
     * @param c        a Collection of Cards that will added to the Hand.
     */
    public PlayersHand(String handName, Collection<? extends Card> c) {
        super(c);
        this.handName = handName;
    }

    /**
     * Returns the name assigned to the PlayerHand.
     *
     * @return the name of the Hand.
     */
    public String getHandName() {
        return this.handName;
    }

    @Override
    public void setHandName(String handName) {
        this.handName = handName.trim();
    }

    public List<Card> getAllRank(Card.Rank rank) {
        ArrayList<Card> results = new ArrayList<>();
        for (Card card : this) {
            if (card.getRank() == rank) results.add(card);
        }
        return results;
    }

    public List<Card> getAllSuit(Card.Suit suit) {
        ArrayList<Card> results = new ArrayList<>();
        for (Card card : this) {
            if (card.getSuit() == suit) results.add(card);
        }
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayersHand cards = (PlayersHand) o;
        return Objects.equals(handName, cards.handName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), handName);
    }

    @Override
    public String toString() {
        return "Hand: {handName=\"" + handName + "\"," + super.toString() + "}";
    }

    @Override
    public int compareTo(Hand o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    // A hand contains cards, this will be used to see if a Hand contains a specific Card.
    // or return false;
    public boolean contains(Object o) {
        if (o == null) return false;
        if (o instanceof Card) return super.contains(o);
        return false;
    }

    @Override
    // A hand contains cards, this will be used to see if a Hand contains a specific Hand.
    // or return false;
    public boolean containsAll(Collection<?> c) {
        if (c == null) return false;
        if (c instanceof Hand) return super.containsAll(c);
        return false;
    }

    public void sort() {
        Collections.sort(this);
    }
}
