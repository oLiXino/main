package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;
import seedu.address.model.deck.dump.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Deck[] getSamplePersons() {
        Deck sampleDeck = new Deck(new Name("Sample Deck 1"));
        sampleDeck.add(new Card(new FrontFace("front"), new BackFace("back")));
        return new Deck[] {
            sampleDeck
        };
    }

    public static ReadOnlyLibrary getSampleAddressBook() {
        Library sampleAb = new Library();
        for (Deck sampleDeck : getSamplePersons()) {
            sampleAb.addPerson(sampleDeck);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
