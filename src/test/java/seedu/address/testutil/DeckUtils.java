package seedu.address.testutil;
import static seedu.address.testutil.CardUtils.JAP_CARDS;
import static seedu.address.testutil.CardUtils.MALAY_CARDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Library;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class DeckUtils {

    public static final Deck JAPANESE_DECK = new DeckBuilder()
            .withName("Japanese")
            .withCards(JAP_CARDS).build();
    public static final Deck MALAY_DECK = new DeckBuilder()
            .withName("Malay")
            .withCards(MALAY_CARDS).build();
    public static final String EMPTY_DECK_NAME = "";

    public static final Deck NEW_EMPTY_DECK = new Deck(new Name("Empty deck"));

    // Manually added

    // Manually added - Person's details found in {@code CommandTestUtil}

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private DeckUtils() {} // prevents instantiation

    public static Deck getTypicalJapDeck() {
        return JAPANESE_DECK;
    }

    public static Deck getTypicalMalayDeck() {
        return MALAY_DECK;
    }
    /**
     * Returns an {@code Libary} with all the typical decks.
     */
    public static Library getTypicalLibrary() {
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
