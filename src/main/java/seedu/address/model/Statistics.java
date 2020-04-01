package seedu.address.model;


import javafx.collections.ObservableList;
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
    private HashMap<Card, Integer> totalAttempts;
    private HashMap<Card, Integer> correctAttempts;
    private HashMap<Card, Integer> wrongAttempts;

    public Statistics(ObservableList<Card> cards) {
        this.correctAns = 0;
        this.wrongAns = 0;
        this.totalQns = 0;
        this.totalAttempts = new HashMap<>();
        this.correctAttempts = new HashMap<>();
        this.wrongAttempts = new HashMap<>();

        // initialize the number of attempt for each card as 0
        for (int i = 0; i < cards.size(); i++) {
            totalAttempts.put(cards.get(i), 0);
            correctAttempts.put(cards.get(i), 0);
            wrongAttempts.put(cards.get(i), 0);
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
    private void incrementAttempt(Card card) {
        totalAttempts.merge(card, 1, Integer::sum);
    }


    /**
     * Increments the number of correct attempts of a certain card.
     */
    public void incrementCorrectAttempt(Card card) {
        ++correctAns;
        ++totalQns;
        correctAttempts.merge(card, 1, Integer::sum);
        incrementAttempt(card);
    }

    /**
     * Increments the number of correct attempts of a certain card.
     */
    public void incrementWrongAttempt(Card card) {
        ++wrongAns;
        ++totalQns;
        wrongAttempts.merge(card, 1, Integer::sum);
        incrementAttempt(card);
    }

    @Override
    public String toString() {
        String output = "Rounds played: " + this.totalQns + "\n" +
                "Correct Attempts: " + this.correctAns + "\n" +
                "Wrong Attempts: " + this.wrongAns + "\n" +
                "Score= " + String.format("%2d", getScore()) + "% \n" +
                "Here is a list of cards you guessed incorrectly:" + "\n";

        for (Map.Entry<Card, Integer> entry: wrongAttempts.entrySet()) {
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
