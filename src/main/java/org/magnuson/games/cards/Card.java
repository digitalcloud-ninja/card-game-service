package org.magnuson.games.cards;

/**
 * Card Interface
 * The card interface represents the concept of a playing card in a deck of cards.
 * <p>
 * @author William Magnuson
 * @version 1.0
 */
public interface Card extends Comparable<Card> {

    /**
     * Card Suit
     * Represents the suit of the card.
     */
    enum Suit {
        /** 1 */
        DIAMONDS(1, "Diamonds"),
        /** 2 */
        CLUBS(2, "Clubs"),
        /** 3 */
        HEARTS(3, "Hearts"),
        /** 4 */
        SPADES(4, "Spades");

        private final int value;
        private final String text;

        Suit(int value, String text) {
            this.value = value;
            this.text = text;
        }
        /**
         * Return the Numeric Value of the Suit
         * @return int
         */
        public int getValue() {
            return this.value;
        }
        /**
         * Return the String Value of the Suit
         * @return String
         */
        public String getText() {
            return this.text;
        }
    }

    /**
     * Card Rank
     * Represents the rank of the card.
     */
    enum Rank {
        /** 1 */
        ACE(1, "Ace"),
        /** 2 */
        TWO(2, "Two"),
        /** 3 */
        THREE(3, "Three"),
        /** 4 */
        FOUR(4, "Four"),
        /** 5 */
        FIVE(5, "Five"),
        /** 6 */
        SIX(6, "Six"),
        /** 7 */
        SEVEN(7, "Seven"),
        /** 8 */
        EIGHT(8, "Eight"),
        /** 9 */
        NINE(9, "Nine"),
        /** 10 */
        TEN(10, "Ten"),
        /** 11 */
        JACK(11, "Jack"),
        /** 12 */
        QUEEN(12, "Queen"),
        /** 13 */
        KING(13, "King");

        private final int value;
        private final String text;

        Rank(int value, String text) {
            this.value = value;
            this.text = text;
        }
        /**
         * Return the Numeric Value of the Rank
         * @return int
         */
        public int getValue() {
            return this.value;
        }
        /**
         * Return the String Value of the Rank
         * @return String
         */
        public String getText() {
            return this.text;
        }
    }

    /**
     * Return the Rank from the Card
     * @return Rank
     */
    Card.Rank getRank();

    /**
     * Return the Suit from the Card
     * @return Suit
     */
    Card.Suit getSuit();
}
