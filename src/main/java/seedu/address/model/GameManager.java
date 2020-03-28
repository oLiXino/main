package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;

/**
 * Represents the model of the game session.
 */

public class GameManager {
    private boolean flipped;
    private ObservableList<Card> cards;
    private int correctAns;
    private int wrongAns;
    private int counter;

    /**
     * Initializes a GameManager with the given deck.
     */
    public GameManager(Deck deck) {
        this.flipped = false;
//        this.cards = deck.asUnmodifiableObservableList();
        this.cards = deck.asObservableList();
        this.correctAns = 0;
        this.wrongAns = 0;
        this.counter = 0;
    }
    
    public boolean isFlipped() {
        return this.flipped;
    }

    /**
     * Flips the card to the back face.
     *
     * @return true if the card has not been flipped, false otherwise
     */
    public BackFace flip() {
        if (flipped) {
            return null;
        }
        flipped = true;
        return cards.get(counter).getBackFace();
    }

    /**
     * Returns the next card after user answers Yes.
     * 
     * @return the next card or null if card list is empty
     */
    public Card answerYes() {
        this.correctAns++;
//        counter++;
        cards.remove(counter);
        flipped = false;
        
        if (counter == cards.size()) {
            return null;
        }
        
        return cards.get(counter);
    }

    /**
     * Returns the next card after user answers No.
     * 
     * @return the next card or null if card list is empty
     */
    public Card answerNo() {
        this.wrongAns++;
        counter++;
        flipped = false;
        
        if (counter == cards.size()) {
            return null;
        }
        
        return cards.get(counter);
    }
}
