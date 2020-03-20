package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.deck.Deck;

/**
 * Unmodifiable view of a library.
 */
public interface ReadOnlyLibrary {

    /**
     * Returns an unmodifiable view of the decks list.
     * This list will not contain any duplicate decks.
     */
    ObservableList<Deck> getDeckList();
}
