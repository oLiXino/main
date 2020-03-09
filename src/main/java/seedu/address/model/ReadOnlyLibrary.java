package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.deck.Deck;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyLibrary {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Deck> getPersonList();
    ObservableList<Deck> getDeckList();
}
