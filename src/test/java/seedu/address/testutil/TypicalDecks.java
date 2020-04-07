package seedu.address.testutil;
import static seedu.address.testutil.TypicalCards.getTypicalJapCards;
import static seedu.address.testutil.TypicalCards.getTypicalMalayCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Library;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.card.Card;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalDecks {

    public static final Deck JAPANESE_DECK = new DeckBuilder()
            .withName("Japanese")
            .withCards(getTypicalJapCards()).build();
    public static final Deck MALAY_DECK = new DeckBuilder()
            .withName("Malay")
            .withCards(getTypicalMalayCards()).build();

    // Manually added

    // Manually added - Person's details found in {@code CommandTestUtil}

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDecks() {} // prevents instantiation

    public Deck getTypicalJapDeck() {
        return JAPANESE_DECK;
    }

    public Deck getTypicalMalayDeck() {
        return MALAY_DECK;
    }
    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Library getTypicalAddressBook() {
        Library library = new Library();
        for (Deck deck : getTypicalDecks()) {
            library.addDeck(deck);
        }
        return library;
    }

    public static List<Deck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(JAPANESE_DECK, MALAY_DECK));
    }
}
