package com.flashspeed.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.flashspeed.model.deck.card.Card;
import static com.flashspeed.testutil.DeckUtils.JAPANESE_DECK;
import static com.flashspeed.testutil.CardUtils.JAP_CARD_1;
import static com.flashspeed.testutil.CardUtils.JAP_CARD_2;
import static com.flashspeed.testutil.CardUtils.JAP_CARD_3;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;

class StatisticsTest {
    private static final ObservableList<Card> CARDS = JAPANESE_DECK.asObservableList();
    private static final int CORRECT_ANS = 6;
    private static final int WRONG_ANS = 6;
    private static final int TOTAL_QNS = 6;
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
    void getCorrectAns_newGame_success() {
        assertEquals(emptyStatistics.getCorrectAns(), 0);
    }

    @Test
    void getCorrectAns_ongoingGame_success() {
        assertEquals(nonEmptyStatistics.getCorrectAns(), CORRECT_ANS);
    }

    @Test
    void getWrongAns_newGame_success() {
        assertEquals(emptyStatistics.getWrongAns(),0 );
    }

    @Test
    void getWrongAns_ongoingGame_success() {
        assertEquals(emptyStatistics.getWrongAns(),WRONG_ANS);
    }

    @Test
    void getTotalQns() {
    }

    @Test
    void getScore() {
    }

    @Test
    void incrementCorrectAttempt() {
    }

    @Test
    void incrementWrongAttempt() {
    }
}