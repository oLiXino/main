package seedu.address.model;


import javafx.collections.ObservableList;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.card.Card;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents statistics report for one game session.
 */
public class Statistics {

    // number of correct answers
    private int correctAns;

    // number of wrong answer
    private int wrongAns;

    //Total number of questions played
    private int totalQns;

    // hash map to keep track the number of attempts to get the correct answer for each card
    private HashMap<Card, Integer> cardAttempts;

    public Statistics(ObservableList<Card> cards) {
        this.correctAns = 0;
        this.wrongAns = 0;
        this.totalQns = 0;
        this.cardAttempts = new HashMap<>();

        // initialize the number of attempt for each card as 0
        for (int i = 0; i < cards.size(); i++) {
            cardAttempts.put(cards.get(i), 0);
        }
    }

    /**
     * Returns the number of correct answers so far.
     */
    public int getCorrectAns() {
        return this.correctAns;
    }

    /**
     * Returns the number of incorrect answers so far.
     */
    public int getWrongAns() {
        return this.wrongAns;
    }

    /**
     * Returns the total number of cards played so far.
     */
    public int getTotalQns() {
        return this.totalQns;
    }

    /**
     * Calculates the current score of the game.
     * @return the current score of the game thus far.
     */
    public long getScore() {
        return Math.round(Double.valueOf(correctAns) / Double.valueOf(totalQns) * 100);
    }

    /**
     * Increments the number of attempts of a certain card.
     */
    public void incrementCardAttempt(Card card) {
        cardAttempts.merge(card, 1, Integer::sum);
    }

    @Override
    public String toString() {
        String output = "Rounds played: " + this.totalQns + "\n" +
                "Correct Answer: " + this.correctAns + "\n" +
                "Wrong Answer: " + this.wrongAns + "\n" +
                "Score= " + getScore() + "% \n" +
                "Here is a list of cards you guessed incorrectly:" + "\n";

        for (Map.Entry<Card, Integer> entry: cardAttempts.entrySet()) {
           if (entry.getValue() > 0) {
               output += String.format("%S : %S (%d times)\n",
                       entry.getKey().getFrontFace().getValue(),
                       entry.getKey().getBackFace().getValue(),
                       entry.getValue());
           }
        }

        return output;
    }

}
