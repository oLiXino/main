package seedu.address.model.util;

import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * Contains utility methods for populating {@code Library} with sample data.
 */
public class SampleDataUtil {
    public static Deck[] getSampleDecks() {
        Deck sampleDeck = new Deck(new Name("Sample Deck 1"));
        sampleDeck.add(new Card(new FrontFace("front"), new BackFace("back")));
        return new Deck[] {
            sampleDeck
        };
    }

    public static ReadOnlyLibrary getSampleLibrary() {
        Library sampleLib = new Library();
        for (Deck sampleDeck : getSampleDecks()) {
            sampleLib.addDeck(sampleDeck);
        }
        return sampleLib;
    }
}
