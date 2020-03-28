package seedu.address.model;


import javafx.collections.ObservableList;
import seedu.address.model.deck.card.Card;

import java.util.HashMap;

/**
 * Represents statistics report for one game session.
 */
public class Statistics {
    // number of correct answers
    private int correctAns;

    // number of wrong answer
    private int wrongAns;

    // number of maximum attempts
    private int maxAttempt;

    // hash map to keep track the number of attempts to get the correct answer for each card
    private HashMap<Card, Integer> cardAttempts;

    public Statistics(GameManager game) {
        this.correctAns = 0;
        this.wrongAns = 0;
        this.maxAttempt = 0;
        this.cardAttempts = new HashMap<>();

        // initialize the number of attempt for each card as 0
        ObservableList cards = game.getCards();
        for (int i = 0; i < cards.size(); i++) {
            cardAttempts.put((Card)cards.get(i), 0);
        }
    }

    public int getCorrectAns() {
        return this.correctAns;
    }

    public int getWrongAns() {
        return this.wrongAns;
    }

    public int getMaxAttempt() {
        return this.maxAttempt;
    }

    /**
     * Updates statistics when user answers the card correctly.
     */
    public void answerYes(Card card) {
        int numOfAttempt = cardAttempts.get(card);
        if (numOfAttempt == 0) {     // answer yes for the first attempt
            this.correctAns++;       // increment number of correct answers
        }
    }

    /**
     * Updates statistics when user answers the card wrongly.
     */
    public void answerNo(Card card) {
        int numOfAttempt = cardAttempts.get(card);
        numOfAttempt++;
        cardAttempts.put(card, numOfAttempt); // increment the number of attempts
        if (numOfAttempt > maxAttempt) {
            maxAttempt = numOfAttempt;      // update maximum attempts
        }
    }

    @Override
    public String toString() {
        return "Correct Answer: " + this.correctAns + "\n" +
                "Wrong Answer: " + this.wrongAns + "\n" +
                "Maximum attempts: " + this.maxAttempt;
    }

}
