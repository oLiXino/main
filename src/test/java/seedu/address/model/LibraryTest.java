package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.CardUtils.JAP_CARDS;
import static seedu.address.testutil.DeckUtils.JAPANESE_DECK;

import java.util.Collections;

import org.junit.jupiter.api.Test;


import seedu.address.model.deck.Deck;
import seedu.address.testutil.DeckBuilder;

import seedu.address.testutil.LibraryBuilder;


public class LibraryTest {

    private final Library library = new Library();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), library.getDeckList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Library newData = new LibraryBuilder().withDeck(JAPANESE_DECK).build();
        library.resetData(newData);
        assertEquals(newData, library);
    }


    @Test
    public void hasDeck_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.hasDeck(null));
    }

    @Test
    public void hasDeck_deckNotIn_returnsFalse() {
        assertFalse(library.hasDeck(JAPANESE_DECK));
    }

    @Test
    public void hasDeck_deckIn_returnsTrue() {
        library.addDeck(JAPANESE_DECK);
        assertTrue(library.hasDeck(JAPANESE_DECK));
    }

    @Test
    public void hasDeck_deckWithSameIdentityFields_returnsTrue() {
        library.addDeck(JAPANESE_DECK);
        Deck newDeck = new DeckBuilder()
                .withName("Japanese")
                .withCards(JAP_CARDS).build();
        assertTrue(library.hasDeck(newDeck));
    }

    @Test
    public void getDeckList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> library.getDeckList().remove(0));
    }


}
