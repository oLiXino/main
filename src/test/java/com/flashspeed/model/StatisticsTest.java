package com.flashspeed.model;

import static com.flashspeed.testutil.CardUtils.JAP_CARD_1;
import static com.flashspeed.testutil.CardUtils.JAP_CARD_2;
import static com.flashspeed.testutil.CardUtils.JAP_CARD_3;
import static com.flashspeed.testutil.DeckUtils.JAPANESE_DECK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.flashspeed.model.deck.card.Card;

import javafx.collections.ObservableList;

class StatisticsTest {
    private static final ObservableList<Card> CARDS = JAPANESE_DECK.asObservableList();
    private static final int CORRECT_ANS = 6;
    private static final int WRONG_ANS = 15;
    private static final int TOTAL_QNS = 21;
    private static final int JAP_CARD_1_CORRECT = 1;
    private static final int JAP_CARD_2_CORRECT = 2;
    private static final int JAP_CARD_3_CORRECT = 3;
    private static final int JAP_CARD_1_WRONG = 4;
    private static final int JAP_CARD_2_WRONG = 5;
    private static final int JAP_CARD_3_WRONG = 6;
    private static final Map<Card, Integer> TOTAL_ATTEMPTS = new HashMap<>() {
        {
            put(JAP_CARD_1, JAP_CARD_1_CORRECT + JAP_CARD_1_WRONG);
            put(JAP_CARD_2, JAP_CARD_2_CORRECT + JAP_CARD_2_WRONG);
            put(JAP_CARD_3, JAP_CARD_3_CORRECT + JAP_CARD_3_WRONG);
        }
    };

    private static final Map<Card, Integer> CORERCT_ATTEMPTS = new HashMap<>() {
        {
            put(JAP_CARD_1, JAP_CARD_1_CORRECT);
            put(JAP_CARD_2, JAP_CARD_2_CORRECT);
            put(JAP_CARD_3, JAP_CARD_3_CORRECT);
        }
    };

    private static final Map<Card, Integer> WRONG_ATTEMPTS = new HashMap<>() {
        {
            put(JAP_CARD_1, JAP_CARD_1_WRONG);
            put(JAP_CARD_2, JAP_CARD_2_WRONG);
            put(JAP_CARD_3, JAP_CARD_3_WRONG);
        }
    };

    private Statistics emptyStatistics = new Statistics(CARDS);

    private Statistics nonEmptyStatistics = new Statistics(CORRECT_ANS, WRONG_ANS, TOTAL_QNS,
            TOTAL_ATTEMPTS, CORERCT_ATTEMPTS, WRONG_ATTEMPTS, CARDS);

    @Test
    void getCorrectAns_newGame_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> emptyStatistics.getCorrectAns());
    }

    @Test
    void getCorrectAns_ongoingGame_success() {
        assertEquals(nonEmptyStatistics.getCorrectAns(), CORRECT_ANS);
    }

    @Test
    void getWrongAns_newGame_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> emptyStatistics.getWrongAns());
    }

    @Test
    void getWrongAns_ongoingGame_success() {
        assertEquals(nonEmptyStatistics.getWrongAns(), WRONG_ANS);
    }

    @Test
    void getTotalQns_newGame_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> emptyStatistics.getTotalQns());
    }

    @Test
    void getTotalQns_ongoingGame_success() {
        assertEquals(nonEmptyStatistics.getTotalQns(), TOTAL_QNS);
    }

    @Test
    void getScore_newGame_throwsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> emptyStatistics.getScore());
    }

    @Test
    void getScore_ongoingGame_success() {
        long score = Math.round(Double.valueOf(CORRECT_ANS) / Double.valueOf(TOTAL_QNS) * 100);
        assertEquals(nonEmptyStatistics.getScore(), score);
    }

    @Test
    void incrementCorrectAttempt_success() {
        nonEmptyStatistics.incrementCorrectAttempt(JAP_CARD_1);
        long score = Math.round(Double.valueOf(CORRECT_ANS + 1) / Double.valueOf(TOTAL_QNS + 1) * 100);
        assertEquals(nonEmptyStatistics.getScore(), score);
    }

    @Test
    void incrementWrongAttempt_success() {
        nonEmptyStatistics.incrementWrongAttempt(JAP_CARD_1);
        long score = Math.round(Double.valueOf(CORRECT_ANS) / Double.valueOf(TOTAL_QNS + 1) * 100);
        assertEquals(nonEmptyStatistics.getScore(), score);
    }

}
