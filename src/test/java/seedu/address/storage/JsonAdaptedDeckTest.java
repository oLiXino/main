package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDeck.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DeckUtils.EMPTY_DECK_NAME;
import static seedu.address.testutil.DeckUtils.JAPANESE_DECK;
import static seedu.address.testutil.DeckUtils.MALAY_DECK;
import static seedu.address.testutil.CardUtils.JAP_CARDS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.testutil.CardUtils;

public class JsonAdaptedDeckTest {
    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedDeck deck = new JsonAdaptedDeck(MALAY_DECK);
        assertEquals(MALAY_DECK, deck.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDeck deck = new JsonAdaptedDeck(new Name(""), JAP_CARDS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, deck::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDeck jsonDeck = new JsonAdaptedDeck(null, JAP_CARDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonDeck::toModelType);
    }

    @Test
    public void toModelType_nullDeck_throwsIllegalValueException() {
        JsonAdaptedDeck jsonDeck = new JsonAdaptedDeck("Hapanese", null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, jsonDeck::toModelType);
    }

}
