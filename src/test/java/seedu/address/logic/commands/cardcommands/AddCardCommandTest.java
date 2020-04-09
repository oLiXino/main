package seedu.address.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.GameManager;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Statistics;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.util.Mode;
import seedu.address.model.util.View;
import seedu.address.testutil.CardBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class AddCardCommandTest {

    @Test
    public void constructor_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCardCommand(null));
    }

    @Test
    public void execute_cardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCardAdded modelStub = new ModelStubAcceptingCardAdded();
        Card validCard = new CardBuilder().build();

        CommandResult commandResult = new AddCardCommand(validCard).execute(modelStub);

        assertEquals(String.format(AddCardCommand.MESSAGE_SUCCESS, validCard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCard), modelStub.cardsAdded);
    }

    @Test
    public void execute_notInViewMode_throwsCommandException() {
        ModelStubPlayMode modelStub = new ModelStubPlayMode();
        Card validCard = new CardBuilder().build();
        AddCardCommand addCardCommand = new AddCardCommand(validCard);

        assertThrows(CommandException.class, AddCardCommand.MESSAGE_NOT_IN_VIEW_MODE, () -> addCardCommand.execute(modelStub));
    }

    @Test
    public void execute_notInDeckView_throwsCommandException() {
        ModelStubLibraryView modelStub = new ModelStubLibraryView();
        Card validCard = new CardBuilder().build();
        AddCardCommand addCardCommand = new AddCardCommand(validCard);

        assertThrows(CommandException.class, Messages.MESSAGE_NOT_IN_DECK_VIEW, () -> addCardCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Card card1 = new CardBuilder().withFrontFace("Hello").withBackFace("안녕하세요").build();
        Card card2 = new CardBuilder().withFrontFace("Yes").withBackFace("네").build();

        AddCardCommand addCard1Command = new AddCardCommand(card1);
        AddCardCommand addCard2Command = new AddCardCommand(card2);

        // same object -> returns true
        assertTrue(addCard1Command.equals(addCard1Command));

        // same values -> returns true
        AddCardCommand addCard1CommandCopy = new AddCardCommand(card1);
        assertTrue(addCard1Command.equals(addCard1CommandCopy));

        // different types -> returns false
        assertFalse(addCard1Command.equals(1));

        // null -> returns false
        assertFalse(addCard1Command.equals(null));

        // different person -> returns false
        assertFalse(addCard1Command.equals(addCard2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLibraryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibraryFilePath(Path libraryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibrary(ReadOnlyLibrary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void returnToLibrary() {
            throw new AssertionError("This method should not be called");
        }

        //=========== Deck Functions =============================================================
        @Override
        public boolean hasDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeck(Deck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeck(Deck target, Deck editedDeck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void createDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectDeck(Index targetIdx) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Deck getCurrentDeck() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Deck getDeck(Index index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean renameDeck(Index targetIndex, Name name) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Deck> selectedDeckProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Mode> currentModeProperty() {
            throw new AssertionError("This method should not be called");
        }

        //=========== Card Functions  =============================================================
        @Override
        public boolean hasCard(Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteCard(Card target) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addCard(Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void replaceCard(Card target, Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Card> playingCardProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Boolean> flippedProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Integer> cardAttemptedProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Integer> cardRemainingProperty() {
            throw new AssertionError("This method should not be called");
        }


        @Override
        public View getView() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Mode getMode() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setCurrentMode(Mode mode) {
            throw new AssertionError("This method should not be called");
        }

        //=========== Filtered Deck List Accessors =============================================================

        @Override
        public ObservableList<Deck> getFilteredDeckList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Card> getFilteredCardList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredDeckList(Predicate<Deck> predicate) {
            throw new AssertionError("This method should not be called");
        }

        //=========== Game Mode Functions =============================================================
        @Override
        public void setSelectedDeck(Deck deck) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setFlipped(Boolean value) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setCardAttempted(int value) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setCardRemaining(int value) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setPlayingCard(Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card getCard(Index index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card play(Index index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public BackFace flip() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card answerYes() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Statistics stop() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public GameManager getGame() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card answerNo() {
            throw new AssertionError("This method should not be called");
        }

    }

    /**
     * A Model stub that always accepts a card being added.
     */
    private class ModelStubAcceptingCardAdded extends ModelStub {
        final ArrayList<Card> cardsAdded = new ArrayList<>();

        @Override
        public Mode getMode() {
            return Mode.VIEW;
        }

        @Override
        public View getView() {
            return View.DECK;
        }

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return cardsAdded.stream().anyMatch(card::equals);
        }

        @Override
        public void addCard(Card card) {
            requireNonNull(card);
            cardsAdded.add(card);
        }
    }

    /**
     * A Model stub that cannot add a card due to being in Play Mode
     */
    private class ModelStubPlayMode extends ModelStub {
        final ArrayList<Card> cardsAdded = new ArrayList<>();

        @Override
        public Mode getMode() {
            return Mode.PLAY;
        }

        @Override
        public View getView() {
            return View.DECK;
        }

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return cardsAdded.stream().anyMatch(card::equals);
        }

        @Override
        public void addCard(Card card) {
            requireNonNull(card);
            cardsAdded.add(card);
        }
    }

    /**
     * A Model stub that cannot add a card due to being in Library View
     */
    private class ModelStubLibraryView extends ModelStub {
        final ArrayList<Card> cardsAdded = new ArrayList<>();

        @Override
        public Mode getMode() {
            return Mode.VIEW;
        }

        @Override
        public View getView() {
            return View.LIBRARY;
        }

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return cardsAdded.stream().anyMatch(card::equals);
        }

        @Override
        public void addCard(Card card) {
            requireNonNull(card);
            cardsAdded.add(card);
        }
    }
}
