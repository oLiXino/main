package seedu.address.model.deck;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.UniqueCardList;

/**
 * Represents a Deck in the library.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deck {
    
    // todo: what other fields represent unique identity of a deck?
    // Identity fields
    private final Name name;

    // Data fields
    private final UniqueCardList cards = new UniqueCardList();

    /**
     * Every field must be present and not null.
     */
    public Deck(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    // todo remove vvv
    
    public Name getName() {
        return name;
    }

    /**
     * Returns true if the list contains an equivalent card as the given argument.
     */
    public boolean contains(Card toCheck) {
        return cards.contains(toCheck);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Card toAdd) {
        cards.add(toAdd);
    }

    /**
     * Removes the equivalent card from the list.
     * The card must exist in the list.
     */
    public void remove(Card toRemove) {
        cards.remove(toRemove);
    }

    /**
     * Replaces the equivalent card from the list with the new card.
     * The old card must exist in the list and the new card must not already exist in the list.
     */
    public void replace(Card toRemove, Card toAdd) {
        cards.replace(toRemove, toAdd);
    }

    /**
     * Returns true if both decks have the same name.
     * This defines a weaker notion of equality between two decks.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        return otherDeck != null
                && otherDeck.getName().equals(getName());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Card> asUnmodifiableObservableList() {
        return cards.asUnmodifiableObservableList();
    }

    /**
     * Returns true if both decks have the same identity and data fields.
     * This defines a stronger notion of equality between two decks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deck)) {
            return false;
        }

        Deck otherDeck = (Deck) other;
        return otherDeck.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getName());

        return builder.toString();
    }
}
