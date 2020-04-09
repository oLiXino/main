package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class CardUtils {

    public static final String EMPTY_FACE = "";

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

    private CardUtils() {} // prevents instantiation

    /**
     * Gets the Japanese test deck.
     */
    public static List<Card> japCards = new ArrayList<>(
            Arrays.asList(cardJap1, cardJap2, cardJap3));


    /**
     * Gets the Malay test deck.
     */
    public static List<Card> malayCards = new ArrayList<>(
            Arrays.asList(cardMalay1, cardMalay2, cardMalay3));

}
