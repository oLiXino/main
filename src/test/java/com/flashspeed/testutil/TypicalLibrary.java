package com.flashspeed.testutil;

import com.flashspeed.model.Library;
import com.flashspeed.model.deck.Deck;
import com.flashspeed.model.deck.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.flashspeed.logic.commands.CommandTestUtil;

public class TypicalLibrary {

    public static final Card koreanHello = new CardBuilder().withInput(CommandTestUtil.VALID_CARD_NAME_KOREAN_1_HELLO).build();
    public static final Card koreanYes = new CardBuilder().withInput(CommandTestUtil.VALID_CARD_NAME_KOREAN_1_YES).build();
    public static final Card koreanNo = new CardBuilder().withInput(CommandTestUtil.VALID_CARD_NAME_KOREAN_1_NO).build();
    public static final Card koreanWeekends = new CardBuilder().withInput(CommandTestUtil.VALID_CARD_NAME_KOREAN_2_WEEKENDS).build();
    public static final Card koreanWeekdays = new CardBuilder().withInput(CommandTestUtil.VALID_CARD_NAME_KOREAN_2_WEEKDAYS).build();
    public static final Card koreanSunday = new CardBuilder().withInput(CommandTestUtil.VALID_CARD_NAME_KOREAN_2_SUNDAY).build();

    public static final ArrayList<Card> korean1List = new ArrayList<>(
            Arrays.asList(koreanHello, koreanYes, koreanNo));
    public static final ArrayList<Card> korean2List = new ArrayList<>(
            Arrays.asList(koreanWeekends, koreanWeekdays, koreanSunday));

    public static final Deck korean1 = new DeckBuilder().withName(CommandTestUtil.VALID_DECK_NAME_KOREAN_1).withCards(korean1List).build();
    public static final Deck korean2 = new DeckBuilder().withName(CommandTestUtil.VALID_DECK_NAME_KOREAN_2).withCards(korean2List).build();

    private TypicalLibrary() {

    }

    /**
     * Returns an {@code Library} with all the typical Decks and Cards.
     */
    public static Library getTypicalLibrary() {
        Library library = new Library();
        for (Deck deck : getTypicalDecks()) {
            library.addDeck(deck);
        }

        return library;
    }

    public static List<Deck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(korean1, korean2));
    }
}
