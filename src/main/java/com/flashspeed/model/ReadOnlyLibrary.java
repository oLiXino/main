package com.flashspeed.model;

import javafx.collections.ObservableList;
import com.flashspeed.model.deck.Deck;

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
