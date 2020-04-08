package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalCards {

    public static final Card cardJap1 = new CardBuilder()
            .withFrontFace(new FrontFace("Hello"))
            .withBackFace(new BackFace(" こんにちは"))
            .build();

    public static final Card cardJap2 = new CardBuilder()
            .withFrontFace(new FrontFace("Goodbye"))
            .withBackFace(new BackFace(" さよなら"))
            .build();

    public static final Card cardJap3 = new CardBuilder()
            .withFrontFace(new FrontFace("Thank you"))
            .withBackFace(new BackFace(" ありがとう"))
            .build();

    public static final Card cardMalay1 = new CardBuilder()
            .withFrontFace(new FrontFace("I"))
            .withBackFace(new BackFace("Saya"))
            .build();

    public static final Card cardMalay2 = new CardBuilder()
            .withFrontFace(new FrontFace("You"))
            .withBackFace(new BackFace("Awak"))
            .build();

    public static final Card cardMalay3 = new CardBuilder()
            .withFrontFace(new FrontFace("Chicken"))
            .withBackFace(new BackFace("Ayam"))
            .build();

    // Manually added

    // Manually added - Person's details found in {@code CommandTestUtil}

    public static final String KEYWORD_MATCHING_MEIER = "Hello"; // A keyword that matches MEIER

    private TypicalCards() {} // prevents instantiation

    public static List<Card> getTypicalJapCards() {
        return new ArrayList<>(Arrays.asList(cardJap1, cardJap2, cardJap3));
    }

    public static List<Card> getTypicalMalayCards() {
        return new ArrayList<>(Arrays.asList(cardMalay1, cardMalay2, cardMalay3));
    }
}
