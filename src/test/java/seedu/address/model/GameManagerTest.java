package seedu.address.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Deckdeck.JAPANESE_DECK;

import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;


public class GameManagerTest {

    private GameManager gameManager = new GameManager(JAPANESE_DECK);

    @Test
    public void flip_alreadyFlip_returnsNull() {
        gameManager.flip();
        BackFace backFace = gameManager.flip();
        assertEquals(backFace, null);
    }

    @Test
    public void getBackFace_flip_returnsTrue() {
        BackFace backFace = gameManager.flip();
        Card card = JAPANESE_DECK.asUnmodifiableObservableList().get(gameManager.getCurrCardIdx());
        assertEquals(backFace, card.getBackFace());
    }


    @Test
    public void getNextCard_answerYes_returnsTrue() {
        gameManager.flip();
        Card nextCard = gameManager.answerYes();
        Card card = JAPANESE_DECK.asUnmodifiableObservableList().get(gameManager.getCurrCardIdx());
        assertEquals(nextCard, card);
    }

    @Test void answerYes_finishAllCards_returnsNull() {
        gameManager.flip();
        gameManager.answerYes();
        gameManager.flip();
         gameManager.answerYes();
        gameManager.flip();
        Card nextCard = gameManager.answerYes();
        assertEquals(nextCard, null);
    }

    @Test
    public void getNextCard_answerNo_returnsTrue() {
        gameManager.flip();
        Card nextCard = gameManager.answerNo();
        Card card = JAPANESE_DECK.asUnmodifiableObservableList().get(gameManager.getCurrCardIdx());
        assertEquals(nextCard, card);
    }



}
