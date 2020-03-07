package seedu.address.model.deck.card;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Card {

    //New fields for FlashSpeed
    private final FrontFace frontFace;
    private final BackFace backFace;

    /**
     * Every field must be present and not null.
     */
    public Card(FrontFace frontFace, BackFace backFace) {
        this.frontFace = frontFace;
        this.backFace = backFace;
    }

    public FrontFace getFrontFace() {
        return this.frontFace;
    }

    public BackFace getBackFace() {
        return this.backFace;
    }

    /**
     * Returns true if both cards have the same front and back faces.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Card)) {
            return false;
        }

        Card otherCard = (Card) other;
        return otherCard.getFrontFace().equals(getFrontFace())
                && otherCard.getBackFace().equals(getBackFace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(frontFace, backFace);
    };

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getFrontFace())
                .append(" : ")
                .append(getBackFace());
        return builder.toString();
    };

}
