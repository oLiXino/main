package seedu.address.model.deck.card;

/**
 * Represents a face of a card.
 */
public abstract class Face {
    private final String value;

    public Face(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
