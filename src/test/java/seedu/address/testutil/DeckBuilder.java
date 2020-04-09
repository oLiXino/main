package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.UniqueCardList;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Deck objects.
 */
public class DeckBuilder {

    public static final String DEFAULT_NAME = "Japanese";
    public static final Deck DEFAULT_DECK = new Deck(new Name("Default deck"));

    private Name name;
    private ArrayList<Card> cards;

    public DeckBuilder() {
        name = new Name(DEFAULT_NAME);
    }

    /**
     * Initializes the DeckBuilder with the data of {@code personToCopy}.
     */
    public DeckBuilder(Deck deckToCopy) {
        name = deckToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Deck} that we are building.
     */
    public DeckBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Adds a list of cards to the deck that we are building.
     */
    public DeckBuilder withCards(List<Card> cards) {
        for (Card card : cards) {
            this.cards.add(card);
        }
        return this;
    }

    /**
     * Adds a card to the deck that we are building.
     */
    public DeckBuilder withCard(Card card) {
       cards.add(card);
       return this;
    }

    /**
     * Builds a deck for testing purposes.
     *
     * @return The deck object built.
     */
    public Deck build() {
        Deck deck = new Deck(name);
        for (Card card : cards) {
            deck.add(card);
        }
        return deck;
    }

}
