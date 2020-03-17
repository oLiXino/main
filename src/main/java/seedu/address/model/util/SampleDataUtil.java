package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.dump.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Deck[] getSamplePersons() {
        return new Deck[] {
            new Deck(new Name("Alex Yeoh")),
            new Deck(new Name("Bernice Yu")),
            new Deck(new Name("Charlotte Oliveiro")),
            new Deck(new Name("David Li")),
            new Deck(new Name("Irfan Ibrahim")),
            new Deck(new Name("Roy Balakrishnan"))
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
