package org.magnuson.games.cards;

import java.util.*;

/**
 * DeckOfCards
 */
public class DeckOfCards extends ArrayList<Card> implements Deck {
    /**
     * Deck Count
     */
    private int deckCount = 1;

    /**
     * Constructor
     * @param initialDeckCount Create this Deck Object with X amount of Decks.
     *                         Where X representS a Deck of 52 Cards.
     */
    public DeckOfCards(int initialDeckCount) {
        super(initialDeckCount * 52);
        this.deckCount = initialDeckCount;
        reset();
    }

    /**
     *  Constructor
     */
    public DeckOfCards() {
        super(52);
        reset();
    }

    /**
     * Constructor
     * @param c {@code Collection} of Cards
     */
    public DeckOfCards(Collection<? extends Card> c) {
        super(c);
        this.deckCount = c.size() / 52;
    }

    @Override
    public int getDeckCount() {
        return this.deckCount;
    }

    @Override
    public Card dealSingleCard() {
        if (iterator().hasNext()) {
            Card topCard = iterator().next();
            remove(topCard);
            return topCard;
        }
        return null;
    }

    @Override
    public List<Card> dealMultipleCards(int numCards) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            if(!isEmpty()) {
                cards.add(dealSingleCard());
            }
        }
        return cards;
    }

    @Override
    public void shuffle() {
        //Usually a dealer shuffles several times, before dealing. So!
        Collections.shuffle(this, new Random());
        Collections.shuffle(this, new Random());
        Collections.shuffle(this, new Random());
        Collections.shuffle(this, new Random());
        Collections.shuffle(this, new Random());
    }

    @Override
    public void reset() {
        clear();
        for (int i = 0; i < this.deckCount; i++) {
            addAll(Deck.createDeck());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeckOfCards cards = (DeckOfCards) o;
        return deckCount == cards.deckCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deckCount);
    }

    @Override
    public String toString() {
        return "Deck{ deckCount=" + deckCount +
        ", cards=" + super.toString() + "}";
    }
}
