package seedu.address.model.deck.card;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Card {

    //New fields for FlashSpeed
    private final Face frontFace = new Face("Dummy Front");
    private final Face backFace = new Face("Dummy Back");

    /*
    public boolean isSameDeck(Card otherDeck) {
        return true;
    }
    */

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    };

    @Override
    public String toString() {
        return "";
    };

}
