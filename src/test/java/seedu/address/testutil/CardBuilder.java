package seedu.address.testutil;

import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_NAME = "Japanese";
    public static final Deck DEFAULT_DECK = new Deck(new Name("Default deck"));

    private FrontFace frontFace;
    private BackFace backFace;

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        this.frontFace = cardToCopy.getFrontFace();
        this.backFace = cardToCopy.getBackFace();
    }

    /**
     * Default constructor.
     */
    public CardBuilder() {

    }

    /**
     * Sets the front face of the card that we are building.
     */
    public CardBuilder withFrontFace(String front) {
        FrontFace frontFace = new FrontFace(front);
        this.frontFace = frontFace;
        return this;
    }

    /**
     * Sets the back face of the card that we are building.
     */
    public CardBuilder withBackFace(String back) {
        BackFace backFace = new BackFace(back);
        this.backFace = backFace;
        return this;
    }

    /**
     * Builds the card with the intended front face and back face value.
     *
     * @return A Card object.
     */
    public Card build() {
        return new Card(frontFace, backFace);
    }

}
