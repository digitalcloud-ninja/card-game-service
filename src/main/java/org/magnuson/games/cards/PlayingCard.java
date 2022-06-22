package org.magnuson.games.cards;

/**
 * PlayingCard
 * The PlayCard implements the Card interface and represents one card in a Deck.
 *
 * @author William Magnuson
 * @version 1.0
 * @see org.magnuson.games.cards.Card
 */
public class PlayingCard implements Card {
    private final Card.Suit suit;
    private final Card.Rank rank;

    /**
     * Create a new PlayingCard object
     * @param suit the Suit to assign the PlayingCard
     * @param rank the Rank to assign the PlayingCard
     */
    public PlayingCard(Card.Suit suit, Card.Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Return the Rank from the Card
     *
     * @return Card.Rank
     */
    @Override
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Return the Suit from the Card
     *
     * @return Card.Suit
     */
    @Override
    public Suit getSuit() {
        return this.suit;
    }

    @Override
    public int hashCode() {
        return ((suit.getValue() - 1) * 13) + rank.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Card) {
            return ((Card) obj).getRank() == this.rank && ((Card) obj).getSuit() == this.suit;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%1$S_OF_%2$S", this.rank.getText(), this.suit.getText());
    }

    @Override
    public int compareTo(Card card) {
        return this.hashCode() - card.hashCode();
    }
}
